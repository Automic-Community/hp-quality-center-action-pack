package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents java binding for authentication information.
 *
 */

@XmlRootElement(name = "AuthenticationInfo")
public class AuthenticationInfo {

    private String username;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    @XmlElement(name = "Username")
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthenticationInfo [username=");
        builder.append(username);
        builder.append("]");
        return builder.toString();
    }

}
