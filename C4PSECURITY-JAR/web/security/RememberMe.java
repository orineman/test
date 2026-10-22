package com.infowave.c4p.web.security;

import javax.crypto.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

    public RememberMe() {
        algo = new EncryptAlgo();
    }

    public void rememberMe(HttpServletRequest request, HttpServletResponse response, String username, String password) throws InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        Cookie c = generateCookie(username, USERNAME_COOKIE);
        if (c != null) {
            response.addCookie(c);
        }

        c = generateCookie(password, PASSWORD_COOKIE);
        if (c != null) {
            response.addCookie(c);
        }
    }

    public Cookie generateCookie(String string, String cookieName) throws InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        byte[] textBytes = string.getBytes();
        byte[] seed = SecureRandom.getSeed(64);
        try {
            SecretKey kp = algo.genKey(seed);

            byte[] enc = algo.encrypt(textBytes, kp);
            byte[] finalEnc = new byte[seed.length + enc.length];
            System.arraycopy(seed, 0, finalEnc, 0, seed.length);
            System.arraycopy(enc, 0, finalEnc, seed.length, enc.length);
            String encStr = URLEncoder.encode(new String(finalEnc, "ISO-8859-1"), "UTF-8");
            Cookie cookie = new Cookie(cookieName, encStr);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");
            return cookie;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (ShortBufferException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
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

    public UserInfo recognizeMe(HttpServletRequest request, HttpServletResponse response) throws IllegalBlockSizeException, InvalidKeyException, ShortBufferException, NoSuchPaddingException, BadPaddingException {
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

    public String recognizeCookie(Cookie cookie) throws IllegalBlockSizeException, InvalidKeyException, ShortBufferException, NoSuchPaddingException, BadPaddingException {
        String value = cookie.getValue();
        try {
            byte[] bsEnc = URLDecoder.decode(value, "UTF-8").getBytes("ISO-8859-1");

            byte[] bsSeed = new byte[64];
            System.arraycopy(bsEnc, 0, bsSeed, 0, 64);
            byte[] bsData = new byte[bsEnc.length - 64];
            System.arraycopy(bsEnc, 64, bsData, 0, bsEnc.length - 64);

            SecretKey hexKP = algo.genKey(bsSeed);

            byte[] res = algo.decrypt(bsData, hexKP);
            return new String(res);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}
