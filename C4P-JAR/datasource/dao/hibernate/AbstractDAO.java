package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.InconsistentDataException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Base class for all Hibernate DAOs.
 */
public class AbstractDAO
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(AbstractDAO.class);

  /**
   * Retrieves an instance of an object. The object is expected to be unique.
   * @param query - the query to execute
   * @param paramName The name of the parameter that represents the object's identity
   * @param paramValue - the value for the parameter
   * @return the object if found; null otherwise.
   * @throws DataException if there is more than one object with that idenmtity.
   * todo rewrite to call the method with the collectionsToInitialize parameters
   */
  Object getSingleInstance (String query, String paramName, String paramValue)
  throws DataException
  {
    Object object = null;
    try {
      List objects = null;
      Session session = HibernateSession.currentSession();
      Query q = session.getNamedQuery(query);
      q.setString(paramName,paramValue);
      objects = q.list();
      if (objects.isEmpty()) {
        object = null;
      } else if (objects.size() > 1) {
        throw new InconsistentDataException ("getSingleInstance - More than one object identified by : " +
                                             paramValue + " = " + paramValue);
      } else {
        object = objects.get(0);
      }
    }
    catch (HibernateException e) {
      throw new DataException ("ErrorCode while executing " + query + " with param "+
                               paramName + " = " + paramValue,e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
    return object;
  }


  Object getMultipleInstance (String query, String paramName, String paramValue)
  throws DataException
  {
    List objects = null;
    try {
      Session session = HibernateSession.currentSession();
      Query q = session.getNamedQuery(query);
      q.setString(paramName,paramValue);
      objects = q.list();
   }
    catch (HibernateException e) {
      throw new DataException ("ErrorCode while executing " + query + " with param "+
                               paramName + " = " + paramValue ,e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
    return (Object)objects;
  }

  /**
   * Retrieves an instance of an object. The object is expected to be unique.
   * @param query - the query to execute
   * @param paramName The name of the parameter that represents the object's identity
   * @param paramValue - the value for the parameter
   * @param collectionsorProxiesToInitialize - the collections or proxies that
   * are lazily initialized and we want to initialize.
   * @return the object if found; null otherwise.
   * @throws DataException if there is more than one object with that idenmtity.
   */
  public Object getSingleInstance (String query, String paramName,
                                   String paramValue,
                                   List collectionsorProxiesToInitialize)
          throws DataException
  {
    Object object = null;
    Map parameters = new HashMap();
    parameters.put(paramName,paramValue);

    List objects = null;

    objects = execute(query,parameters,collectionsorProxiesToInitialize);
    if (objects.isEmpty()) {
      object = null;
    } else if (objects.size() > 1) {
      throw new InconsistentDataException ("getSingleInstance - More than one object identified by : " +
                                           paramValue + " = " + paramValue);
    } else {
      object = objects.get(0);
    }
    return object;
  }

  /**
   * Executes a query with no parameters.
   */
  protected List execute(String query) throws DataException
  {
      List objects = null;
    try {
      Session session = HibernateSession.currentSession();
      Query q = session.getNamedQuery(query);

      objects = q.list();
    }
    catch (HibernateException e) {
      throw new DataException ("ErrorCode while executing " + query,e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
    return objects;
  }

  /**
   * Executes a query with parameters.
   * @param queryName - Name name of a query
   * @param parameters - a map of parameters
   * @param collectionsOrProxiesToInitialize - the collections or proxiesthat are
   * lazily initialized and that we want to initialize.
   * @return the query's result
   * @throws DataException
   */
  protected List execute(String queryName,
                         Map parameters,
                         List collectionsOrProxiesToInitialize) throws DataException
  {
    List objects = null;
    try {
      Session session = HibernateSession.currentSession();
      Query q = session.getNamedQuery(queryName);
      for (Iterator it = parameters.keySet().iterator();it.hasNext();) {
        String paramName = (String) it.next();
        q.setParameter(paramName,parameters.get(paramName));
      }
      objects = q.list();
      if (objects != null) {
        for (Iterator it = objects.iterator();it.hasNext();){
          Object bean = it.next();
          if (collectionsOrProxiesToInitialize != null) {
            for (Iterator jt=collectionsOrProxiesToInitialize.iterator();jt.hasNext();){
              String propertyName = (String) jt.next();
              try {
                Hibernate.initialize(PropertyUtils.getSimpleProperty(bean,
                                                                     propertyName));
              } catch (IllegalAccessException e) {
                throw new DataException(e);
              } catch (InvocationTargetException e) {
                throw new DataException(e);
              } catch (NoSuchMethodException e) {
                throw new DataException(e);
              }
            }
          }
        }
      }
    }
    catch (HibernateException e) {
      throw new DataException ("ErrorCode while executing " + queryName,e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
    return objects;
  }

  /**
   * Deletes the single instance of an object in a table.
   *
   * @param query - how to retrieve the object.
   * @param paramName - the named parameter that identify the object.
   * @param paramValue - value for that parameter.
   * @throws DataException - if there is more than one object with that identity.
   */
  void deleteSingleInstance (String query, String paramName, String paramValue)
          throws DataException
  {
    Object object = null;
    Transaction tx = null;

    object = getSingleInstance(query,paramName,paramValue);

    try {
      Session session = HibernateSession.currentSession();
      tx = session.beginTransaction();
      session.delete(object);
      tx.commit();
    }
    catch (HibernateException e) {
      if (tx != null) {
        try{tx.rollback();} catch(Exception ex){}
      }
      throw new DataException ("deleteSingleInstance - query: " + query +
                               " " + paramName + "=" + paramValue,e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
  }

  /**
   * Creates the object it is not already persistent; otherwise, update it.
   *
   * @param object - the object to persist/update.
   * @throws DataException
   */
  public void save(Object object) throws DataException
  {
    Transaction tx = null;
    try {
      Session session = HibernateSession.currentSession();
      tx = session.beginTransaction();
      session.saveOrUpdate(object);
      tx.commit();
    }
    catch (HibernateException e){
      if (tx != null) {
        try{tx.rollback();} catch(Exception ex){}
      }
      throw new DataException("Cannot save object:",e);
    }
    finally {
      try {HibernateSession.closeSession();} catch(Exception e){}
    }
  }
  /**
   * Update a list of objects
   *
   * @param objects - a list of beans to update
   * @throws DataException
   */
  public void save(List objects) throws DataException
  {
    Transaction tx = null;
    try {
      Session session = HibernateSession.currentSession();
      tx = session.beginTransaction();
      for (Iterator it = objects.iterator();it.hasNext();) {
        session.saveOrUpdate(it.next());
      }
      tx.commit();
    }
    catch (HibernateException e){
      if (tx != null) {
        try{tx.rollback();} catch(Exception ex){}
      }
      throw new DataException("Cannot save objects:",e);
    }
    finally {
      try {HibernateSession.closeSession();} catch(Exception e){}
    }
  }


  protected int countObjects(String queryName,
                             Map parameters) throws DataException
  {
    int count = 0;
    try {
      Session session = HibernateSession.currentSession();
      Query q = session.getNamedQuery(queryName);
      for (Iterator it = parameters.keySet().iterator();it.hasNext();) {
        String paramName = (String) it.next();
        q.setParameter(paramName,parameters.get(paramName));
      }
      count =( (Integer) q.iterate().next() ).intValue();
    }
    catch (HibernateException e) {
      throw new DataException ("ErrorCode while executing " + queryName,e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
    return count;
  }
}


