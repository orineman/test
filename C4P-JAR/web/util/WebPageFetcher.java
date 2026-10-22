package com.infowave.c4p.web.util;

import java.io.*;
import java.net.*;

/**
* Fetches the HTML content of a web page as a String.
*/
public final class WebPageFetcher
{

  private URL m_url;
  private static final String HTTP_PROTOCOL = "http";
private static final String NEWLINE = System.getProperty("line.separator");

  public WebPageFetcher( URL url ){
    //if ( !url.getProtocol().equals(HTTP_PROTOCOL) ) {
    //  throw new IllegalArgumentException("URL is not for HTTP Protocol: " + url);
    //}
    m_url = url;
  }

  public WebPageFetcher( String url ) throws MalformedURLException {
    this ( new URL(url) );
  }

  /**
  * Fetch the HTML content of the page as simple text.
  */
  public String getPageContent() {
    StringBuffer result = new StringBuffer();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader( new InputStreamReader(m_url.openStream()) );
      String line = null;
      while ( (line = reader.readLine()) != null) {
        result.append(line);
        result.append(NEWLINE);
      }
    }
    catch ( IOException ex ) {
      System.err.println("Cannot retrieve contents of: " + m_url);
    }
    finally {
      shutdown( reader );
    }
    return result.toString();
  }

  /**
  * Fetch the HTML headers as simple text.
  */
  public String getPageHeader(){
    StringBuffer result = new StringBuffer();

    URLConnection connection = null;
    try {
      connection = m_url.openConnection();
    }
    catch (IOException ex) {
      System.err.println("Cannot open connection to URL: " + m_url);
    }

    //not all headers come in key-value pairs - sometimes the key is
    //null or an empty String
    int headerIdx = 0;
    String headerKey = null;
    String headerValue = null;
    while ( (headerValue = connection.getHeaderField(headerIdx)) != null ) {
      headerKey = connection.getHeaderFieldKey(headerIdx);
      if ( headerKey != null && headerKey.length()>0 ) {
        result.append( headerKey );
        result.append(" : ");
      }
      result.append( headerValue );
      result.append(NEWLINE);
      headerIdx++;
    }
    return result.toString();
  }


  private void shutdown ( Reader aReader ){
    try {
      if (aReader != null) aReader.close();
    }
    catch (IOException ex){
      System.err.println("Cannot close reader: " + aReader);
    }
  }


// --------------------------------------------------------------- Test methods
  public static void main(String[] aArguments) throws MalformedURLException
  {
    String url = "http://www.slashdot.org";
    url = "https://my-dev.laplink.com/html";
    WebPageFetcher fetcher = new  WebPageFetcher(url);
    System.out.println( fetcher.getPageContent() );
    System.out.println( fetcher.getPageHeader());

  }

  }

