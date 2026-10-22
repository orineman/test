package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.RegistrationCode;
import com.infowave.c4p.datasource.DataException;

/**
 * This interface defines the methods supported by Reg Code DAO implementions.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface RegistrationCodeDAO
{
/**
 * Returns a RegistrationCode given its code.
 *
 * @param code - the code we want to retrieve
 * @return The RegistrationCode bean corresponding to that code.
 * @throws DataException
 */
  public RegistrationCode getByCode(String code) throws DataException;

  /**
   * Save - or update- a RegistrationCode bean.
   * @param regCode - the bean to be saved.
   * @throws DataException
   */
  public void update(RegistrationCode regCode) throws DataException;

}
