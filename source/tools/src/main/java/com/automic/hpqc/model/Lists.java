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
@XmlRootElement(name = "Lists")
public class Lists {
    private ArrayList<com.automic.hpqc.model.List> list;

    /**
     * @return the list
     */
    public ArrayList<com.automic.hpqc.model.List> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    @XmlElement(name = "List")
    public void setList(ArrayList<com.automic.hpqc.model.List> list) {
        this.list = list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Lists [list=");
        builder.append(list);
        builder.append("]");
        return builder.toString();
    }

}
