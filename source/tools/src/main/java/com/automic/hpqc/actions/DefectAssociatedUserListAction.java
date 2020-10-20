/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.HPQCUserService;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Users;
import com.automic.hpqc.utils.CommonUtil;

/**
 * @author sumitsamson
 * 
 *         This class retrieves all the users for the specified project.
 */
public class DefectAssociatedUserListAction extends AbstractHttpAction {

    private String domain;
    private String project;
    private File filePath;

    public DefectAssociatedUserListAction() {
        addOption("domain", true, "Domain name");
        addOption("project", true, "project name");
        addOption("filepath", true, "File path");
    }

    /**
     * This method validate and initialize input
     * 
     * @throws AutomicException
     */
    private void prepareInput() throws AutomicException {
        // initialize variable
        domain = getOptionValue("domain");
        HPQCValidator.checkDomainNotEmpty(domain);

        project = getOptionValue("project");
        HPQCValidator.checkProjectNotEmpty(project);

        String temp = getOptionValue("filepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);
    }

    @Override
    public void executeSpecific() throws AutomicException {
        // initialize & validate input
        prepareInput();
        HPQCUserService hpqcUserService = new HPQCUserService();
        Users users = hpqcUserService.getUserList(baseUrl, domain, project, client);
        CommonUtil.object2Xml(users, true, filePath);
    }
}
