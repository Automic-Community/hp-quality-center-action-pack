package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents java object corresponding to HPQC Relation entity.
 *
 */
@XmlRootElement(name = "Relation")
public class Relation {
    @XmlAttribute(name = "Alias")
    private String alias;
    @XmlElement(name = "Entity")
    private Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public String getAlias() {
        return alias;
    }

}
