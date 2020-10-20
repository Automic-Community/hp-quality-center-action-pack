/**
 * 
 */
package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
@XmlRootElement(name = "LockStatusEntity")
public class LockStatusEntity {

    private String lockStatus;
    private Boolean lockedByMe;
    private String lockUser;

    public String getLockStatus() {
        return lockStatus;
    }

    @XmlElement(name = "LockStatus")
    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Boolean isLockedByMe() {
        return lockedByMe;
    }

    @XmlElement(name = "LockedByMe")
    public void setLockedByMe(Boolean lockedByMe) {
        this.lockedByMe = lockedByMe;
    }

    public String getLockUser() {
        return lockUser;
    }

    @XmlElement(name = "LockUser")
    public void setLockUser(String lockUser) {
        this.lockUser = lockUser;
    }

}
