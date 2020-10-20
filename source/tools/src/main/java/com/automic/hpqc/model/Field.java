package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
/**
 * Field class is used to map the response from HPQC
 *
 */
@XmlRootElement(name = "Field")
public class Field {

    private String physicalName;

    private String name;

    private String label;

    private String value;

    private Boolean isRequired;
    private String type;
    private Boolean isEditable;
    private Boolean isFilterable;
    private Integer listId;

    public String getValue() {
        return value;
    }

    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

    public String getPhysicalName() {
        return physicalName;
    }

    @XmlAttribute(name = "PhysicalName")
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    @XmlAttribute(name = "Label")
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the isRequired
     */
    public Boolean isRequired() {
        return isRequired;
    }

    /**
     * @param isRequired
     *            the isRequired to set
     */
    @XmlElement(name = "Required")
    public void setRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    @XmlElement(name = "Type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the isEditable
     */
    public Boolean isEditable() {
        return isEditable;
    }

    /**
     * @param isEditable
     *            the isEditable to set
     */
    @XmlElement(name = "Editable")
    public void setEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * @return the listId
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * @param listId
     *            the listId to set
     */
    @XmlElement(name = "List-Id")
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * @return the isFilterable
     */
    public Boolean isFilterable() {
        return isFilterable;
    }

    /**
     * @param isFilterable
     *            the isFilterable to set
     */
    @XmlElement(name = "Filterable")
    public void setFilterable(Boolean isFilterable) {
        this.isFilterable = isFilterable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Field [physicalName=");
        builder.append(physicalName);
        builder.append(", name=");
        builder.append(name);
        builder.append(", label=");
        builder.append(label);
        builder.append(", value=");
        builder.append(value);
        builder.append(", isRequired=");
        builder.append(isRequired);
        builder.append(", type=");
        builder.append(type);
        builder.append(", isEditable=");
        builder.append(isEditable);
        builder.append(", isFilterable=");
        builder.append(isFilterable);
        builder.append(", listId=");
        builder.append(listId);
        builder.append("]");
        return builder.toString();
    }

}
