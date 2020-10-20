/**
 * 
 */
package com.automic.hpqc.actions;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.utils.CommonUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author sumitsamson
 *
 *         This class will delete the defect by the given defect id from HPQC.
 */

public class DeleteDefectAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(DeleteDefectAction.class);
    private String domain;
    private String project;
    private int defectId;

    public DeleteDefectAction() {
        addOption("domain", true, "Domain name");
        addOption("project", true, "project name");
        addOption("defectid", true, " Defect id");
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

        defectId = CommonUtil.parseStringValue(getOptionValue("defectid"), Constants.MINUS_ONE);
        HPQCValidator.lessThan(defectId, Constants.ZERO, "Defect Id");
    }

    @Override
    public void executeSpecific() throws AutomicException {
        // initialize & validate input
        prepareInput();
        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(String.valueOf(defectId));
        LOGGER.info("Calling url " + webResource.getURI());
        webResource.accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);
        LOGGER.info("Defect with ID " + defectId + " deleted successfully ");
    }
}
