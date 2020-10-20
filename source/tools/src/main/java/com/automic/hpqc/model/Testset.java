package com.automic.hpqc.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents java object corresponding to HPQC Testset entity.
 *
 */
@XmlRootElement(name = "Testset")
public class Testset {

    private String folderPath;
    private String name;
    private ArrayList<Fields> testInstanceFieldsList;

    public ArrayList<Fields> getTestInstanceFieldsList() {
        return testInstanceFieldsList;
    }

    @XmlElement(name = "Fields")
    public void setTestInstanceFieldsList(ArrayList<Fields> testInstanceFieldsList) {
        this.testInstanceFieldsList = testInstanceFieldsList;
    }

    public String getFolderPath() {
        return folderPath;
    }

    @XmlElement(name = "FolderPath")
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

}
