package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.LicenseDAO;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Concrete License DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class LicenseHibernateDAO extends AbstractDAO implements LicenseDAO
{
  private static final String GET_LICENSE_BY_CODE = "License.only.by.code";
  private static final String GET_SINGLE_BY_CODE = "License.by.code";
  private static final String GET_BY_CODE = "Licenses.by.code";
  private static final String GET_ALL_LIKE_CODE = "Licenses.all.like.code";
  private static final String GET_BY_CODE_AND_USER_ID = "Licenses.by.code.and.userId";
  private static final String GET_UNUSED_BY_CODE = "Unused.licenses.by.code";
  private static final String CODE_ATTRIBUTE = "code";
  private static final String USER_ID_ATTRIBUTE = "userId";
  private static final String RESELLER_ID_ATTRIBUTE = "resellerId";
  private static final String COUNT_LICENSES_BY_CODE = "Count.licenses.by.code";
  private static final String COUNT_ALL_LICENSES_LIKE_CODE = "Count.licenses.all.like.code";

  private static final String GET_BY_CODE_RC = "Licenses.by.code.rc";
  private static final String GET_BY_CODE_AND_USER_ID_RC = "Licenses.by.code.and.userId.rc";
  private static final String GET_UNUSED_BY_CODE_RC = "Unused.licenses.by.code.rc";
  private static final String COUNT_LICENSES_BY_CODE_RC = "Count.licenses.by.code.rc";
  /**
   * Returns a License given its serial number.
   *
   * @param code - the serial number we want to retrieve
   * @return The License bean corresponding to that code.
   * @throws com.infowave.c4p.datasource.DataException
   * @deprecated remove this method
   */
  public License getSingleByCode(String code, String resellerId)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

    List list = execute(GET_SINGLE_BY_CODE,parameters,null);
    License license = null;
    if( list != null && list.size() == 1 ) {
	license = (License)list.get(0);
    } else {
	throw new DataException("Improper amount of license data returned");
    }

    return license;
  }

  /**
   * Returns the licenses with a specific code and owned by a specific user.
   *
   * @param code - the serial number we want to retrieve
   * @param userId - a user's id
   * @return The Licenses with that code belonging to that user
   * @throws com.infowave.c4p.datasource.DataException
   */
  public List getByCodeAndUserId(String code, String resellerId, String userId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(USER_ID_ATTRIBUTE,userId);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

   return execute(GET_BY_CODE_AND_USER_ID,parameters,null);
  }

  public List getByCodeAndUserIdRC(String code, String resellerId, String userId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(USER_ID_ATTRIBUTE,userId);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

   return execute(GET_BY_CODE_AND_USER_ID_RC,parameters,null);
  }


  /**
   * Returns the licenses with a specific code.
   *
   * @param code - the serial number we want to retrieve
   * @return The Licenses with that code belonging to that user
   * @throws com.infowave.c4p.datasource.DataException
   */

  public List getByCode(String code, String resellerId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

   return execute(GET_BY_CODE,parameters,null);
  }

  public List getByCodeRC(String code, String resellerId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

   return execute(GET_BY_CODE_RC,parameters,null);
  }

  public List getAllByCode(String code, String resellerId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

   return execute(GET_ALL_LIKE_CODE,parameters,null);
  }




  /**
   * Returns the licenses with a specific code that have not been used yet,
   *
   * @param code - the serial number we want to retrieve
   * @return The Licenses with that code that have not been used
   * @throws com.infowave.c4p.datasource.DataException
   */
  public List getUnusedByCode(String code, String resellerId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);
    return execute(GET_UNUSED_BY_CODE,parameters,null);
  }
  public List getUnusedByCodeRC(String code, String resellerId)
          throws DataException
  {
    License license;
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);
    return execute(GET_UNUSED_BY_CODE_RC,parameters,null);
  }


  /**
   * Returns the licenses with a specific code that have not been used yet,
   * specific to LapLink Gold 11.5. Note that the code is only the first 11
   * digits of a 12 digit entity.
   *
   * @param code - the serial number we want to retrieve
   * @return The Licenses with that code that have not been used
   * @throws com.infowave.c4p.datasource.DataException
   */
    public List getUnusedLLGByCode(String code, String resellerId)
        throws DataException
    {
        Map parameters = new HashMap();
        parameters.put("code", code+"_");
        parameters.put("resellerId", resellerId);
        return execute("Unused.llg.licenses.by.code", parameters, null);
    }



  /**
   * Save - or update- a License bean.
   * @param license - the bean to be saved.
   * @throws com.infowave.c4p.datasource.DataException
   */

  public void update(License license)
          throws DataException
  {
    super.save(license);
  }

  /**
   * Save a license, the desktop that receives this license, and the desktop's
   * previous license.
   * @param license
   * @param desktop
   * @throws DataException
   */
  public void licenseDesktop(License license,Desktop desktop,
                             List licensesMadeAvailable,List expiredLicenses,
                             Account account)
          throws DataException
  {
    List objects = new ArrayList(3);
    objects.add(license);
    objects.add(desktop);
    if (licensesMadeAvailable != null) {
      objects.addAll(licensesMadeAvailable);
    }
    if (account != null){
      objects.add(account);
    }
    super.save(objects);
  }
  public void licenseRC(License license,RCUsage rcusage,
                             List licensesMadeAvailable)
          throws DataException
  {
    List objects = new ArrayList(3);
    objects.add(license);
    objects.add(rcusage);
    if (licensesMadeAvailable != null) {
      objects.addAll(licensesMadeAvailable);
    }
    super.save(objects);
  }
    /**
   * Saves the license and the desktop.
   *
   * @param licenses
   * @param desktop
   * @throws DataException
   */
  public void unlicenseDesktop(List licenses, Desktop desktop)
          throws DataException
  {
    List objects = new ArrayList(2);
    objects.addAll(licenses);
    objects.add(desktop);
    super.save(objects);

  }
  public void unlicenseRC(List licenses, RCUsage rcusage)
          throws DataException
  {
    List objects = new ArrayList(2);
    objects.addAll(licenses);
    objects.add(rcusage);
    super.save(objects);

  }


  /**
   * Count the number of licenses having a particular code.
   * @param code
   * @param resellerId
   * @return
   * @throws DataException
   */
  public int countLicenses(String code, String resellerId)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

    return super.countObjects(COUNT_LICENSES_BY_CODE,parameters);
  }
  public int countLicensesRC(String code, String resellerId)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

    return super.countObjects(COUNT_LICENSES_BY_CODE_RC,parameters);
  }
  public int countAllLicenses(String code, String resellerId)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(RESELLER_ID_ATTRIBUTE,resellerId);

    return super.countObjects(COUNT_ALL_LICENSES_LIKE_CODE,parameters);
  }
  /**
   * Creates a batch of licenses.
   * @param licenses
   * @throws DataException
   */
  public void createLicenses(List licenses) throws DataException
  {
    super.save(licenses);
  }
/**
 * Deletes a licenses
 * @param licenseCode
 * @throws DataException
 */

public void deleteLicense(String licenseCode) throws DataException
{
	super.deleteSingleInstance(GET_LICENSE_BY_CODE, CODE_ATTRIBUTE, licenseCode);
}

  // -------------------------------------------------------------- Test methods

private static void testGetByCodeAndUserId() throws Exception
{
  String username = "c4test";
  String resellerName = "laplink";
  String code = "1234567890123456";
  AccountDAO accountDAO = DAOFactory.getAccountDAO();
  ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
  LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();


  Account account = accountDAO.getByUsername(username);
  Reseller reseller =  resellerDAO.getByCode(resellerName);

  List licenses = licenseDAO.getByCodeAndUserId(code,reseller.getId(),
                                                account.getId());
  System.out.println("licenses for user " +  username + " and code " + code);
  for (int i=0;i<licenses.size();i++){
    System.out.println(licenses.get(i));
  }
}

private static void testGetUnusedByCode() throws Exception
{
  String code = "1111117890123456";
  String resellerName = "laplink";
  LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
  ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
  Reseller reseller =  resellerDAO.getByCode(resellerName);
  List licenses = licenseDAO.getUnusedByCode(code,reseller.getId());


  System.out.println("Unused licenses with code " + code);
  for (int i=0;i<licenses.size();i++){
    System.out.println(licenses.get(i));
  }
}
private static void testCountLicenses() throws Exception
{
  LicenseHibernateDAO licenseDAO = new LicenseHibernateDAO();
  String code = "r0";
  String resellerName = "laplink";
  ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
  Reseller reseller =  resellerDAO.getByCode(resellerName);

  System.out.println("nb of licenses with code " + code + ": " +
                     licenseDAO.countLicenses(code,reseller.getId()));
}

  public static void main(String [] args)
  {
    try{
      //testGetByCodeAndUserId();
      //testGetUnusedByCode();
      testCountLicenses();
    }
    catch(Exception e) {e.printStackTrace();}

  }
}
