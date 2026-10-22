package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Reseller;


import java.util.List;
import java.util.Iterator;

/**
 * Concrete Reseller DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ResellerHibernateDAO extends AbstractDAO implements ResellerDAO
{

  private static final String GET_BY_CODE_QUERY = "Reseller.by.code";
  private static final String GET_ALL_QUERY = "Reseller.all";
  private static final String CODE_ATTRIBUTE = "code";



  public Reseller getByCode(String code)
          throws DataException
  {
    Reseller reseller = null;
    reseller = (Reseller) super.getSingleInstance(GET_BY_CODE_QUERY,
                                                  CODE_ATTRIBUTE,
                                                  code);
    return reseller;
  }

  public void update(Reseller reseller) throws DataException
  {
    super.save(reseller);
  }
  public Reseller[] getAll() throws DataException
  {
    Reseller[] resellers = null;
    List results;
    results = super.execute(GET_ALL_QUERY);
    resellers = new Reseller[results.size()];
    for (int i=0;i<results.size();i++)
    {
      resellers[i] = (Reseller)results.get(i);
    }
    return resellers;
  }
  // --------------------------------------------------------- Test
  public static void testGetAll() throws Exception
  {
   Reseller[] resellers;
    ResellerDAO dao = DAOFactory.getResellerDAO();
    resellers = dao.getAll();
    for (int i =0;i < resellers.length;i++) {
      System.out.println(resellers[i]);
    }


  }

  public static void testGetByCode() throws Exception
  {
    ResellerDAO dao = DAOFactory.getResellerDAO();
    Reseller reseller = dao.getByCode("res_0");
    System.out.println(reseller);
  }
/*
  public static void testCreate() throws Exception
  {
    AccountDAO dao = new AccountDAO();
    Account account = new Account();
    account.setUsername("acc_" + 10);
    account.setBillingId("1");
    account.setCreationDate(new Date());
    account.setEmail("acc1@jkjkj.com");
    account.setFirstName("acc1_fn" + 10);
    account.setLastName("acc1_ln" + 10);
    account.setLocale("en");
    account.setRegionalSettings("settings");
    account.setSecurityAnswer("foo");
    account.setSecurityQuestion("the question");
    //account.setReseller(m_resellers[0]);
    dao.update(account);
  }
*/

  public static void main (String[] args)
  {
    try {
      //testGetByCode();
      testGetAll();
      //testCreate();
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}
