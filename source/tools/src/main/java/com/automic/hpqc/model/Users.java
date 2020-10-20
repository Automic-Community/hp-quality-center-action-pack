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
@XmlRootElement(name = "Users")
public class Users {

    private ArrayList<User> userList;

    /**
     * @return the userList
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * @param userList
     *            the userList to set
     */
    @XmlElement(name = "User")
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public java.util.List<String> getUserNameList() {
        java.util.List<String> userNameList = new ArrayList<String>();
        for (User user : userList) {
            userNameList.add(user.getName());
        }
        return userNameList;
    }

    @Override
    public String toString() {
        return "Users [userList=" + userList + "]";
    }
}
