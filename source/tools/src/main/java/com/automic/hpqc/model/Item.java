/**
 * 
 */
package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
@XmlRootElement(name = "Item")
public class Item {

    private String value;
    private String logicalName;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the logicalName
     */
    public String getLogicalName() {
        return logicalName;
    }

    /**
     * @param value
     *            the value to set
     */
    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param logicalName
     *            the logicalName to set
     */
    @XmlAttribute
    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Item [value=");
        builder.append(value);
        builder.append(", logicalName=");
        builder.append(logicalName);
        builder.append("]");
        return builder.toString();
    }

}
