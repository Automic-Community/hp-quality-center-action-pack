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
@XmlRootElement(name = "List")
public class List {
    private String name;
    private int id;
    private String logicalName;
    private Items items;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the logicalName
     */
    public String getLogicalName() {
        return logicalName;
    }

    /**
     * @return the items
     */
    public Items getItems() {
        return items;
    }

    /**
     * @param name
     *            the name to set
     */
    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param id
     *            the id to set
     */
    @XmlElement(name = "Id")
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param logicalName
     *            the logicalName to set
     */
    @XmlElement(name = "LogicalName")
    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    /**
     * @param items
     *            the items to set
     */
    @XmlElement(name = "Items")
    public void setItems(Items items) {
        this.items = items;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("List [name=");
        builder.append(name);
        builder.append(", id=");
        builder.append(id);
        builder.append(", logicalName=");
        builder.append(logicalName);
        builder.append(", items=");
        builder.append(items);
        builder.append("]");
        return builder.toString();
    }

}
