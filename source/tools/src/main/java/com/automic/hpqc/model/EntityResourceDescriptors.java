/**
 * 
 */
package com.automic.hpqc.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
@XmlRootElement(name = "EntityResourceDescriptors")
public class EntityResourceDescriptors {

    private ArrayList<EntityResourceDescriptor> entityResourceDescriptor;

    @XmlElement(name = "EntityResourceDescriptor")
    public void setEntityResourceDescriptor(ArrayList<EntityResourceDescriptor> entityResourceDescriptor) {
        this.entityResourceDescriptor = entityResourceDescriptor;
    }

    public ArrayList<EntityResourceDescriptor> getEntityResourceDescriptor() {
        return entityResourceDescriptor;
    }

}
