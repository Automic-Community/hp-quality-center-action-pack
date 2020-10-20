/**
 * 
 */
package com.automic.hpqc.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author sumitsamson
 *
 */
@XmlRootElement(name = "Projects")
public class Projects {

    private List<Project> projects;
    private String domain;

    /**
     * @return the projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * @param projects
     *            the projects to set
     */
    @XmlElement(name = "Project")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    @XmlTransient
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Projects [projects=");
        builder.append(projects);
        builder.append("]");
        return builder.toString();
    }

}
