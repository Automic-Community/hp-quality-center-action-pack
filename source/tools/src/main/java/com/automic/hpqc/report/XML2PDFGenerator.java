package com.automic.hpqc.report;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.exception.AutomicException;
/**
 * This class is used to generate pdf from xml
 *
 */
public final class XML2PDFGenerator {

    private static final Logger LOGGER = LogManager.getLogger(XML2PDFGenerator.class);

    private XML2PDFGenerator() {
    }

    public static void generatePDF(File inputXmlFile, File pdffile, String xsltfilepath) throws AutomicException {

        LOGGER.info("Generating Pdf ... " + " inputFilePath " + inputXmlFile.getPath() + " pdfPath " + pdffile.getPath()
                + " xsltfilepath " + xsltfilepath);

        // OutputStream out = null;
        InputStream xsltStream = null;
        boolean flag = true;
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(pdffile))) {

            // configure fopFactory as desired
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.getEventBroadcaster().addEventListener(new LoggingEventListener());
            // configure foUserAgent as desired

            xsltStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xsltfilepath);

            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltStream));

            // Setup input for XSLT transformation
            Source src = new StreamSource(inputXmlFile);

            // Resulting SAX events (the generated FO) must be piped through
            // to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
            flag = false;
        } catch (TransformerException | FOPException | IOException ex) {
            // TBD log the exception
            throw new AutomicException("General Error.");
        } finally {
            if (xsltStream != null) {
                try {
                    xsltStream.close();
                } catch (IOException ex) {
                    LOGGER.error("Error occured while closing xslt stream ", ex);
                }
            }

            if (flag && !pdffile.delete()) {
                LOGGER.error("Error deleting file " + pdffile);

            }
        }
    }

}
