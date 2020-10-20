/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.EntityService;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.utils.CommonUtil;

/**
 * @author sumitsamson
 * 
 */
/**
 * This class will fetch the list associated with the defects*/
public class DefectAssociatedListAction extends AbstractHttpAction {

    private String domain;
    private String project;
    private String fields;
    private File filePath;

    public DefectAssociatedListAction() {
        addOption("domain", true, "Domain name");
        addOption("project", true, "project name");
        addOption("filepath", true, "File path");
        addOption("fields", false, " Fields name");
    }

    /**
     * This method validate and initialize input
     * 
     * @throws AutomicException
     */
    private void prepareInputParameters() throws AutomicException {
        domain = getOptionValue("domain");
        HPQCValidator.checkDomainNotEmpty(domain);

        project = getOptionValue("project");
        HPQCValidator.checkProjectNotEmpty(project);

        fields = getOptionValue("fields");
      
        String temp = getOptionValue("filepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);
    }

    @Override
    public void executeSpecific() throws AutomicException {
        // initialize & validate input
        prepareInputParameters();
        EntityService entityService = new EntityService(client, baseUrl, domain, project);
        com.automic.hpqc.model.Lists defectList = entityService.getListAssociatedWithDefect(fields);
        CommonUtil.object2Xml(defectList, true, filePath);
    }

}
