/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author sumitsamson
 * 
 */
/**
 * This class attaches the given attachment into the existing defect.*/
public class AttachmentAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(AttachmentAction.class);
    private static final String INDENTATION = "%-30s | %20s %n";
    private static final int NEGATIVE_RANGE_START = -1;
    private String domain;
    private String project;
    private int defectId;
    private File attachmentToUpload;

    public AttachmentAction() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("defectid", true, "Defect id where attachment needs to be added");
        addOption("attachment", true, "File/Folder needs to be attached");
    }

    private void prepareInputParameters() throws AutomicException {

        try {
            domain = getOptionValue("domain");
            HPQCValidator.checkDomainNotEmpty(domain);

            project = getOptionValue("project");
            HPQCValidator.checkProjectNotEmpty(project);

            defectId = CommonUtil.parseStringValue(getOptionValue("defectid"), NEGATIVE_RANGE_START);
            HPQCValidator.lessThan(defectId, Constants.ZERO, "Defect Id");

            String attachmentPath = getOptionValue("attachment");
            HPQCValidator.checkFilePathNotEmpty(attachmentPath);
            attachmentToUpload = new File(attachmentPath);

            if (!attachmentToUpload.exists()) {
                LOGGER.error(String.format(ExceptionConstants.INVALID_ATTACHMENT, attachmentPath));
                throw new AutomicException(String.format(ExceptionConstants.INVALID_ATTACHMENT, attachmentPath));
            }

            if (attachmentToUpload.isDirectory() && (listFiles(attachmentToUpload).isEmpty())) {
                LOGGER.error(String.format(ExceptionConstants.EMPTY_DIRECTORY, attachmentPath));
                throw new AutomicException(String.format(ExceptionConstants.EMPTY_DIRECTORY, attachmentPath));
            }
        } catch (AutomicException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void executeSpecific() throws AutomicException {

        prepareInputParameters();

        StringBuilder fileAttachStatus = new StringBuilder();

        client.setChunkedEncodingSize(1024);

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(String.valueOf(defectId)).path("attachments");
        LOGGER.info("Calling url " + webResource.getURI());

        try {

            fileAttachStatus = new StringBuilder();

            if (attachmentToUpload.isDirectory()) {
                List<File> files = listFiles(attachmentToUpload);

                for (File f : files) {

                    upload(webResource, f);

                    if (files.indexOf(f) == 0) {
                        fileAttachStatus.append(String.format(INDENTATION, "FileName", "Attach Status"));
                    }

                    fileAttachStatus.append(String.format(INDENTATION, f.getName(), "SUCCESS"));
                }

            } else {

                upload(webResource, attachmentToUpload);
                fileAttachStatus.append(String.format(INDENTATION, "FileName", "Attach Status"));
                fileAttachStatus.append(String.format(INDENTATION, attachmentToUpload.getName(), "SUCCESS"));
            }

        } finally {

            if (fileAttachStatus.length() > 1) {
                ConsoleWriter.write(fileAttachStatus.toString());
            }

        }

    }

    private void upload(WebResource webResource, File fileToUpload) throws AutomicException {
        LOGGER.info("Uploading file :" + fileToUpload.getName());

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(fileToUpload);
        } catch (FileNotFoundException e) {
            LOGGER.error(String.format(ExceptionConstants.INVALID_FILE, fileToUpload.getName()), e);
            throw new AutomicException(String.format(ExceptionConstants.INVALID_FILE, fileToUpload.getName()));
        }

        webResource.accept(MediaType.APPLICATION_XML).entity(inputStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("Slug", fileToUpload.getName()).post(ClientResponse.class);

    }

    /**
     * Finds the files in a directory
     * */
    private List<File> listFiles(File fileToUpload) {

        List<File> files = new ArrayList<File>();

        File[] fileFolder = fileToUpload.listFiles();

        if (null != fileFolder) {

            for (File f : fileFolder) {
                if (!f.isDirectory()) {
                    files.add(f);
                }
            }
        }

        return files;
    }

}
