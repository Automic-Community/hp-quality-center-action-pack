/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;
import java.io.IOException;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.EntityService;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.utils.CommonUtil;

/**
 * @author sumitsamson
 * 
 *         This Class gets the list of filterable fields for the defect and the output will be saved in a file.
 */
public class QueryFilterableFieldsAction extends AbstractHttpAction {

    private String domain;
    private String project;
    private String entityType;
    private File filePath;

    public QueryFilterableFieldsAction() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("entity", true, "Entity type");
        addOption("filepath", true, "File to save the list of filterable fields");
    }

    private void prepareInputParameters() throws AutomicException {
        domain = getOptionValue("domain");
        HPQCValidator.checkDomainNotEmpty(domain);

        project = getOptionValue("project");
        HPQCValidator.checkProjectNotEmpty(project);

        entityType = getOptionValue("entity");
        HPQCValidator.checkNotEmpty(entityType, "Entity");

        String temp = getOptionValue("filepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);
    }

    @Override
    public void executeSpecific() throws AutomicException {
        prepareInputParameters();
        EntityService entityService = new EntityService(client, baseUrl, domain, project);
        Fields fields = entityService.getFieldsForEntity(entityType, false);
        StringBuilder sb = new StringBuilder();
        for (Field field : fields.getFieldList()) {
            if (field.isFilterable()) {
                sb.append(field.getName());
                sb.append("\n");
            }
        }
        try {
            CommonUtil.writeFile(filePath, sb.toString());
        } catch (IOException e) {
            throw new AutomicException(ExceptionConstants.SYSTEM_ERROR, e);
        }
    }
}
