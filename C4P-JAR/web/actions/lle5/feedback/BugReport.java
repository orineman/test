package com.infowave.c4p.web.actions.lle5.feedback;

import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.commons.ClientCapabilities;
import com.infowave.c4p.web.util.BrowserInfo;

import java.io.InputStream;

public class BugReport {
    protected UserDTO user;

    protected DesktopDTO desktop;

    protected HostReport hostReport;

    protected byte[] hostLogFile;

    protected String userComment;

    protected ClientCapabilities clientCapabilities;

    protected BrowserInfo browserInfo;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public DesktopDTO getDesktop() {
        return desktop;
    }

    public void setDesktop(DesktopDTO desktop) {
        this.desktop = desktop;
    }

    public HostReport getHostReport() {
        return hostReport;
    }

    public void setHostReport(HostReport hostReport) {
        this.hostReport = hostReport;
    }

    public byte[] getHostLogFile() {
        return hostLogFile;
    }

    public void setHostLogFile(byte[] hostLogFile) {
        this.hostLogFile = hostLogFile;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public BrowserInfo getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(BrowserInfo browserInfo) {
        this.browserInfo = browserInfo;
    }

    public ClientCapabilities getClientCapabilities() {
        return clientCapabilities;
    }

    public void setClientCapabilities(ClientCapabilities clientCapabilities) {
        this.clientCapabilities = clientCapabilities;
    }
}
