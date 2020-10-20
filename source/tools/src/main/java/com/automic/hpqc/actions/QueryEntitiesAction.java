/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.HpqcQueryService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entities;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;

/**
 * This class is used for entity related query
 * 
 * @author sumitsamson
 * 
 */
public class QueryEntitiesAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(QueryEntitiesAction.class);

    private String domain;
    private String project;
    private String entityResource;
    private String filterableFields;
    private String queryText;
    private int maxQuery;
    private File filePath;

    public QueryEntitiesAction() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("artifacts", true, "Entity type of query");
        addOption("fields", false, "Filterable fields of query");
        addOption("query", false, "Query text");
        addOption("maxquery", true, "Maximum query results that can be retrieved");
        addOption("filepath", true, "File to save generated results");
    }

    private void validate() throws AutomicException {
        domain = getOptionValue("domain");
        HPQCValidator.checkNotEmpty(domain, "Domain");

        project = getOptionValue("project");
        HPQCValidator.checkNotEmpty(project, "Project");

        entityResource = getOptionValue("artifacts");
        HPQCValidator.checkNotEmpty(entityResource, "Artifact(s)");
        entityResource = entityResource.toLowerCase();

        maxQuery = CommonUtil.parseStringValue(getOptionValue("maxquery"), Constants.MINUS_TWO);
        HPQCValidator.lessThan(maxQuery, Constants.MINUS_ONE, "Max Query");

        String temp = getOptionValue("filepath");
        HPQCValidator.checkNotEmpty(temp, "File Path");
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);
    }

    private void initialize() {
        filterableFields = getOptionValue("fields");
        queryText = getOptionValue("query");
    }

    @Override
    public void executeSpecific() throws AutomicException {

        validate();
        initialize();

        try {
            Entities finalEntities = HpqcQueryService.queryArtifact(client, baseUrl, domain, project, entityResource,
                    queryText, filterableFields, maxQuery);
            ConsoleWriter.writeln("TOTAL_RESULT_COUNT ::=" + finalEntities.getTotalResults());

            if (finalEntities.getEntity() != null) {
                int totalRecords = finalEntities.getEntity().size();
                finalEntities.setTotalResults(totalRecords);
            } else {
                finalEntities.setTotalResults(Constants.ZERO);
            }
            CommonUtil.object2Xml(finalEntities, false, filePath);

            /*
             * if (finalEntities != null && finalEntities.getTotalResults() != 0) {
             * 
             * } else { LOGGER.info("No records found for the specified input"); }
             */
        } catch (IOException e) {
            LOGGER.error("Error occured while executing action ", e);
            throw new AutomicException(ExceptionConstants.GENERIC_ERROR_MSG);
        }
    }

}
