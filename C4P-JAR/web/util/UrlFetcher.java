package com.infowave.c4p.web.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpRecoverableException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Iterator;
import java.net.URL;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class UrlFetcher
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(UrlFetcher.class);
  private String m_url = null;
  private Map m_headers = null;

  public UrlFetcher (URL url){
    m_url = url.toExternalForm();
  }
  public UrlFetcher (URL url,Map headers){
    m_url = url.toExternalForm();
    m_headers = headers;
  }

  public UrlFetcher (String url){
    m_url = url;
  }
  public UrlFetcher (String url,Map headers){
    m_url = url;
    m_headers = headers;
  }


  /**
   * Returns the url's content's as an array of bytes
   *
   * @return the url's content's as an array of bytes
   */
  public byte[] getBytes()
  {
    HttpMethod method = getGetMethod();
    HttpClient client = new HttpClient();

    int statusCode = -1;
    byte[] responseBody = null;
    try {
      statusCode = client.executeMethod(method);
      responseBody = method.getResponseBody();
    } catch (HttpRecoverableException e) {
      m_log.debug("A recoverable exception occurred. " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      m_log.debug("Failed to fetch url.", e);
      e.printStackTrace();
    } finally {
      // Release the connection.
      if (method != null) {
        method.releaseConnection();
      }
    }

    return responseBody;
  }
// ------------------------------------------------------------ Helper methods
  private HttpMethod getGetMethod()
  {
    HttpMethod method = new GetMethod(m_url);
    if (m_headers != null){
      for (Iterator it= m_headers.keySet().iterator();it.hasNext();) {
        String header = (String) it.next();
        method.setRequestHeader(header,(String)m_headers.get(header));
      }
    }
    return method;
  }
// ---------------------------------------------------------------- Test

  private static void testBasicAuth() throws Exception
  {
    String url = "https://files.laplink.com/L/C:?did=khPdprqn";
    url = "https://files.laplink.com/lsfiles/index.jsp?did=5Ai4KasZ";
    String username = "c4test";
    String password = "ww";
    String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);
    HttpClient client = new HttpClient();
    HttpMethod method = new GetMethod(url);
    method.setRequestHeader("Authorization",authorization);
    int statusCode = -1;
    byte[] responseBody = null;
    try {
      statusCode = client.executeMethod(method);
      responseBody = method.getResponseBody();
      Header[] headers = method.getResponseHeaders();
      if (headers != null) {
        for (int i=0;i<headers.length;i++) {
          System.out.println(headers[i].getName() +"=" + headers[i].getValue());
        }
      } else {
        System.out.println("no headers");
      }
      if (responseBody != null) {
        System.out.println("ret code: " + statusCode);
        System.out.println("got " + new String(responseBody));
      }
    } catch (HttpRecoverableException e) {
      m_log.debug("A recoverable exception occurred. " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      m_log.debug("Failed to fetch url.", e);
      e.printStackTrace();
    } finally {
      // Release the connection.
      if (method != null) {
        method.releaseConnection();
      }
    }

  }
  private static void testGetBytes() throws Exception
  {
    String url = "http://www.infowave.com/images/logo_infowave.gif";
    url = "https://my-dev.laplink.com/html";
    //url = "http://www.infowave.com/images/title_info.gif ";
    UrlFetcher fetcher = new UrlFetcher(url);
    byte[] b = fetcher.getBytes();
    if (b != null){
      String res = new String(b);
      System.out.println("got " + b.length + " bytes");
      System.out.println("got " + res);
    } else {
      System.out.println("0 bytes retrieves");
    }
  }
  public static void main(String[] args) {
    byte[] bytes = new byte[12323];
    System.out.println(bytes.getClass().getName());
    try {
     //testGetBytes();
      testBasicAuth();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
