/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.utils.CommonUtil;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author sumitsamson
 *
 *         DecfectQueryData class implements the IUserAction interface and overrides its executeAction method to query
 *         defect data like domain name and project in the domain.
 */
public class ReadDomainAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(UpdateDefectAction.class);

    private File filePath;

    public ReadDomainAction() {
        addOption("filepath", true, "File to save the details of project and domain");
    }

    private void prepareInput() throws AutomicException {
        String temp = getOptionValue("filepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);
    }

    @Override
    public void executeSpecific() throws AutomicException {

        prepareInput();

        String content = client.resource(baseUrl).path("qcbin").path("rest").path("domains")
                .queryParam("include-projects-info", "y").accept(MediaType.APPLICATION_XML).get(ClientResponse.class)
                .getEntity(String.class);
        try {
            CommonUtil.writeFile(filePath, content);
        } catch (IOException e) {
            String msg = String.format(ExceptionConstants.UNABLE_TO_WRITEFILE, filePath);
            LOGGER.error(msg, e);
            throw new AutomicException(msg);
        }
    }

}
