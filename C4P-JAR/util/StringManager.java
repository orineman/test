package com.infowave.c4p.util;

/**
 * Title:        String Manager <p>

 * @author       Vincent Aumont
 * @version      $Revision: 1.2 $
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Hashtable;
import java.util.Locale;
import java.text.MessageFormat;
import java.io.UnsupportedEncodingException;


/**
 * A strings manager wrapper for handling localization.
 *
 *
 * <p>The strings should be entered in a file called
 * <code>*.properties</code>, stored in the package.
 *
 */
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
		   " is nor defined");
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

  /**
   * Return a string from the bundle, and do a printf
   * using the arguments passed in.
   *
   * @param key - the message key.
   * @param args - an array of argument for teh printf operation.
   */
  public String getString(String key, Object[] args)
  {
    String str = null;
    String format = this.getString(key);
    try {
      str = MessageFormat.format(format, args);
    }
    catch (IllegalArgumentException e) {
      StringBuffer s = new StringBuffer();
      s.append(str);
      for (int i = 0; i < args.length; i++) {
        s.append("args[").append(i).append("]=").append(args[i]);
      }
      m_log.warn(s.toString());
      str = key;
    }
    return str;
  }

  /**
   * Returns a message corresponding to a key and performs a printf with the
   * argument.<p>
   *
   * @param key - the message key.
   * @param arg - the message argument for the printf operation
   */
  public String getString(String key, Object arg)
  {
    Object[] args = new Object[] {
      arg
    };
    return this.getString(key, args);
  }

  /**
   * Returns a message corresponding to a key and performs a printf with the
   * arguments provided in parameters.<p>
   *
   * @param key - the message key.
   * @param arg1 - the first message argument for the printf operation
   * @param arg2 - the second message argument for the printf operation
   */
  public String getString(String key, Object arg1, Object arg2)
  {
    Object[] args = new Object[] {
      arg1, arg2
    };
    return this.getString(key, args);
  }

  /**
   * Returns a message corresponding to a key and performs a printf with the
   * arguments provided in parameters.<p>
   *
   * @param key - the message key.
   * @param arg1 - the first message argument for the printf operation
   * @param arg2 - the second message argument for the printf operation
   * @param arg3 - the third message argument for the printf operation
   */
  public String getString(String key, Object arg1, Object arg2, Object arg3)
  {
    Object[] args = new Object[] {
      arg1, arg2, arg3
    };
    return this.getString(key, args);
  }

  /**
   * Returns a message corresponding to a key and performs a printf with the
   * arguments provided in parameters.<p>
   *
   * @param key - the message key.
   * @param arg1 - the first message argument for the printf operation
   * @param arg2 - the second message argument for the printf operation
   * @param arg3 - the third message argument for the printf operation
   * @param arg4 - the fourth message argument for the printf operation
   */
  public String getString(String key, Object arg1, Object arg2, Object arg3,
                          Object arg4)
  {
    Object[] args = new Object[] {
      arg1, arg2, arg3, arg4
    };
    return this.getString(key, args);
  }

  /**
   * Returns a message corresponding to a key and performs a printf with the
   * arguments provided in parameters.<p>
   *
   * @param key - the message key.
   * @param arg1 - the first message argument for the printf operation
   * @param arg2 - the second message argument for the printf operation
   * @param arg3 - the third message argument for the printf operation
   * @param arg4 - the fourth message argument for the printf operation
   * @param arg5 - the fifth message argument for the printf operation
   */
  public String getString(String key, Object arg1, Object arg2, Object arg3,
                          Object arg4, Object arg5)
  {
    Object[] args = new Object[] {
      arg1, arg2, arg3, arg4, arg5
    };
    return this.getString(key, args);
  }

  /**
   * test driver
   */
  public static void main(String[] args)
  {
    String appRes = "laplink";
    String key1 = "message.copyright";
    key1= "mail.newAccountBody";
    String msg;
    StringManager mgr = StringManager.getManager(appRes);
    StringManager mgr_fr = StringManager.getManager(appRes,
                                                    Locale.CANADA_FRENCH);
    //StringManager mgr_foo = StringManager.getManager(appRes,
    //                                               new Locale("foo","bar"));

    msg = mgr.getString(key1);
    System.out.println(msg);

    msg = mgr_fr.getString(key1);
    System.out.println(msg);
  }

  public static String htmlencode( String item )
  {
     	String retVal = item.trim();
	try {
		byte[] item_b = item.getBytes( "8859_1" );
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
		byte[] item_b = item.getBytes( "8859_1" );
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

}
