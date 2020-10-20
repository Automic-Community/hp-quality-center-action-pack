package com.automic.hpqc.actions;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.HpqcQueryService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entities;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.RelatedEntities;
import com.automic.hpqc.model.Relation;
import com.automic.hpqc.model.Testset;
import com.automic.hpqc.model.TestsetFolderFields;
import com.automic.hpqc.report.XML2PDFGenerator;
import com.automic.hpqc.utils.CommonUtil;

/**
 * @author kamalgarg
 *
 *         This class is used for test set report generation in pdf format of previously executed Test set.
 */
public class QTPGenerateReportTestsetAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(QTPGenerateReportTestsetAction.class);
    private static final String XSLTFILEPATH = "pdftemplate.xsl";
    private final List<TestsetFolderFields> listFolderFields = new ArrayList<TestsetFolderFields>();
    private String domain;
    private String project;
    private String testSetFolderPath;
    private String testSetName;
    private File inputFilePath;
    private File outputFilePath;

    public QTPGenerateReportTestsetAction() {
        addOption("domain", true, "Domain name");
        addOption("project", true, "project name");
        addOption("testsetfolderpath", true, " Test set folder path");
        addOption("testsetname", true, "Test set name");
        addOption("inputfilepath", true, "Input file path");
        addOption("outputfilepath", true, "Output file path");
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

        testSetFolderPath = getOptionValue("testsetfolderpath");
        HPQCValidator.checkNotEmpty(testSetFolderPath, "TestSet Folder Path");

        testSetName = getOptionValue("testsetname");
        HPQCValidator.checkNotEmpty(testSetName, "TestSet Name");

        String temp = getOptionValue("inputfilepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        inputFilePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(inputFilePath);

        temp = getOptionValue("outputfilepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        outputFilePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(outputFilePath);
    }

    @Override
    public void executeSpecific() throws AutomicException {
        // initialize & validate input
        prepareInput();
        Testset testset = null;
        try {
            // Invoking getTestSetDetails() method to get test set object
            testset = getTestSetDetails();
            if (null == testset) {
                throw new AutomicException("Test set obj found null!");
            }
            CommonUtil.object2Xml(testset, false, inputFilePath);
            try {
                XML2PDFGenerator.generatePDF(inputFilePath, outputFilePath, XSLTFILEPATH);
            } finally {
                if (!inputFilePath.delete()) {
                    LOGGER.error("Error deleteing input file " + inputFilePath);
                }
            }

        } catch (JAXBException | IOException e) {
            LOGGER.error("Error occoured while executing action %s ", e);
            throw new AutomicException(ExceptionConstants.GENERIC_ERROR_MSG);
        }
    }

    /**
     * This method will create the specified test set data in test set pojo object.
     * 
     * @param args
     * @return
     * @throws AutomicException
     * @throws UnsupportedEncodingException
     * @throws JAXBException
     */
    private Testset getTestSetDetails() throws AutomicException, UnsupportedEncodingException, JAXBException {
        LOGGER.info("Executing of getTestSetFolders() starts..");
        Testset testSet = new Testset();
        // Invoking getTestSetFolderId() method to get the specified test set folder id
        String testSetFolderId = getTestSetFolderId();
        String listOfFields = "status,exec-date,exec-time,actual-tester,owner,test-config.name";
        String filter = "test-set.name[" + testSetName + "];test-set.parent-id[" + testSetFolderId + "]";
        Entities entities = HpqcQueryService.queryArtifact(client, baseUrl, domain, project, "test-instances", filter,
                listOfFields, -1);
        if (entities.getEntity() != null && !entities.getEntity().isEmpty()) {
            LOGGER.info("Total test set instances found.. " + entities.getTotalResults());
            ArrayList<Fields> fieldsList = new ArrayList<Fields>();
            for (Entity entity : entities.getEntity()) {
                if (Constants.TEST_INSTANCE.equalsIgnoreCase(entity.getType())) {
                    RelatedEntities relatedEntities = entity.getRelatedEntities();
                    Relation relation = relatedEntities.getRelation();
                    Fields fields = relation.getEntity().getFields();
                    for (Field field : fields.getFieldList()) {
                        if (Constants.NAME.equals(field.getName())) {
                            entity.getFields().getFieldList().add(field);
                            break;
                        }
                    }
                }
                fieldsList.add(entity.getFields());
            }
            ArrayList<Fields> testInstanceFieldsList = new ArrayList<Fields>();
            for (Fields fields : fieldsList) {

                Fields testInstanceFields = new Fields();
                ArrayList<Field> testInstanceFieldList = new ArrayList<Field>();
                for (Field field : fields.getFieldList()) {
                    String name = field.getName();
                    String value = field.getValue();
                    if (Constants.ID.equalsIgnoreCase(name) || Constants.TEST_CONFIG_ID.equalsIgnoreCase(name)) {
                        continue;
                    }
                    Field testInstanceField = new Field();
                    if (!(Constants.ID.equalsIgnoreCase(name) || Constants.TEST_CONFIG_ID.equalsIgnoreCase(name))) {
                        testInstanceField.setName(name);
                        testInstanceField.setValue(value);
                    }
                    testInstanceFieldList.add(testInstanceField);
                }
                testInstanceFields.setFieldList(testInstanceFieldList);
                testInstanceFieldsList.add(testInstanceFields);
            }
            if (testInstanceFieldsList.isEmpty()) {
                throw new AutomicException("No result found while iterating fieldsList!!!");
            }
            testSet.setTestInstanceFieldsList(testInstanceFieldsList);
            testSet.setName(testSetName);
            testSet.setFolderPath(testSetFolderPath);
        } else {
            throw new AutomicException("No result found!!. Possible reason may be sepecified test set " + testSetName
                    + " or test set folder path " + testSetFolderPath + " is incorrect!!.");
        }
        return testSet;
    }

    /**
     * This method will get the specified test set folder id
     * 
     * @return
     * @throws ClientHandlerException
     * @throws HpqcException
     * @throws UnsupportedEncodingException
     *
     * @return
     * @throws AutomicException
     * @throws UnsupportedEncodingException
     */
    private String getTestSetFolderId() throws AutomicException, UnsupportedEncodingException {
        LOGGER.info("Execution of getTestSetFolderId() starts..");
        String testSetFolderId = null;
        String[] indivisualTestSetFolderArr = testSetFolderPath.split(Pattern.quote(Constants.DOUBLE_SLASH));
        if (indivisualTestSetFolderArr.length == 0) {
            throw new AutomicException(
                    "No test set folder found!! Possible reason may be specified test set folder path "
                            + testSetFolderPath + " is invalid!");
        } else {
            for (int i = 0; i < indivisualTestSetFolderArr.length; i++) {
                if (indivisualTestSetFolderArr[i].trim().length() == 0) {
                    throw new AutomicException(
                            "Invalid test set folder found!! Possible reason may be specified test set folder path "
                                    + testSetFolderPath + " is invalid!");
                }
            }
        }
        String listOfFields = "id,parent-id,name";
        Entities entities = HpqcQueryService.queryArtifact(client, baseUrl, domain, project, "test-set-folders", "",
                listOfFields, -1);
        if (entities.getEntity() != null && !entities.getEntity().isEmpty()) {
            LOGGER.info("Total test set folders found.. " + entities.getTotalResults());
            for (Entity entity : entities.getEntity()) {
                if (Constants.TEST_SET_FOLDER.equalsIgnoreCase(entity.getType())) {
                    TestsetFolderFields testSetFolderFields = new TestsetFolderFields();

                    Map<String, String> map = entity.getFields().nameValueMap();
                    if (null != map && !map.isEmpty()) {
                        testSetFolderFields.setFolderName(map.get(Constants.NAME));
                        testSetFolderFields.setFolderId(map.get(Constants.ID));
                        testSetFolderFields.setParentId(map.get(Constants.PARENT_ID));
                        listFolderFields.add(testSetFolderFields);
                    } else {
                        throw new AutomicException("Name value map has found empty!!!!");
                    }
                }
            }
            LOGGER.info("Total records found.. " + listFolderFields.size());
        }
        String folderId = Constants.ROOT_TESTSET_FOLDER_PARENT_ID;
        for (String indivisualTestSetFolder : indivisualTestSetFolderArr) {
            folderId = getFolderId(indivisualTestSetFolder, folderId);
            if (null == folderId) {
                throw new AutomicException("No result found! Possible resaon may be specified test set folder path "
                        + testSetFolderPath + " is invalid.");
            }
        }
        testSetFolderId = folderId;
        LOGGER.info("Test set " + testSetName + " has folder id ::" + testSetFolderId);

        LOGGER.info("Executing of getTestSetFolderId() ends..");
        return testSetFolderId;
    }

    private String getFolderId(String folderName, String parentId) {
        LOGGER.info("Executing of getTestSetFolderId() starts..");
        String folderId = null;
        for (TestsetFolderFields testSetFolderFields : listFolderFields) {
            if (folderName.equalsIgnoreCase(testSetFolderFields.getFolderName())
                    && parentId.equals(testSetFolderFields.getParentId())) {
                folderId = testSetFolderFields.getFolderId();
                break;
            }
        }
        return folderId;
    }
}
