/*
 * NewEmailForm.java
 *
 * Created on 24 Сентябрь 2007 г., 18:34
 */

package com.infowave.pda.forms.email;

import org.apache.struts.validator.DynaValidatorForm;
import static org.apache.commons.beanutils.BeanUtils.copyProperties;

import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import com.infowave.pda.kernel.objects.Attachment;
import com.infowave.pda.kernel.objects.errors.InvalidNumberOfParams;

/**
 *
 * @author Alexey.Ogarkov
 * @version
 */

public class NewEmailForm {
/*          <form-property initial="" name="to" type="java.lang.String"/>
            <form-property initial="" name="from" type="java.lang.String"/>
            <form-property initial="" name="body" type="java.lang.String"/>
            <form-property initial="" name="cc" type="java.lang.String"/>
            <form-property initial="" name="bcc" type="java.lang.String"/>
            <form-property initial="" name="attach" type="java.lang.String"/>
            <form-property initial="" name="subject" type="java.lang.String"/>
            <form-property initial="" name="action" type="java.lang.String"/>*/
    private String to;

    private String from;
    
    private String cc;

    private String bcc;

    private String subject;    

    private String attachFullName;
    
    private String attachAsLink;
    
    private String body;
/*    Fields    */

    private ArrayList<Attachment> attachList;

    public NewEmailForm(DynaValidatorForm form) throws IllegalAccessException, InvocationTargetException, InvalidNumberOfParams {
        copyProperties(this,form);
        this.initAttachList();
    }

    private void initAttachList() throws InvalidNumberOfParams {
        String[] attachFullNames = this.attachFullName.split(";");
        String[] attachAsLinks = this.attachAsLink.split(";");
        if (attachAsLinks.length==attachFullNames.length) {
            for (int i=0;i<attachAsLinks.length;i++) {
                
            }
        } else {
            throw new InvalidNumberOfParams("number of attachment FullNames and AsLink strings are not equal");
        }
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<Attachment> getAttachList() {
        return attachList;
    }

    public void setAttachList(ArrayList<Attachment> attachList) {
        this.attachList = attachList;
    }

    public String getAttachFullName() {
        return attachFullName;
    }

    public void setAttachFullName(String attachFullName) {
        this.attachFullName = attachFullName;
    }

    public String getAttachAsLink() {
        return attachAsLink;
    }

    public void setAttachAsLink(String attachAsLink) {
        this.attachAsLink = attachAsLink;
    }
}
