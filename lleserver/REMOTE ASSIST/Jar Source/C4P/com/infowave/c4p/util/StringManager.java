package com.infowave.c4p.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;

public class StringManager {


  /** The class's logger */
  private static Log m_log = LogFactory.getLog(StringManager.class);

  /** All the curently active managers */
  private static Hashtable managers = new Hashtable();

  /**  The resource bundle */
  private ResourceBundle m_bundle;

  /** The bundle's name */
  private String m_bundleName;

  /** The bundle's locale */
  private Locale m_locale;

  public static String webDecode( String item )
  {
	String retVal = item.trim();
	try {
		byte[] item_b = item.getBytes( "8859_1" );
		retVal = new String( item_b );
	} catch( UnsupportedEncodingException uee ) {}
	return retVal;
  }

  public static String htmlencode( String item )
  {
    String retVal = item.trim();
	try {
		byte[] item_b = item.getBytes( "utf-8" );
		byte[] retval_b = new byte[ item_b.length * 6 ];
		int j = 0;
		String charstr;
		for( int i=0; i<item_b.length; i++ ) {
		    if( item_b[i] > 0x7f ) {
			charstr = Integer.toString(item_b[i]);
			byte[] charstr_b = charstr.getBytes();
			retval_b[j++] = '#';
			retval_b[j++] = '&';
			for( int k=0; k<charstr_b.length; k++ ) {
			    retval_b[j++] = charstr_b[k];
			}
			retval_b[j++] = ';';
			} else {
			retval_b[j++] = item_b[i];
		    }
		}
		retVal = new String( retval_b, 0, j );
          } catch( UnsupportedEncodingException uee ) {}
	return retVal;
  }

  public static String linkencode( String item )
  {
     String retVal = item.trim();
	 try {
		//byte[] item_b = item.getBytes( "8859_1" );
		byte[] item_b = item.getBytes( "utf-8" );
		byte[] retval_b = new byte[ item_b.length * 3 ];
		int j = 0;
		String charstr;
		byte[] hex_b;
		for( int i=0; i<item_b.length; i++ ) {
		    if( Character.isLetterOrDigit( (char)item_b[i] ) ) {
			  retval_b[j++] = item_b[i];
			} else {
			  retval_b[j++] = '%';
			  charstr = Integer.toHexString(item_b[i]);
			  hex_b = charstr.getBytes();
			  if( hex_b.length == 1 ) {
				retval_b[j++] = '0';
			  } else {
				retval_b[j++] = hex_b[0];
			  }
			  retval_b[j++] = hex_b[1];
			}
		}
		retVal = new String( retval_b, 0, j );
     } catch( UnsupportedEncodingException uee ) {}
	 return retVal;
  }

  public static String linkdecode( String item )
  {
     String retVal = item.trim();
		byte[] item_b = item.getBytes( );
		byte[] retval_b = new byte[ item_b.length ];
		int j = 0;
		Integer byte_i;
		byte[] hexstr_b = new byte[2];
		String hexstr;
		for( int i=0; i<item_b.length; i++ ) {
			if( item_b[i] == '%' ) {
			  hexstr_b[0] = item_b[++i];
			  hexstr_b[1] = item_b[++i];
			  hexstr = new String( hexstr_b, 0, 2 );
			  byte_i = new Integer(Integer.parseInt(hexstr, 16));
			  retval_b[j++] = byte_i.byteValue();
			} else {
			  retval_b[j++] = item_b[i];
			}
		}
		retVal = new String( retval_b, 0, j );
	 return retVal;
  }

  /**
   * Returns a StringManager for a particular package in the JVM's locale. <p>
   *
   * Try to reuse an existing one, or create one if it does not exist.
   *
   * @param packageName - the package's name.
   */
  public synchronized static StringManager getManager(String packageName) {
    // check if it is already created
    StringManager mgr = (StringManager)managers.get(packageName);
    if (mgr == null) {
      mgr = new StringManager(packageName);
      managers.put(packageName, mgr);
    }
    return mgr;
  }

  /**
   * Returns a StringManager for a particular package in a given locale. <p>
   *
   * Try to reuse an existing one, or create one if it does not exist.
   *
   * @param packageName - the package's name.
   * @param locale - teh locale
   */
  public synchronized static StringManager getManager(String packageName,
                                                      Locale locale)
  {
    // check if it is already created
    StringManager mgr = (StringManager)managers.get(packageName + locale);
    if (mgr == null) {
      mgr = new StringManager(packageName,locale);
      managers.put(packageName + locale, mgr);
    }
    return mgr;
  }

  /**
   * Creates a StringManager for a given package name in the default locale.
   * First we tried a resource file called <packageName>.MessageStrings
   * If no file with that name exists, we try to simply use the package
   * name and let the ResourceBundle method figure out a nmae.
   *
   * @param packageName - the package's name.
   */
  private StringManager(String packageName)
  {
    m_bundleName = packageName + ".properties";
    m_locale = Locale.getDefault();
    try {
      m_bundle = ResourceBundle.getBundle(m_bundleName);
    }
    catch (Exception e) {m_bundle = null;}

    if (m_bundle == null) {
      m_bundleName = packageName;
      try {
	m_bundle = ResourceBundle.getBundle(m_bundleName);
      }
      catch (Exception e) {m_bundle = null;}
    }
    if (m_bundle == null) {
      m_log.warn("Cannot get bundle for: " + packageName);
    }
  }

  /**
   * Creates a StringManager for a given package name in a given locale.
   * First we tried a resource file called <packageName>.MessageStrings
   * If no file with that name exists, we try to simply use the package
   * name and let the ResourceBundle method figure out a nmae.
   *
   * @param packageName - the package's name.
   * @param locale - the locale
   */
  private StringManager(String packageName, Locale locale)
  {
    m_bundleName = packageName + ".properties";
    m_locale = locale;
    try {
      m_bundle = ResourceBundle.getBundle(m_bundleName,locale);
    }
    catch (Exception e) {m_bundle = null;}

    if (m_bundle == null) {
      m_bundleName = packageName;
      try {
	m_bundle = ResourceBundle.getBundle(m_bundleName,locale);
      }
      catch (Exception e) {m_bundle = null;}
    }
    if (m_bundle == null) {
      m_log.warn("Cannot get bundle for: " + packageName + " for locale " +
		 locale);
    }


  }

  /**
   * Get a particular string from the bundle.<p>
   *  This is a synonymous for ResourceBundle.getString
   *
   * @param key - the message key.
   */
  public String getString(String key) {
    String str = null;
    try {
      if (m_bundle == null) {
        m_log.warn("No message for key: " + key + " - Bundle " + m_bundleName +
		   " is not defined");
        str= key;
      }
      else {
        str = m_bundle.getString(key);
      }
    }
    catch (MissingResourceException e) {
      m_log.warn("No message for key: " + key + " in bundle " + m_bundleName);
      str = key;
    }
    //finally {
      return str;
    //}
  }

  public static final boolean isOkay( String data )
  {
    boolean isOkay = true;
	String datalc = data.toLowerCase();
	for( int i = 0; i<badwords.length; i++ ) {
		if( datalc.indexOf( badwords[i] ) != -1 ) {
			isOkay = false;
			break;
		}	
	}
	return isOkay;
  }

  private static final String[] badwords = {"fuck", "shit", "damn", "cunt", "hell", "asshole", "dick", "nigger"};
}
