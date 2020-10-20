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
@XmlRootElement(name = "EntityResourceDescriptor")
public class EntityResourceDescriptor {

    private String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    @XmlAttribute(name = "collectionName")
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return "EntityResourceDescriptor [collectionName=" + collectionName + "]";
    }

}
