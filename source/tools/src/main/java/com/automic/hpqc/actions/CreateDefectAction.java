/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;
import java.util.Iterator;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.HPQCUserService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.User;
import com.automic.hpqc.model.Users;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author sumitsamson
 * 
 */
/**
 * This class creates the defect in the HPQC and returns the created defect id
 * */
public class CreateDefectAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(CreateDefectAction.class);

    private String domain;
    private String project;
    private File filePath;

    public CreateDefectAction() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("filepath", true, "File containing defect data xml");
    }

    private void prepareInputParameters() throws AutomicException {
        String temp = getOptionValue("filepath");
        filePath = new File(temp);
        HPQCValidator.checkFilePathNotEmpty(temp);
        HPQCValidator.checkFileExists(filePath);

        domain = getOptionValue("domain");
        HPQCValidator.checkDomainNotEmpty(domain);

        project = getOptionValue("project");
        HPQCValidator.checkDomainNotEmpty(project);
    }

    @Override
    public void executeSpecific() throws AutomicException {

        prepareInputParameters();

        HPQCUserService hpqcservice = new HPQCUserService();
        String loggedInUser;
        ClientResponse response = null;
        Fields inputFields = null;
        try {
            inputFields = CommonUtil.xml2object(Fields.class, filePath);
        } catch (JAXBException e) {
            LOGGER.error("Error occured while unmarshilling file " + filePath.getName(), e);
            throw new AutomicException(ExceptionConstants.INVALID_DEFECT_INFORMATION);
        }

        loggedInUser = hpqcservice.getLoginUser(client, baseUrl);

        Users users = hpqcservice.getUserList(baseUrl, domain, project, client);

        String fullname = null;
        for (User user : users.getUserList()) {
            if (loggedInUser.equals(user.getName())) {
                fullname = user.getFullName();
                break;
            }
        }

        if (inputFields.getFieldList() == null) {
            throw new AutomicException("Error in the defect field xml");
        }
        for (Iterator<Field> itr = inputFields.getFieldList().iterator(); itr.hasNext();) {
            Field f = itr.next();
            if (f.getValue() == null || f.getValue().isEmpty()) {
                itr.remove();
            } else if (f.getName().equals(Constants.DEV_COMMENTS)) {
                f.setValue(populateCommentField(f.getValue(), loggedInUser, fullname, null));
            } else if (f.getName().equals(Constants.ATTACHEMNT)) {
                itr.remove();
            }
        }

        Entity entity = new Entity("defect", inputFields);

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").queryParam("alt", MediaType.APPLICATION_XML);

        LOGGER.info("Calling url " + webResource.getURI());

        response = webResource.entity(entity).type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class);

        printDefectId(response.getEntity(Entity.class));
    }

    private void printDefectId(Entity entity) {

        for (Field field : entity.getFields().getFieldList()) {
            if ("ID".equalsIgnoreCase(field.getName())) {
                int defectId = Integer.parseInt(field.getValue());
                ConsoleWriter.writeln("defect_id ::=" + defectId);
                LOGGER.info("Defect " + defectId + " has been created successfully");
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
        if (filePath != null) {
            LOGGER.info("Create Defect File deleted " + filePath.delete());
        }
    }

}
