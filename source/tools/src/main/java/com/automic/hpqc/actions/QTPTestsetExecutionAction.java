/**
 * 
 */
package com.automic.hpqc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.QTPTestSetModel;
import com.automic.hpqc.qtp.exceptions.OtaException;
import com.automic.hpqc.qtp.service.QTPTestsetExecutionService;
import com.automic.hpqc.utils.Validator;

/**
 * @author kamalgarg
 * 
 */

/**
 * QTPTestsetExecutionAction class invokes a wrapper class between Test Set Execution Action and ota client
 * stubs to execute a specified test set.
 */
public class QTPTestsetExecutionAction extends AbstractAction {

    private static final Logger LOGGER = LogManager.getLogger(QTPTestsetExecutionAction.class);

    private String baseUrl;
    private String domain;
    private String project;
    private String testSetFolderPath;
    private String testSetName;
    private String hostIPAddress;
    private String username;
    private String password;
   

    private QTPTestSetModel qtpTestsetModel;

    public QTPTestsetExecutionAction() {   
        addOption(Constants.BASE_URL, true, "Base url");        
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("testsetfolderpath", true, "Test set folder path");
        addOption("testsetname", true, "Test set name");
        addOption("hostipaddress", true, "Host IP address");
        addOption("username", true, "Username for testset");
        addOption("password", true, "Password for test");

    }

 

    private void prepareInputParameters() throws AutomicException {     
        
        try {          
            
            baseUrl = getOptionValue(Constants.BASE_URL);
            HPQCValidator.checkDomainNotEmpty(Constants.BASE_URL);
            Validator.validateURI(baseUrl);
            
            domain = getOptionValue("domain");
            HPQCValidator.checkDomainNotEmpty(domain);

            project = getOptionValue("project");
            HPQCValidator.checkProjectNotEmpty(project);

            testSetFolderPath = getOptionValue("testsetfolderpath");
            HPQCValidator.checkNotEmpty(testSetFolderPath, "Test Set folder path");
            
            testSetName = getOptionValue("testsetname");
            HPQCValidator.checkNotEmpty(testSetName, "Test Set name");
            
            hostIPAddress = getOptionValue("hostipaddress");
            HPQCValidator.checkNotEmpty(hostIPAddress, "Host IP Address");
            
            username = getOptionValue("username");
            HPQCValidator.checkNotEmpty(username, "Username for HPQC");
            
            password = getOptionValue("password");
            HPQCValidator.checkNotEmpty(password, "Password for HPQC");
                       
            qtpTestsetModel = new QTPTestSetModel();
            qtpTestsetModel.setDomain(domain);
            qtpTestsetModel.setProject(project);
            qtpTestsetModel.setTestSetFolderPath(testSetFolderPath);
            qtpTestsetModel.setTestSetName(testSetName);
            qtpTestsetModel.setHostIPAddress(hostIPAddress);
            qtpTestsetModel.setUsername(username);
            qtpTestsetModel.setPassword(password);
            qtpTestsetModel.setBaseUrl(baseUrl);
            
        } catch (AutomicException e) {
            LOGGER.error(e);
            throw e;
        }

    }

    @Override
    public void execute() throws AutomicException {        
        prepareInputParameters();
        LOGGER.info("Entered into testsetExecution() method");
        try {
            QTPTestsetExecutionService testSetExecService = new QTPTestsetExecutionService(qtpTestsetModel,LOGGER);
            testSetExecService.testsetExecution();
        } catch (OtaException e) {
            LOGGER.error("Error occured while executing action ", e);
            throw new AutomicException(e.getMessage());
        }
        LOGGER.info("Exit from testsetExecution() method");
    }

}
