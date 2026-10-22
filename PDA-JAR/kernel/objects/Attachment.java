package com.infowave.pda.kernel.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 27.09.2007
 * Time: 17:05:18
 * To change this template use File | Settings | File Templates.
 */
public class Attachment {
    private String id;

    private String name;

    private String fullName;

    private boolean isLink;

    private boolean deleted;

    private String fileSize;

    private String fileType;

    private String fileAttribs;

    private boolean isEmail;
    private boolean isContact;
    private boolean isTask;
    private boolean isEvent;
    private boolean isNote;

    private String itemID;

    public Attachment() {
        
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean getLink() {
        return isLink;
    }

    public void setLink(boolean link) {
        isLink = link;
    }
    
    public boolean equals(Object o) {
        if (this.id!=null) {
            return this.id.equals(((Attachment)o).getId());
        } else if (this.fullName!=null) {
            return this.fullName.equals(((Attachment)o).getFullName());
        }
        return false;
    }

    public int hashCode() {
        return this.id.hashCode();           
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatedSize() {
        return com.infowave.pda.utils.files.FilesAndFoldersAttribs.getFormatedSize(fileSize);
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileAttribs() {
        return fileAttribs;
    }

    public void setFileAttribs(String fileAttribs) {
        this.fileAttribs = fileAttribs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isNote() {
        return isNote;
    }

    public void setNote(boolean note) {
        isNote = note;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public boolean isTask() {
        return isTask;
    }

    public void setTask(boolean task) {
        isTask = task;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
    }

    public boolean isEmail() {
        return isEmail;
    }

    public void setEmail(boolean email) {
        isEmail = email;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
}
