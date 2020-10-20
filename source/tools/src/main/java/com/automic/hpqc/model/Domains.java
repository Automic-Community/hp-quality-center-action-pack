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
@XmlRootElement(name = "Domains")
public class Domains {

    private ArrayList<Domain> domain;

    /**
     * @return the domain
     */
    public ArrayList<Domain> getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    @XmlElement(name = "Domain")
    public void setDomain(ArrayList<Domain> domain) {
        this.domain = domain;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Domains [domain=");
        builder.append(domain);
        builder.append("]");
        return builder.toString();
    }

}
