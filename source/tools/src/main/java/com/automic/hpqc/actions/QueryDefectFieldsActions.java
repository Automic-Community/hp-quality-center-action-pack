package com.automic.hpqc.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.EntityService;
import com.automic.hpqc.actions.service.HPQCUserService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.Lists;
import com.automic.hpqc.model.Users;
import com.automic.hpqc.utils.CommonUtil;

/**
 * 
 * @author shrutinambiar
 *
 *         This class will get the defect information and produce it in a XML format.
 */
public class QueryDefectFieldsActions extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(QueryDefectFieldsActions.class);
    private Map<Integer, List<String>> defectAssociatedListMap;
    private List<String> userList = new ArrayList<String>();
    private List<String> resourceList = new ArrayList<String>();
    private String loggedInUser;
    private String domain;
    private String project;
    private File filePath;

    public QueryDefectFieldsActions() {
        addOption("domain", true, "Domain of the user");
        addOption("project", true, "Project of the user");
        addOption("filepath", true, "File to savethe generated output");
    }

    /**
     * @return the userList
     */
    private List<String> getUserList() {
        return userList;
    }

    /**
     * @param userList
     *            the userList to set
     */
    private void setUserList(List<String> userList) {
        this.userList = userList;
    }

    /**
     * @param resourceList
     *            the resourceList to set
     */
    public void setResourceList(List<String> resourceList) {
        this.resourceList = resourceList;
    }

    private void prepareInputParameters() throws AutomicException {
        domain = getOptionValue("domain");
        HPQCValidator.checkNotEmpty(domain, "Domain");

        project = getOptionValue("project");
        HPQCValidator.checkNotEmpty(project, "Project");

        String temp = getOptionValue("filepath");
        HPQCValidator.checkNotEmpty(temp, "File Path");
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);
    }

    @Override
    public void executeSpecific() throws AutomicException {
        prepareInputParameters();
        HPQCUserService hpqcService = null;
        EntityService entityService = null;
        hpqcService = new HPQCUserService();
        entityService = new EntityService(client, baseUrl, domain, project);
        loggedInUser = hpqcService.getLoginUser(client, baseUrl);
        Users users = hpqcService.getUserList(baseUrl, domain, project, client);
        setUserList(users.getUserNameList());
        Lists lists = entityService.getListAssociatedWithEntity("defect");
        defectAssociatedListMap = entityService.getEntityListMap(lists);
        Fields fields = entityService.getFieldsForEntity("defect", false);
        resourceList = entityService.getResources();
        List<Field> optionalList = new ArrayList<Field>();
        List<Field> mandatory = new ArrayList<Field>();
        for (Field field : fields.getFieldList()) {
            field.setValue("");
            if (field.isRequired()) {
                mandatory.add(field);
            } else if (!"attachment".equals(field.getName())) {
                optionalList.add(field);
            }
        }
        Fields optionalFields = new Fields(optionalList);
        Fields mandotoryFields = new Fields(mandatory);
        String mandatoryXmlString = processXml(mandotoryFields, true);
        String optionalXmlString = processXml(optionalFields, false).replace("<Value/>", "<Value></Value>");
        mandatoryXmlString = mandatoryXmlString.replace("</Fields>", "").trim()
                + optionalXmlString.replace("<Fields>", "");
        try {
            CommonUtil.writeFile(filePath, mandatoryXmlString);
        } catch (IOException e) {
            throw new AutomicException(ExceptionConstants.SYSTEM_ERROR, e);
        }
    }

    private String processXml(Fields fields, boolean ismandatory) throws AutomicException {
        StringWriter out = new StringWriter();
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(CommonUtil.object2Xml(fields, false).getBytes()));
            if (!ismandatory) {
                Element element = doc.getDocumentElement();
                Comment comment = doc.createComment("      Optional fields starts here    ");
                element.getParentNode().insertBefore(comment, element);
            }
            // reading the xml and adding comments and removing attributes
            // 'Label' &
            // 'PhysicalName' and removing all Field elements except Value
            NodeList fieldListNode = doc.getElementsByTagName("Field");
            for (int i = 0; i < fieldListNode.getLength(); i++) {
                Node node = fieldListNode.item(i);
                Element element = (Element) node;
                List<Node> nodesToDelete = new ArrayList<Node>();
                NodeList tmpNodeList = element.getChildNodes();
                String mainNode = null;
                String type = null;
                int listId = -1;
                Node valueNode = null;
                for (int j = 0; j < tmpNodeList.getLength(); j++) {
                    Node tmpNode = tmpNodeList.item(j);
                    String nodeName = tmpNode.getNodeName();
                    mainNode = element.getAttributes().getNamedItem(Constants.ATTRIBUTE_LABEL).getNodeValue();
                    if (Constants.ELEMENT_VALUE.equalsIgnoreCase(nodeName)) {
                        valueNode = tmpNode;
                        continue;
                    }
                    if (Constants.ELEMENT_TYPE.equalsIgnoreCase(nodeName)) {
                        type = tmpNode.getChildNodes().item(0).getNodeValue();
                        nodesToDelete.add(tmpNode);
                    } else if (Constants.ELEMENT_LIST_ID.equalsIgnoreCase(nodeName)) {
                        String val = tmpNode.getChildNodes().item(0).getNodeValue();
                        listId = (val != null) ? (Integer.parseInt(val)) : listId;
                        nodesToDelete.add(tmpNode);
                    } else if (!Constants.ELEMENT_VALUE.equalsIgnoreCase(nodeName)) {
                        nodesToDelete.add(tmpNode);
                    }
                }
                String commentText = getCommentsForField(type, mainNode, listId, valueNode, ismandatory);
                if (!commentText.isEmpty()) {
                    Comment comment = doc.createComment(commentText);
                    element.getParentNode().insertBefore(comment, element);
                }
                for (Node n : nodesToDelete) {
                    element.removeChild(n);
                }
                element.removeAttribute(Constants.ATTRIBUTE_PHYSICAL_NAME);
            }
            doc.normalize();
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            tf.setOutputProperty(OutputKeys.STANDALONE, "yes");
            tf.transform(new DOMSource(doc), new StreamResult(out));
        } catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError
                | TransformerException e) {
            LOGGER.error("Error while processing xml  ", e);
            throw new AutomicException(ExceptionConstants.GENERIC_ERROR_MSG);
        }
        return out.toString();
    }

    private String getCommentsForField(String type, String nodeName, int listId, Node valueNode, boolean isMandatory)
            throws AutomicException {
        StringBuilder comment = new StringBuilder();
        switch (type) {
            case Constants.TYPE_STRING:
                comment.append("Enter text for ");
                comment.append("'" + nodeName + "'");
                if (isMandatory) {
                    valueNode.setTextContent("Text for " + nodeName);
                }
                break;
            case Constants.TYPE_USERLIST:
                comment.append("'");
                comment.append(nodeName);
                comment.append("' is a single-value list ,possible values :");
                comment.append(getUserList());
                if (isMandatory) {
                    valueNode.setTextContent(loggedInUser);
                }
                break;
            case Constants.TYPE_LOOKUPLIST:
                if (defectAssociatedListMap.get(listId) != null) {
                    comment.append("'");
                    comment.append(nodeName);
                    comment.append("' is a single-value list ,possible values : ");
                    comment.append(defectAssociatedListMap.get(listId));
                    if (isMandatory) {
                        valueNode.setTextContent(defectAssociatedListMap.get(listId).get(0));
                    }
                } else {
                    comment.append("Enter the valid HPQC entity ");
                }
                break;
            case Constants.TYPE_DATE:
                comment.append("Enter valid Date [yyyy-MM-dd] for ");
                comment.append("'" + nodeName + "'");
                if (isMandatory) {
                    valueNode.setTextContent(CommonUtil.getFormattedDate("yyyy-MM-dd"));
                }
                break;
            case Constants.TYPE_DATE_TIME:
                comment.append("Enter valid DateTime [yyyy-MM-dd HH:mm:ss] for ");
                comment.append("'" + nodeName + "'");
                if (isMandatory) {
                    valueNode.setTextContent(CommonUtil.getFormattedDate("yyyy_MM_dd_HH_mm_ss"));
                }
                break;
            case Constants.TYPE_REFERNCE:
                if (!resourceList.isEmpty()) {
                    comment.append("'");
                    comment.append(nodeName);
                    comment.append("' is a single-value list ,possible values : ");
                    comment.append(resourceList.toString());
                    if (isMandatory) {
                        valueNode.setTextContent(resourceList.get(0));
                    }
                } else {
                    comment.append("Enter the valid HPQC entity ");
                }
                break;
            case Constants.TYPE_MEMO:
                comment.append("Enter the valid HPQC entity ");
                break;
            case Constants.TYPE_NUMBER:
                comment.append("Enter valid number for '");
                comment.append(nodeName);
                comment.append("' ,do not use comma (,), any alphabetic character, white space, or any punctuation "
                        + "other than (.)");
                break;
            default:
                String msg = "Invalid type [ " + type + "] for Field " + nodeName;
                LOGGER.error(msg);
                throw new AutomicException(msg);
        }
        return comment.toString();
    }

}
