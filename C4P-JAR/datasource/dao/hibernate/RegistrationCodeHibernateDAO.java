package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.model.RegistrationCode;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.RegistrationCodeDAO;

/**
 * Concrete Reg Code DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class RegistrationCodeHibernateDAO extends AbstractDAO implements RegistrationCodeDAO
{
  private static final String GET_BY_CODE_QUERY = "RegistrationCode.by.code";
  private static final String REG_CODE_ATTRIBUTE = "regCode";

  /**
 * Returns a RegistrationCode given its code.
 *
 * @param code - the code we want to retrieve
 * @return The RegistrationCode bean corresponding to that code.
 * @throws com.infowave.c4p.datasource.DataException
 */
  public RegistrationCode getByCode(String code)
        throws DataException
  {
    RegistrationCode regCode;
    regCode = (RegistrationCode)super.getSingleInstance(GET_BY_CODE_QUERY,
            REG_CODE_ATTRIBUTE,code);
    return regCode;
  }

  /**
   * Save - or update- a RegistrationCode bean.
   * @param regCode - the bean to be saved.
   * @throws DataException
   */
  public void update(RegistrationCode regCode) throws DataException
  {
    super.save(regCode);
  }



}
