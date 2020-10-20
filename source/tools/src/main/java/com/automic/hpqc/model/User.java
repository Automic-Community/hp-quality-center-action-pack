/**
 * 
 */
package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
@XmlRootElement(name = "User")
public class User {

    private String name;
    private String fullName;
    private String userAvatarCache;
    private String description;
    private String email;
    private String phone;
    private boolean userActive;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the userAvatarCache
     */
    public String getUserAvatarCache() {
        return userAvatarCache;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the userActive
     */
    public boolean isUserActive() {
        return userActive;
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
     * @param fullName
     *            the fullName to set
     */
    @XmlAttribute(name = "FullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", fullName=" + fullName + ", userAvatarCache=" + userAvatarCache
                + ", description=" + description + ", email=" + email + ", phone=" + phone + ", userActive="
                + userActive + "]";
    }

}
