package com.automic.hpqc.actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;

/**
 * @author kamalgarg
 * 
 */

/**
 * JobLuncherAction class invokes the QTPTestSetExecutionAction
 */
public class JobLauncherAction extends AbstractAction {

    private static final Logger LOGGER = LogManager.getLogger(JobLauncherAction.class);

    private String baseUrl;
    private String domain;
    private String project;
    private String testSetFolderPath;
    private String testSetName;
    private String hostIPAddress;
    private String username;
    private String password;
    private String mainAction;
    private String jarPath;

    private Map<String, String> argMap = new HashMap<String, String>();

    public JobLauncherAction() {

        addOption(Constants.BASE_URL, true, "Base url");
        addOption("project", true, "Project of the user");
        addOption("domain", true, "Domain of the user");
        addOption("testsetfolderpath", true, "Test set folder path");
        addOption("testsetname", true, "Test set name");
        addOption("hostipaddress", true, "Host IP address");
        addOption("username", true, "Username for testset");
        addOption("password", true, "Password for test");
        addOption("mainaction", true, "Main action for running qtp test set");
        addOption("jarpath", true, "Jar Path");

    }

    private void initialize() {
        jarPath = getOptionValue("jarpath");
        mainAction = getOptionValue("mainaction");
        baseUrl = getOptionValue(Constants.BASE_URL);
        domain = getOptionValue("domain");
        project = getOptionValue("project");
        testSetFolderPath = getOptionValue("testsetfolderpath");
        testSetName = getOptionValue("testsetname");
        hostIPAddress = getOptionValue("hostipaddress");
        username = getOptionValue("username");
        password = getOptionValue("password");

    }

    private void validate() throws AutomicException {

        try {
            String temp = getOptionValue("jarpath");
            HPQCValidator.checkFilePathNotEmpty(temp);
            HPQCValidator.checkFileExists(new File(temp));
        } catch (AutomicException e) {
            LOGGER.error(e);
            throw e;
        }
    }

    private void fillArgMap() {

        argMap.put("-baseurl", baseUrl);
        argMap.put("-domain", domain);
        argMap.put("-project", project);
        argMap.put("-testsetfolderpath", testSetFolderPath);
        argMap.put("-testsetname", testSetName);
        argMap.put("-hostipaddress", hostIPAddress);
        argMap.put("-username", username);
        argMap.put("-password", password);

    }

    @Override
    public void execute() throws AutomicException {

        initialize();
        validate();
        fillArgMap();

        StringBuilder sb = new StringBuilder("java -jar ").append("\"").append(jarPath).append("\"").append(" ");
        sb.append("\"").append("-action").append("\"").append(" ").append("\"").append(mainAction).append("\"")
                .append(" ");

        for (String key : argMap.keySet()) {
            sb.append("\"");
            sb.append(key);
            sb.append("\"");
            sb.append(" ");
            sb.append("\"");
            sb.append(argMap.get(key));
            sb.append("\"");
            sb.append(" ");

        }

        try {
            Runtime.getRuntime().exec(sb.toString());
        } catch (IOException e) {
            LOGGER.error("Error occured while running Job Launcher ", e);
            throw new AutomicException(ExceptionConstants.GENERIC_ERROR_MSG);
        }

    }

}
