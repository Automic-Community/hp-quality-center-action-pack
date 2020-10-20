/**
 * 
 */
package com.automic.hpqc.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.actions.service.ReadEntityService;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.utils.CommonUtil;


/**
 * ReadAction class implements the IUserAction interface and overrides its executeAction method for READ action.The
 * method will read the defect from HPQC based on the defect id. Throws exception if defect id not found which will
 * caught and logged
 *  @author sumitsamson
 */
public class ReadDefectAction extends AbstractHttpAction {

    private String domain;
    private String project;
    private Integer defectId;
    private File filePath;

    public ReadDefectAction() {
        addOption("domain", true, "Domain name");
        addOption("project", true, "project name");
        addOption("defectid", true, " Defect id");
        addOption("filepath", true, "File path");
    }

    /**
     * This method validate and initialize input *
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

        String temp = getOptionValue("filepath");
        HPQCValidator.checkFilePathNotEmpty(temp);
        filePath = new File(temp);
        HPQCValidator.checkFileDirectoryExists(filePath);

    }

    /**
     * @param action
     *            arguments
     * @throws AutomicException
     * 
     **/
    @Override
    public void executeSpecific() throws AutomicException {

        prepareInput();
        Entity entity = new ReadEntityService().readEntity(client, baseUrl, domain, project, "defects", defectId);
        String content;
        try {
            content = processXml(entity);
            CommonUtil.writeFile(filePath, content);
        } catch (JAXBException | ParserConfigurationException | SAXException | IOException
                | TransformerFactoryConfigurationError | TransformerException e) {
            throw new AutomicException(ExceptionConstants.GENERIC_ERROR_MSG);
        }

    }

    private String processXml(Entity entity) throws JAXBException, ParserConfigurationException, SAXException,
            IOException, TransformerFactoryConfigurationError, TransformerException, AutomicException {
        StringWriter out = new StringWriter();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new ByteArrayInputStream(CommonUtil.object2Xml(entity, false).getBytes()));

        NodeList fieldListNode = doc.getElementsByTagName("Field");
        for (int i = 0; i < fieldListNode.getLength(); i++) {
            Node node = fieldListNode.item(i);
            Element element = (Element) node;

            List<Node> nodesToDelete = new ArrayList<Node>();

            NodeList tmpNodeList = element.getChildNodes();
            for (int j = 0; j < tmpNodeList.getLength(); j++) {
                Node tmpNode = tmpNodeList.item(j);

                String nodeName = tmpNode.getNodeName();
                if (!"Value".equalsIgnoreCase(nodeName)) {
                    nodesToDelete.add(tmpNode);
                }
            }

            for (Node n : nodesToDelete) {
                element.removeChild(n);

            }

        }
        doc.normalize();
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        tf.setOutputProperty(OutputKeys.STANDALONE, "yes");
        tf.transform(new DOMSource(doc), new StreamResult(out));
        return out.toString();
    }

}
