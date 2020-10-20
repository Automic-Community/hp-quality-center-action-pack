package com.automic.hpqc.qtp.service;

import org.apache.logging.log4j.Logger;

import ota.client12.ClassFactory;
import ota.client12.IExecEventInfo;
import ota.client12.IExecutionStatus;
import ota.client12.IList;
import ota.client12.ITDConnection2;
import ota.client12.ITSScheduler;
import ota.client12.ITestExecStatus;
import ota.client12.ITestSet;
import ota.client12.ITestSetFolder;
import ota.client12.ITestSetTreeManager;

import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.model.QTPTestSetModel;
import com.automic.hpqc.qtp.constants.QTPConstants;
import com.automic.hpqc.qtp.exceptions.OtaException;
import com.automic.hpqc.utils.ConsoleWriter;
import com4j.Com4jObject;
import com4j.ComException;

/**
 * @author kamalgarg
 */
/**
 * TestsetExecutionService class is acting as a wrapper class between Test Set Execution Action and ota client stubs.
 * The method 'testsetExecution' will execute a specified test set by calling ota stubs.
 */

public class QTPTestsetExecutionService {

    private final Logger logger;

    private ITDConnection2 connection;
    private ITSScheduler scheduler;
    private ITestSet testSet;

    private boolean testSetExecStatus;
    private QTPTestSetModel testSetModel;

    /**
     * public constructor
     * 
     * @param qtpTestsetModel
     *            contains all the required parameters to run the testset
     * @param logger
     *            log object
     * 
     **/
    public QTPTestsetExecutionService(QTPTestSetModel qtpTestsetModel, Logger logger) {
        this.logger = logger;
        this.testSetModel = qtpTestsetModel;
        testSetExecStatus = true;
    }

    /**
     * @param action
     *            arguments
     * @throws OtaException
     * 
     *             This method will execute a specified test set on QTP
     **/
    public void testsetExecution() throws OtaException {
        logger.info("Entered into testsetExecution() method");

        /**
         * Check the jvm 32 bit or 64
         */
        String javaBitSize = System.getProperty("sun.arch.data.model");
        String temp;
        if (javaBitSize != null) {
            temp = javaBitSize + " bit Java found..";            
        } else {
            temp = "No bit size found for Java..!!";
        }
        logger.info(temp);
        ConsoleWriter.writeln(temp);

        try {
            // Establishing the connection with Qtp
            createConnection();
            retrieveTestSet();
            if (testSet == null) {
                String msg = "Possible reason may be :: specified test set "
                        + testSetModel.getTestSetName() + " does not exist for the given folder path "
                        + testSetModel.getTestSetFolderPath();
                throw new OtaException(msg);
            }
            ConsoleWriter.writeln("TestSet has been retrieved");
            logger.info("Executing test set " + testSetModel.getTestSetName());
            startScheduler();
            
            ConsoleWriter.writeln("TestSet Scheduler has been started");

            /**
             * calling refreshExecStatusInfo() to refresh all exec status info
             */
            IExecutionStatus status = scheduler.executionStatus().queryInterface(IExecutionStatus.class);
            refreshExecStatusInfo(status, QTPConstants.EXECUTION);
            logger.debug("Execution log " + scheduler.executionLog());
            logger.info("Execution has been finished");

            logTestReport(status);
            if (testSetExecStatus) {
                ConsoleWriter.writeln("status::=Pass");
                logger.info("SUCCESS", "Test Set has been passed!");
            } else {
                ConsoleWriter.writeln("status::=Fail");
                logger.info("FAILURE", "Test Set has not been passed!");
            }
        } finally {
            if (scheduler != null) {
                try {
                    stopScheduler(scheduler);
                } catch (Exception ex) {
                    logger.error("Got some error while stop scheduler", ex);
                }
            }
            releaseConnection();
        }
    }

    private void createConnection() throws OtaException {
        StringBuilder urlBuilder = new StringBuilder(testSetModel.getBaseUrl());
        String url = (urlBuilder.append(QTPConstants.SLASH).append(QTPConstants.URL_POSTFIX).append(QTPConstants.SLASH))
                .toString();

        connection = ClassFactory.createTDConnection();
        connection.initConnectionEx(url);
        connection.connectProjectEx(testSetModel.getDomain(), testSetModel.getProject(), testSetModel.getUsername(),
                testSetModel.getPassword());
        boolean connectionStatus = connection.connected();
        String temp = "Connection status with QTP:: " + connection.connected();
        logger.info(temp);
        ConsoleWriter.writeln(temp);
        if (!connectionStatus) {
            String msg = "Failure!! Connection Status ::" + connectionStatus + " .Not able to make a connection.";
            throw new OtaException(msg);
        }
    }

    private void retrieveTestSet() throws OtaException {
        ITestSetTreeManager treeManagerObj = connection.testSetTreeManager().queryInterface(ITestSetTreeManager.class);
        IList testSets = null;

        try {
            Com4jObject com4jObj = treeManagerObj.nodeByPath(testSetModel.getTestSetFolderPath());
            if (null == com4jObj) {
                String msg = "Failure!! Specified test set folder path " + testSetModel.getTestSetFolderPath()
                        + " not found!!";
                throw new OtaException(msg);
            }

            ITestSetFolder testSetFolderObj = com4jObj.queryInterface(ITestSetFolder.class);

            testSets = testSetFolderObj.findTestSets(testSetModel.getTestSetName(), false, "");

            if (null == testSets || testSets.count() == 0) {
                String msg = "Possible reason may be :: specified test set "
                        + testSetModel.getTestSetName() + " does not exist for the given folder path "
                        + testSetModel.getTestSetFolderPath();
                throw new OtaException(msg);
            } else {
                String folderName = testSetFolderObj.name();
                for (int i = 1; i <= testSets.count(); i++) {
                    ITestSet tempSet = ((Com4jObject) testSets.item(i)).queryInterface(ITestSet.class);
                    String currFolderName = tempSet.testSetFolder().queryInterface(ITestSetFolder.class).name();
                    if (folderName.equals(currFolderName)) {
                        testSet = tempSet;
                        break;
                    }
                }
            }
        } catch (ComException e) {
            String msg = "Failure ,Possible reason may be :: specified test set folder path "
                    + testSetModel.getTestSetFolderPath() + " not exists!!";
            logger.error(msg, e);
            throw new OtaException(msg);
        }
    }

    private void startScheduler() {
        scheduler = ((Com4jObject) testSet.startExecution("")).queryInterface(ITSScheduler.class);
        logger.info("Scheduler started");
        String hostipAddr = testSetModel.getHostIPAddress();
        if (null == hostipAddr || hostipAddr.equalsIgnoreCase(QTPConstants.LOCAL_HOST)
                || "".equalsIgnoreCase(hostipAddr) || hostipAddr.isEmpty()) {
            logger.info("Running test on local host..");
            scheduler.runAllLocally(true);
        } else {
            logger.info("Running test on host:: " + hostipAddr);
            scheduler.tdHostName(hostipAddr);
        }
        scheduler.run(testSet);
    }

    private void refreshExecStatusInfo(IExecutionStatus status, String execType) {
        logger.info(execType + " Status :" + status.finished());
        while (!status.finished()) {
            try {
                Thread.sleep(Constants.FIVE * 1000);
            } catch (InterruptedException e) {
                logger.debug("InterruptedException " + e);
            }
            status.refreshExecStatusInfo(QTPConstants.ALL, true);
            logger.info(execType + " Status :" + status.finished());
        }
    }

    private void logTestReport(IExecutionStatus status) {
        IList eventList = status.eventsList();

        for (int j = 1; j <= eventList.count(); j++) {
            IExecEventInfo eventInfo = ((Com4jObject) eventList.item(j)).queryInterface(IExecEventInfo.class);

            String eventType = getStatusAsText(eventInfo.eventType());

            String eventReport = "EventDate :" + eventInfo.eventDate() + "  EventTime :" + eventInfo.eventTime()
                    + "  EventType :" + eventType;
            ConsoleWriter.writeln(eventReport);
            logger.info(eventReport);

        }

        logger.info("Individual tests report");

        for (int j = 1; j <= status.count(); j++) {
            ITestExecStatus execStatus = ((Com4jObject) status.item(j)).queryInterface(ITestExecStatus.class);
            String execTestStatus = execStatus.status();

            if (!execTestStatus.equalsIgnoreCase(QTPConstants.STATUS_FINISHED_PASSED)) {
                testSetExecStatus = false;
            }
            String testInstanceReport = "TestInstance :" + execStatus.testInstance() + "  Message :"
                    + execStatus.message() + "  Status :" + execStatus.status();
            ConsoleWriter.writeln(testInstanceReport);
            logger.info(testInstanceReport);
        }
    }

    private String getStatusAsText(int status) {
        String textStatus = null;
        switch (status) {
            case Constants.ONE:
                textStatus = QTPConstants.STATUS_FAIL;
                break;
            case Constants.TWO:
                textStatus = QTPConstants.STATUS_PASSED;
                break;
            case Constants.THREE:
                textStatus = QTPConstants.STATUS_ENV_FAIL;
                break;
            case Constants.FOUR:
                textStatus = QTPConstants.STATUS_TIMEOUT;
                break;
            case Constants.FIVE:
                textStatus = QTPConstants.STATUS_MANUAL;
                break;
            default:
                textStatus = QTPConstants.STATUS_UNKNOWN;
        }
        return textStatus;
    }

    private void stopScheduler(ITSScheduler scheduler) {
        logger.info("stopScheduler() started");
        scheduler.stop(QTPConstants.ALL);
        /**
         * calling refreshExecStatusInfo() to refresh all exec status info
         */
        IExecutionStatus status = scheduler.executionStatus().queryInterface(IExecutionStatus.class);
        refreshExecStatusInfo(status, QTPConstants.STOP);
        logger.info("stopScheduler() ended");
    }

    private void releaseConnection() {
        if (connection != null) {
            try {
                connection.disconnectProject();
            } catch (Exception ex) {
                logger.error("Got some error while disconnecting project from connection ", ex);
            }
            try {
                connection.logout();
            } catch (Exception ex) {
                logger.error("Got some error while loggingout ", ex);
            }
            try {
                connection.releaseConnection();
            } catch (Exception ex) {
                logger.error("Got some error while releasing connection ", ex);
            }
        }
    }
}
