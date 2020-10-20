/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.LockService;
import com.automic.hpqc.actions.service.UnlockService;
import com.automic.hpqc.actions.service.UpdateService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.utils.CommonUtil;

/**
 * @author sumitsamson
 * 
 *         Update the defect information for a specific defect.
 */
public class UpdateDefectAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(UpdateDefectAction.class);
    private String domain;
    private String project;
    private int defectId;
    private File filePath;

    public UpdateDefectAction() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("defectid", true, "Defect id for which the information is to be updated");
        addOption("filepath", true, "File containing defect data xml");
    }

    /**
     * This method validate and initialize input
     * 
     * @throws AutomicException
     */
    private void prepareInputParameters() throws AutomicException {
        String temp = getOptionValue("filepath");
        filePath = new File(temp);
        HPQCValidator.checkFilePathNotEmpty(temp);
        HPQCValidator.checkFileExists(filePath);

        domain = getOptionValue("domain");
        HPQCValidator.checkDomainNotEmpty(domain);

        project = getOptionValue("project");
        HPQCValidator.checkProjectNotEmpty(project);

        defectId = CommonUtil.parseStringValue(getOptionValue("defectid"), Constants.MINUS_ONE);
        HPQCValidator.lessThan(defectId, Constants.ZERO, "Defect Id");

    }

    @Override
    public void executeSpecific() throws AutomicException {
        prepareInputParameters();
        UpdateService updateService = new UpdateService(client, baseUrl, domain, project);
        LockService lockService = new LockService();
        UnlockService unlockService = new UnlockService();
        boolean isLocked = false;
        try {
            isLocked = lockService.putLockOnDefect(client, baseUrl, domain, project, defectId);
            if (isLocked) {
                try {
                    updateService.updateDefect(defectId, CommonUtil.xml2object(Fields.class, filePath));
                } catch (JAXBException e) {
                    LOGGER.error("Error occured while unmarshalling", e);
                    throw new AutomicException(ExceptionConstants.INVALID_DEFECT_INFORMATION);
                }
            }
        } finally {
            if (isLocked) {
                unlockService.removeLockOnDefect(client, baseUrl, domain, project, defectId);
            }
        }
    }

    @Override
    protected void cleanUp() {
        if (filePath != null) {
            LOGGER.info("Update defect File deleted " + filePath.delete());
        }
    }
}
