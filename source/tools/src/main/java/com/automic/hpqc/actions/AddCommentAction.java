/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.HPQCUserService;
import com.automic.hpqc.actions.service.LockService;
import com.automic.hpqc.actions.service.ReadEntityService;
import com.automic.hpqc.actions.service.UnlockService;
import com.automic.hpqc.actions.service.UpdateService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.User;
import com.automic.hpqc.model.Users;
import com.automic.hpqc.utils.CommonUtil;

/**
 * @author sumitsamson
 * 
 */

/**
 * This class adds the comments to the specific defect.It will append the new comment to the existing comments(if any)
 */

public class AddCommentAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(AddCommentAction.class);

    private static final int NEGATIVE_RANGE_START = -1;
    private String domain;
    private String project;
    private int defectId;
    private File commentFile;

    public AddCommentAction() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("defectid", true, "Defect id where commnet needs to be added");
        addOption("filepath", true, "Comment to be added");

    }

    private void prepareInputParameters() throws AutomicException {

        try {
            String temp = getOptionValue("filepath");
            commentFile = new File(temp);
            HPQCValidator.checkFilePathNotEmpty(temp);            
            HPQCValidator.checkFileExists(commentFile);

            domain = getOptionValue("domain");
            HPQCValidator.checkDomainNotEmpty(domain);

            project = getOptionValue("project");
            HPQCValidator.checkProjectNotEmpty(project);

            defectId = CommonUtil.parseStringValue(getOptionValue("defectid"), NEGATIVE_RANGE_START);
            HPQCValidator.lessThan(defectId, Constants.ZERO, "Defect Id");
        } catch (AutomicException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void executeSpecific() throws AutomicException {

        prepareInputParameters();

        UnlockService unlockService = null;
        ReadEntityService readservice = null;
        LockService lockService = null;
        HPQCUserService hpqcService = null;
        UpdateService updateService = null;
        boolean isLocked = false;

        try {

            updateService = new UpdateService(client, baseUrl, domain, project);
            readservice = new ReadEntityService();
            hpqcService = new HPQCUserService();
            lockService = new LockService();
            unlockService = new UnlockService();

            isLocked = lockService.putLockOnDefect(client, baseUrl, domain, project, defectId);
            if (isLocked) {

                // get the defect in which comment needs to be added
                Entity entityToUpdate = readservice.readEntity(client, baseUrl, domain, project, "defects", defectId);

                String loggedInUser = hpqcService.getLoginUser(client, baseUrl);

                Users users = hpqcService.getUserList(baseUrl, domain, project, client);

                String fullname = null;
                for (User user : users.getUserList()) {
                    if (loggedInUser.equals(user.getName())) {
                        fullname = user.getFullName();
                        break;
                    }
                }

                // get the old comments and add the new comment at the last and
                // then update the defect
                addCommentAndUpdate(updateService, entityToUpdate, loggedInUser, fullname);
            }

        } catch (IOException e) {
            LOGGER.error("Error while reading comment file " + commentFile, e);
            throw new AutomicException(String.format(ExceptionConstants.ERROR_READING_FILE, commentFile));
        } finally {
            if (isLocked) {
                unlockService.removeLockOnDefect(client, baseUrl, domain, project, defectId);
            }
        }
    }

    /**
     * @param updateService
     * @param entityToUpdate
     * @param loggedInUser
     * @param fullname
     * @throws IOException
     * @throws AutomicException
     */
    private void addCommentAndUpdate(UpdateService updateService, Entity entityToUpdate, String loggedInUser,
            String fullname) throws IOException, AutomicException {
        for (Field field : entityToUpdate.getFields().getFieldList()) {
            if (field.getName().equals(Constants.DEV_COMMENTS)) {

                String oldComment = field.getValue();
                String newComment = CommonUtil.readFileContents(commentFile);

                String finalCommentString = populateCommentField(newComment, loggedInUser, fullname, oldComment);
                field.setValue(finalCommentString);
                ArrayList<Field> tmpFieldList = new ArrayList<Field>();
                tmpFieldList.add(field);
                Fields newCommentField = new Fields(tmpFieldList);

                updateService.updateDefect(defectId, newCommentField);
                LOGGER.info(String.format("Comment added for defect id: %s  ", defectId));

                break;
            }
        }
    }

    private static String populateCommentField(String newComment, String loggedInUser, String fullname,
            String oldComment) {
        StringBuilder finalCommentString = new StringBuilder();
        StringBuilder formatTemplate = new StringBuilder();

        if (null != oldComment && !oldComment.isEmpty()) {

            finalCommentString.append(oldComment);

            formatTemplate.append(String.format("%s&lt;%s&gt;, %s :", fullname, loggedInUser,
                    CommonUtil.getFormattedDate("MM/dd/yyy")));
            formatTemplate.append(newComment);

        } else {
            formatTemplate.append(String.format("%s<%s>, %s :", fullname, loggedInUser,
                    CommonUtil.getFormattedDate("MM/dd/yyy")));
            formatTemplate.append(newComment);
        }

        finalCommentString.append(formatTemplate.toString());
        return finalCommentString.toString();
    }

    @Override
    protected void cleanUp() {
        if (commentFile != null) {
            LOGGER.info("Comment File deleted " + commentFile.delete());
        }
    }
}
