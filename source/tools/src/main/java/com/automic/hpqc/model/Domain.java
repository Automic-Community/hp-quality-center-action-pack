/**
 * 
 */
package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 * 
 */
@XmlRootElement(name = "Domain")
public class Domain {

    private String name;
    private Projects projects;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    @XmlAttribute(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the projects
     */
    public Projects getProjects() {
        return projects;
    }

    /**
     * @param projects
     *            the projects to set
     */
    @XmlElement(name = "Projects")
    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Domain [name=");
        builder.append(name);
        builder.append(", projects=");
        builder.append(projects);
        builder.append("]");
        return builder.toString();
    }

}
