/**
 * 
 */
package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 * 
 */

@XmlRootElement(name = "QCRestException")
public class QCRestError {

    private String id;

    private String title;

    private ExceptionProperties exceptionProperties;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    @XmlElement(name = "Id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    @XmlElement(name = "Title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the exceptionProperties
     */
    public ExceptionProperties getExceptionProperties() {
        return exceptionProperties;
    }

    /**
     * @param exceptionProperties
     *            the exceptionProperties to set
     */
    @XmlElement(name = "ExceptionProperties")
    public void setExceptionProperties(ExceptionProperties exceptionProperties) {
        this.exceptionProperties = exceptionProperties;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QCRestException [id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", exceptionProperties=");
        builder.append(exceptionProperties);
        builder.append("]");
        return builder.toString();
    }

}
