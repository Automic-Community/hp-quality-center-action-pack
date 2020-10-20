package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 * 
 */
/**
 * Credentials class will encapsulate the user data that needs to be send to HPQC for authentication
 * 
 */
@XmlRootElement(name = "alm-authentication")
public class Credentials {

    private String user;
    private String password;

    public Credentials(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    public Credentials() {
        super();
    }

    public String getUser() {
        return user;
    }

    @XmlElement(name = "user")
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }

}
