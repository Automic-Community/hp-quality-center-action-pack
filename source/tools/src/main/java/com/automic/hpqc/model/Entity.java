package com.automic.hpqc.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 * 
 */

/**
 * Entity class for mapping the HPQC response
 * 
 */

@XmlRootElement(name = "Entity")
public class Entity {

    @XmlAttribute(name = "Type")
    private String type;

    @XmlElement(name = "Fields")
    private Fields fields;

    @XmlElement(name = "RelatedEntities")
    private RelatedEntities relatedEntities;

    public Entity() {
        super();
    }

    public Entity(String type, Fields fields) {
        super();
        this.type = type;
        this.fields = fields;
    }

    public RelatedEntities getRelatedEntities() {
        return relatedEntities;
    }

    public String getType() {
        return type;
    }

    public Fields getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Entity [type=" + type + ", fields=" + fields + ", relatedEntities=" + relatedEntities + "]";
    }

    public Map<String, String> toMap() {

        Map<String, String> entityMap = new HashMap<String, String>();

        for (Field f : this.fields.getFieldList()) {
            entityMap.put(f.getName(), f.getValue());
        }

        return entityMap;

    }

}
