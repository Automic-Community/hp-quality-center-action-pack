package com.automic.hpqc.model;

/**
 * This class holds all the data which is required to trigger the test set execution. 
 *
 */
public class QTPTestSetModel {

    private String baseUrl;
    private String domain;
    private String project;
    private String testSetFolderPath;
    private String testSetName;
    private String hostIPAddress;
    private String username;
    private String password;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTestSetFolderPath() {
        return testSetFolderPath;
    }

    public void setTestSetFolderPath(String testSetFolderPath) {
        this.testSetFolderPath = testSetFolderPath;
    }

    public String getTestSetName() {
        return testSetName;
    }

    public void setTestSetName(String testSetName) {
        this.testSetName = testSetName;
    }

    public String getHostIPAddress() {
        return hostIPAddress;
    }

    public void setHostIPAddress(String hostIPAddress) {
        this.hostIPAddress = hostIPAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
