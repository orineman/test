package com.infowave.c4p.web.actions.lle5.feedback;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alexogar
 * Date: Jul 29, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class HostReport implements Serializable {
        private String os;
        private String outlook;
        private String usercreditcard;
        private List   passwords;
        private FileInputStream file;

        public HostReport() {}

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getOutlook() {
            return outlook;
        }

        public void setOutlook(String outlook) {
            this.outlook = outlook;
        }

        public String getUser_credit_card() {
            return usercreditcard;
        }

        public void setUsercreditcard(String usercreditcard) {
            this.usercreditcard = usercreditcard;
        }

    public List getPasswords() {
        return passwords;
    }

    public void setPasswords(List passwords) {
        this.passwords = passwords;
    }

    public FileInputStream getFile() {
        return file;
    }

    public void setFile(FileInputStream file) {
        this.file = file;
    }
}
