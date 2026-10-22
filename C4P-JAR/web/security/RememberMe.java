package com.infowave.c4p.web.security;

import javax.crypto.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.security.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 30.10.2007
 * Time: 15:03:37
 * To change this template use File | Settings | File Templates.
 */
public class RememberMe {
    public static final String USERNAME_COOKIE = "enUSR";
    public static final String PASSWORD_COOKIE = "enPS";

    private EncryptAlgo algo;

    /**
     * Constructor RememberMe creates a new RememberMe instance.
     * @throws NoSuchProviderException when
     * @throws NoSuchAlgorithmException when
     * @throws NoSuchPaddingException when
     */
    public RememberMe() throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException {
        algo = new EncryptAlgo();
    }

    /**
     * Method rememberMe ...
     *
     * @param request of type HttpServletRequest
     * @param response of type HttpServletResponse
     * @param username of type String
     * @param password of type String
     * @throws InvalidKeyException when
     * @throws NoSuchPaddingException when
     * @throws BadPaddingException when
     * @throws NoSuchProviderException when
     * @throws InvalidAlgorithmParameterException when
     * @throws IllegalBlockSizeException when
     * @throws ShortBufferException when
     */
    public void rememberMe(HttpServletRequest request, HttpServletResponse response, String username, String password) throws InvalidKeyException, NoSuchPaddingException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException, IllegalBlockSizeException, ShortBufferException {
        Cookie c = generateCookie(username, USERNAME_COOKIE);
        if (c != null) {
            response.addCookie(c);
        }

        c = generateCookie(password, PASSWORD_COOKIE);
        if (c != null) {
            response.addCookie(c);
        }
    }

    /**
     * Method generateCookie ...
     *
     * @param string of type String
     * @param cookieName of type String
     * @return Cookie
     * @throws InvalidKeyException when
     * @throws NoSuchPaddingException when
     * @throws BadPaddingException when
     * @throws NoSuchProviderException when
     * @throws InvalidAlgorithmParameterException when
     * @throws IllegalBlockSizeException when
     * @throws ShortBufferException when
     */
    public Cookie generateCookie(String string, String cookieName) throws InvalidKeyException, NoSuchPaddingException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException, IllegalBlockSizeException, ShortBufferException {
            String encString = algo.encryptCookieString(string);
            Cookie cookie = new Cookie(cookieName, encString);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");
            return cookie;
    }

    public class UserInfo {
        private String username;
        private String password;

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
    }

    /**
     * Method recognizeMe ...
     *
     * @param request of type HttpServletRequest
     * @param response of type HttpServletResponse
     * @return UserInfo
     * @throws IllegalBlockSizeException when
     * @throws InvalidKeyException when
     * @throws ShortBufferException when
     * @throws NoSuchPaddingException when
     * @throws BadPaddingException when
     * @throws NoSuchProviderException when
     * @throws InvalidAlgorithmParameterException when
     */
    public UserInfo recognizeMe(HttpServletRequest request, HttpServletResponse response) throws IllegalBlockSizeException, InvalidKeyException, ShortBufferException, NoSuchPaddingException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException {
        UserInfo user = new UserInfo();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase(USERNAME_COOKIE)) {
                    String username = recognizeCookie(cookies[i]);
                    if (username != null) {
                        user.setUsername(username);
                    } else {
                        return user;
                    }
                }

                if (cookies[i].getName().equalsIgnoreCase(PASSWORD_COOKIE)) {
                    String password = recognizeCookie(cookies[i]);
                    if (password != null) {
                        user.setPassword(password);
                    } else {
                        return user;
                    }
                }
            }
        }
        return user;
    }

    /**
     * Method recognizeCookie ...
     *
     * @param cookie of type Cookie
     * @return String
     * @throws IllegalBlockSizeException when
     * @throws InvalidKeyException when
     * @throws ShortBufferException when
     * @throws NoSuchPaddingException when
     * @throws BadPaddingException when
     * @throws NoSuchProviderException when
     * @throws InvalidAlgorithmParameterException when
     */
    public String recognizeCookie(Cookie cookie) throws IllegalBlockSizeException, InvalidKeyException, ShortBufferException, NoSuchPaddingException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException {
        String value = cookie.getValue();
        return algo.decryptCookieString(value);
    }
}
