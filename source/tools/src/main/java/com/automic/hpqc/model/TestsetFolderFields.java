package com.automic.hpqc.model;

/**
 * This class holds the data for Testset Folder fields.
 *
 */
public class TestsetFolderFields {

    String folderId;
    String parentId;
    String folderName;

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

}
