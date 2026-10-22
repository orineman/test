package com.infowave.c4p.util.security;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This class is reponsible for building and caching password encoders.
 * A PasswordEncoderImpl is identified by its class name.
 *
 */
public class PasswordEncoderFactory
{
  /** the singleton */
   private static  PasswordEncoderFactory m_instance = null;

  /** The list of known encoders. The hey are the class names, the values are
   * the encoders.
   */
  private Map m_encoders = new HashMap();

  /**
   * Returns the class's singleton.
   */
  public static PasswordEncoderFactory instance()
  {
    if (m_instance == null){
      m_instance = new PasswordEncoderFactory();
    }
    return m_instance;
  }
  /**
   * Returns an encoder based on its class name.
   *
   * @param classname - the class ti instanciate.
   * @return the encoder.
   */
  public PasswordEncoder getEncoder(String classname)
  {
    PasswordEncoder encoder = null;
    if (m_encoders.containsKey(classname)) {
      encoder = (PasswordEncoder) m_encoders.get(classname);
    } else {
      try {
        Class theClass = Class.forName(classname);
        encoder = (PasswordEncoder) theClass.newInstance();
        synchronized(m_encoders) {
          if (!m_encoders.containsKey(classname)) {
          m_encoders.put(classname,encoder);
          }
        }
      }
      catch (Exception e) {
        throw new RuntimeException("Cannot instanciate PasswordEncoderImpl from : "+ classname);
      }
    }
    return encoder;
  }
}

