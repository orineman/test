package com.infowave.pda.kernel.objects;

import static java.net.URLEncoder.encode;
import java.util.Locale;
import java.io.UnsupportedEncodingException;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 16.03.2008
 * Time: 15:50:32
 * Paccage : com.infowave.pda.kernel.objects
 */
public class WeatherService {
        private boolean enabled;
        private String location;
        private Locale locale;
        private String url;

        public WeatherService() {
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getUrl() throws UnsupportedEncodingException {
            return "http://www.google.com/ig/api?weather="+ encode(location,"UTF-8")+"&hl="+locale.getLanguage();
        }

        public Locale getLocale() {
            return locale;
        }

        public void setLocale(Locale locale) {
            this.locale = locale;
        }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
