package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents java object corresponding to HPQC Related Entities information.
 *
 */
@XmlRootElement(name = "RelatedEntities")
public class RelatedEntities {

    @XmlElement(name = "Relation")
    private Relation relation;

    public Relation getRelation() {
        return relation;
    }

}
