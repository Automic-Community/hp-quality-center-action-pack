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
@XmlRootElement(name = "Project")
public class Project {

    private String name;
    private Entities entites;
    private com.automic.hpqc.model.List priority;
    private com.automic.hpqc.model.List severity;
    private com.automic.hpqc.model.List bugStatus;
    private Users users;

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
     * @return the entites
     */
    public Entities getEntites() {
        return entites;
    }

    /**
     * @param entites
     *            the entites to set
     */
    @XmlElement(name = "Entities")
    public void setEntites(Entities entites) {
        this.entites = entites;
    }

    /**
     * @return the priority
     */
    public com.automic.hpqc.model.List getPriority() {
        return priority;
    }

    /**
     * @return the severity
     */
    public com.automic.hpqc.model.List getSeverity() {
        return severity;
    }

    /**
     * @return the bugStatus
     */
    public com.automic.hpqc.model.List getBugStatus() {
        return bugStatus;
    }

    /**
     * @param priority
     *            the priority to set
     */

    public void setPriority(com.automic.hpqc.model.List priority) {
        this.priority = priority;
    }

    /**
     * @param severity
     *            the severity to set
     */

    public void setSeverity(com.automic.hpqc.model.List severity) {
        this.severity = severity;
    }

    /**
     * @param bugStatus
     *            the bugStatus to set
     */

    public void setBugStatus(com.automic.hpqc.model.List bugStatus) {
        this.bugStatus = bugStatus;
    }

    /**
     * @return the users
     */
    public Users getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(Users users) {
        this.users = users;
    }

}
