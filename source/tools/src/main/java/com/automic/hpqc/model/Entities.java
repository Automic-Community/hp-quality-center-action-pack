/**
 * 
 */
package com.automic.hpqc.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
@XmlRootElement(name = "Entities")
public class Entities {

    private int totalResults;
    private ArrayList<Entity> entity;

    /**
     * @return the totalResults
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults
     *            the totalResults to set
     */
    @XmlAttribute(name = "TotalResults")
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return the entity
     */
    public ArrayList<Entity> getEntity() {
        return entity;
    }

    /**
     * @param entity
     *            the entity to set
     */
    @XmlElement(name = "Entity")
    public void setEntity(ArrayList<Entity> entity) {
        this.entity = entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Entities [totalResults=");
        builder.append(totalResults);
        builder.append(", entity=");
        builder.append(entity);
        builder.append("]");
        return builder.toString();
    }

}
