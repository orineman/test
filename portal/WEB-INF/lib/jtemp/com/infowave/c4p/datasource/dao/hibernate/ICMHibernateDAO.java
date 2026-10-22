package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.ICMDAO;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.ICM;

import java.util.List;
import java.util.Iterator;

/**
 * Concrete ICM DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ICMHibernateDAO extends AbstractDAO implements ICMDAO

{
  private static final String GET_ALL_QUERY = "ICM.all";
  private static final String GET_BY_NAME_QUERY = "ICM.by.name";
  private static final String NAME_ATTRIBUTE = "name";

    /**
   * Gets an ICM given its name.
   *
   * @param name - the ICM's name
   * @return The ICM with that name or null if this name does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
    public ICM getByUsername(String name) throws DataException
    {
      ICM icm = null;
      icm = (ICM) super.getSingleInstance(GET_BY_NAME_QUERY,
                                                NAME_ATTRIBUTE,
                                                name);
      return icm;

    }

  /**
   * Returns all ICMs.
   * @return all the ICMs presents in the system.
   * @throws DataException
   */
  public ICM[] getAll() throws DataException
  {
    ICM[] icms = null;
    List results;
    results = super.execute(GET_ALL_QUERY);
    icms = new ICM[results.size()];
    for (int i=0;i<results.size();i++)
    {
      icms[i] = (ICM)results.get(i);
    }
    return icms;
  }

  /**
   * Update -or create- an ICM.
   * @param icm - the ICM to persist or update.
   * @throws DataException
   */
  public void update(ICM icm) throws DataException
  {
    super.save(icm);
  }

// ------------------------------------------------------- Test methods
  private static void testGetAll() throws Exception
  {
    ICMDAO dao = new ICMHibernateDAO();
    ICM[] icms = dao.getAll();
    System.out.println("ICMS:");
    for (int i=0;i<icms.length;i++){
      System.out.println(icms[i]);
    }
  }
public  static void main (String[] args)
{
  try {
    testGetAll();
  }
  catch(Exception e){e.printStackTrace();}
}

}
