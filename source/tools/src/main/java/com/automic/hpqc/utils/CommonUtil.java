package com.automic.hpqc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.exception.AutomicRuntimeException;

/**
 * Common Utility class contains basic function(s) required by HPQC actions.
 * 
 */
public final class CommonUtil {

    private CommonUtil() {
    }

    /**
     * Method to format error message in the format "ERROR | message"
     * 
     * @param message
     * @return formatted message
     */
    public static String formatErrorMessage(final String message) {
        final StringBuilder sb = new StringBuilder();
        sb.append("ERROR").append(" | ").append(message);
        return sb.toString();
    }

    /**
     * 
     * Method to parse String containing numeric integer value. If string is not valid, then it returns the default
     * value as specified.
     * 
     * @param value
     * @param defaultValue
     * @return numeric value
     */
    public static int parseStringValue(final String value, int defaultValue) {
        int i = defaultValue;
        if (Validator.checkNotEmpty(value)) {
            try {
                i = Integer.parseInt(value);
            } catch (final NumberFormatException nfe) {
            }
        }
        return i;
    }

    /**
     * Utility to write specified content into specified file.
     * 
     * @param file
     * @param content
     * @throws IOException
     */
    public static void writeFile(File file, String content) throws IOException {
        try (FileWriter fr = new FileWriter(file)) {
            fr.write(content);
        }
    }

    /**
     * Utility to read file contents as string.
     * 
     * @param file
     * @return file content
     * @throws IOException
     */
    public static String readFileContents(File file) throws IOException {
        StringBuilder out = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                StandardCharsets.UTF_8))) {
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        }
        return out.toString();
    }

    /**
     * Utility to write java objects into XML format as specified by JAXB.
     * 
     * @param obj
     *            Object that needs to be serialized into XML format.
     * @param isFormatted
     *            true means XML file will be formatted.
     * @return XML Content corresponding to java object
     * @throws AutomicException
     */
    public static String object2Xml(Object obj, boolean isFormatted) throws AutomicException {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormatted);
            StringWriter stringWriter = new StringWriter();
            m.marshal(obj, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException ex) {
            throw new AutomicException(ExceptionConstants.SYSTEM_ERROR, ex);
        }
    }

    /**
     * Utility to write java objects into specified File in XML format as specified by JAXB.
     * 
     * @param obj
     *            Object that needs to be serialized into XML format.
     * @param isFormatted
     *            true means XML file will be formatted.
     * @param file
     *            File Object containing XML content.
     * @throws AutomicException
     */
    public static void object2Xml(Object obj, boolean isFormatted, File file) throws AutomicException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormatted);
            m.marshal(obj, fileWriter);
        } catch (JAXBException | IOException ex) {
            throw new AutomicException(ExceptionConstants.SYSTEM_ERROR, ex);
        }
    }

    /**
     * Utility to convert XML File content into java object corresponding to specified class.
     * 
     * @param c
     *            Object Class in which XML file will be converted.
     * @param file
     *            XML File Object
     * @return corresponding java object
     * @throws JAXBException
     */
    public static <T> T xml2object(Class<T> c, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object obj;
        try (InputStream is = new FileInputStream(file)) {
            obj = unmarshaller.unmarshal(is);
        } catch (IOException ex) {
            throw new AutomicRuntimeException(ExceptionConstants.SYSTEM_ERROR, ex);
        }
        return c.cast(obj);
    }

    /**
     * Utility to convert XML content into java object corresponding to specified class.
     * 
     * @param c
     *            Object Class in which XML content will be converted.
     * @param content
     *            XML content
     * @return corresponding java object
     * @throws JAXBException
     */
    public static <T> T xml2object(Class<T> c, String content) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(new StringReader(content));
        return c.cast(obj);
    }

    /**
     * Utility to get date or date time format represented by current system time stamp.
     * 
     * @param format
     *            date or date time format.
     * @return formatted date as specified by format.
     */
    public static String getFormattedDate(final String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

}
