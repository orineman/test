package com.infowave.c4p.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created for get information about client browser from Header User Agent
 * User: alexog
 * Date: 14.08.2007
 * Time: 17:16:26
 */
public class BrowserInfo {
    protected HttpServletRequest request;
    protected HttpSession session;
    protected int width;
    protected int height;
    protected String userAgent;
    protected String company;
    protected String name;
    protected String version;
    protected String mainVersion;
    protected String minorVersion;
    protected String os;
    protected String language = "de";
    protected Locale locale;

    private Hashtable<String, String> supportedLanguages;

    public BrowserInfo(HttpServletRequest request, HttpSession session) {
        this.initialize();
        this.request = request;
        this.session = session;

        this.setUserAgent(this.request.getHeader("User-Agent"));
        this.setCompany();
        this.setName();
        /*this.setVersion();
        this.setMainVersion();
        this.setMinorVersion();*/
        this.setOs();
        this.setLanguage();
        this.setLocale();
        this.setDimension();
    }

    public void initialize() {
        this.supportedLanguages = new Hashtable<String, String>(2);

        this.supportedLanguages.put("en", "");
        this.supportedLanguages.put("ru", "");
    }

    public void setUserAgent(String httpUserAgent) {
        this.userAgent = httpUserAgent.toLowerCase();
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    private void setCompany() {
        if (this.userAgent.indexOf("msie") > -1) {
            this.company = "Microsoft";
        } else if (this.userAgent.indexOf("opera") > -1) {
            this.company = "Opera Software";
        } else if (this.userAgent.indexOf("mozilla") > -1) {
            this.company = "Netscape Communications";
        } else {
            this.company = "unknown";
        }
    }

    public String getCompany() {
        return this.company;
    }

    private void setName() {
        if (this.company.equals("Microsoft")) {
            this.name = "Microsoft Internet Explorer";
        } else if (this.company.equals("Netscape Communications")) {
            this.name = "Mozilla";
        } else if (this.company.equals("Operasoftware")) {
            this.name = "Operasoftware Opera";
        } else if (this.company.equals("")){

        } else {
            this.name = "unknown";
        }
    }

    public String getName() {
        return this.name;
    }

    private void setVersion() {
        int tmpPos;
        String tmpString;

        if (this.company.equals("Microsoft")) {
            String str = this.userAgent.substring(this.userAgent.indexOf("msie") + 5);
            this.version = str.substring(0, str.indexOf(";"));
        } else {
/*            tmpString = (this.userAgent.substring(tmpPos = (this.userAgent.indexOf("/")) + 1,
                    tmpPos + this.userAgent.indexOf(" "))).trim();
            this.version = tmpString.substring(0, tmpString.indexOf(" "));*/
        }
    }

    public String getVersion() {
        return this.version;
    }

    private void setMainVersion() {
        this.mainVersion = this.version.substring(0, this.version.indexOf("."));
    }

    public String getMainVersion() {
        return this.mainVersion;
    }

    private void setMinorVersion() {
        this.minorVersion = this.version.substring(this.version.indexOf(".") + 1).trim();
    }

    public String getMinorVersion() {
        return this.minorVersion;
    }

    private void setOs() {
        if (this.userAgent.indexOf("win") > -1) {
            if (this.userAgent.indexOf("windows 95") > -1 ||
                    this.userAgent.indexOf("win95") > -1) {
                this.os = "Windows 95";
            }
            if (this.userAgent.indexOf("windows 98") > -1 ||
                    this.userAgent.indexOf("win98") > -1) {
                this.os = "Windows 98";
            }
            if (this.userAgent.indexOf("windows nt") > -1 ||
                    this.userAgent.indexOf("winnt") > -1) {
                this.os = "Windows NT";
            }
            if (this.userAgent.indexOf("win16") > -1 ||
                    this.userAgent.indexOf("windows 3.") > -1) {
                this.os = "Windows 3.x";
            }
        }
    }

    public String getOs() {
        return this.os;
    }

    private void setLanguage() {
        String prefLanguage = this.request.getHeader("Accept-Language");

        if (prefLanguage != null) {
            String language;
            StringTokenizer st = new StringTokenizer(prefLanguage, ",");

            int elements = st.countTokens();

            for (int idx = 0; idx<elements;idx++)
            {
                if (this.supportedLanguages.containsKey((language = st.nextToken()))) {
                    this.language = this.parseLocale(language);
                }
            }
        }
    }

    private String parseLocale(String language) {
        StringTokenizer st = new StringTokenizer(language, "-");

        if (st.countTokens() == 2) {
            return st.nextToken();
        } else {
            return language;
        }
    }

    private void setDimension() {
        if (request.getHeader("UA-pixels")!=null) {
            String header = request.getHeader("UA-pixels");
            String[] d = header.split("x");
            width = Integer.parseInt(d[0]);
            height = Integer.parseInt(d[1]);
        } else if ((userAgent.indexOf("iphone")!=-1)||(userAgent.indexOf("iPhone")!=-1)) {
            width = 968;
            height = 768;

        } else {
            Pattern pWidth = Pattern.compile("\\d\\d\\dx");
            Matcher mWidth = pWidth.matcher(userAgent);
            if (mWidth.find()) {
                width = Integer.parseInt(userAgent.substring(mWidth.start(),mWidth.end()-1));
            }

            Pattern pHeight = Pattern.compile("x\\d\\d\\d");
            Matcher mHeight = pHeight.matcher(userAgent);
            if (mHeight.find()) {
                height = Integer.parseInt(userAgent.substring(mHeight.start()+1,mHeight.end()));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLanguage() {
        return this.language;
    }

    private void setLocale() {
        this.locale = new Locale(this.language, "");
    }

    public Locale getLocale() {
        return this.locale;
    }

    public boolean isMozilla() {
        return this.getName().equals("Mozilla");
    }

    public boolean isSafari() {
        return (this.userAgent.indexOf("safari")!=-1)||(this.userAgent.indexOf("Safari")!=-1);
    }

    public boolean isOpera() {
        return (this.userAgent.indexOf("opera")!=-1)||(this.userAgent.indexOf("Opera")!=-1);
    }

    public boolean isIPhone() {
        return ((this.userAgent.toLowerCase()).indexOf("iphone"))!=-1;
    }

    public boolean isIE() {
        return ((this.userAgent.toLowerCase()).indexOf("msie"))!=-1;
    }
}
