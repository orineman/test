package com.infowave.c4p.model.facade;

import com.infowave.c4p.service.delegate.ConfigurationManager;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.model.assembler.ResellerAssembler;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * This class is responsible for extracting some configration info from the
 * database. Right now, this information is limited to teh list of resellers.
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ConfigurationManagerImpl implements ConfigurationManager

 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ConfigurationManager.class);

  /**
   * Returns the list of all known resellers.
   *
   * @return the list of all known resellers.
   * @throws ServiceException
   */
  public List getAllResellers() throws ServiceException
  {
    List resellers = new ArrayList();
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
    try {
      Reseller[] res = null;
      res = resellerDAO.getAll();
      if (res != null){
        for (int i=0;i<res.length;i++){
          resellers.add(ResellerAssembler.createResellerDTO(res[i]));
        }
    }
    }
    catch(DataException e)
    {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return resellers;
  }

  // ------------------------------------------------------------------- test

  private static void testGetAllResellers() throws Exception
  {
    ConfigurationManagerImpl cfg = new ConfigurationManagerImpl();
    List resellers;
    resellers = cfg.getAllResellers();
    for (Iterator it = resellers.iterator();it.hasNext();){
      Reseller reseller = (Reseller) it.next();
      System.out.println(reseller);
    }
  }
  public static void main(String[] args) {
    try {
      testGetAllResellers();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
