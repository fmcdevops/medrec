package com.bea.medrec.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import org.apache.log4j.Logger;

/**
 * <p>Encapsulates service lookup and creation processes for EJBs.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class EJBHomeFactory
{
  static Logger logger = Logger.getLogger(EJBHomeFactory.class.getName());

  private InitialContext ic = null;
  private static EJBHomeFactory instance;

 /**
  * Construtors
  */
  private EJBHomeFactory() throws NamingException
  {
    try {
      ic = new InitialContext();
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throw ne;
    } 
  }

  private EJBHomeFactory(InitialContext iCtx) { ic = iCtx; }

 /**
  * <p>Get instance of EJB factory.</p>
  *
  * @return EJBHomeFactory
  */
  public static EJBHomeFactory getFactory() throws NamingException
  {
    if (instance == null) instance = new EJBHomeFactory();
    return instance;    
  }

 /**
  * <p>Get instance of EJB factory.</p>
  *
  * @param iCtx
  *
  * @return EJBHomeFactory
  */
  public static EJBHomeFactory getFactory(InitialContext iCtx)
  {
    if (instance == null) instance = new EJBHomeFactory(iCtx);
    return instance;
  }

 /**
  * <p>Lookup EJB local home.</p>
  *
  * @param name
  * @param homeClass
  *
  * @return EJBLocalHome
  */
  public EJBLocalHome lookupLocalHome(String name, Class homeClass)
    throws NamingException, ClassCastException
  {
    logger.debug("Getting local home: "+name+", "+homeClass.getName());
    
    EJBLocalHome home = null;

    try {
      Object objRef = ic.lookup(name);
      home = (EJBLocalHome)PortableRemoteObject.narrow(objRef, homeClass);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throw ne;
    }
    catch(ClassCastException cce) {
      logger.error(cce.getMessage());
      throw cce;
    }
    return home;
  }

 /**
  * <p>Lookup EJB remote home.</p>
  *
  * @param name
  * @param homeClass
  *
  * @return EJBHome
  */
  public EJBHome lookupHome(String name, Class homeClass)
    throws NamingException, ClassCastException
  {
    logger.debug("Getting home: "+name+", "+homeClass.getName());
    
    EJBHome home = null;

    try {
      Object objRef = ic.lookup(name);
      logger.debug("objRef: "+objRef);
      home = (EJBHome)PortableRemoteObject.narrow(objRef, homeClass);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throw ne;
    }
    catch(ClassCastException cce) {
      logger.error(cce.getMessage());
      throw cce;
    }
    return home;
  }

 /**
  * <p>Get local object.</p>
  *
  * @param pJndiLookupName
  * @param homeClass
  *
  * @return Object
  */
  public Object getLocalObj(String pJndiLookupName, Class homeClass)
   throws EJBException, NamingException
  {
    try
    {
      EJBLocalHome objLocalHome = lookupLocalHome(pJndiLookupName, homeClass);
      //get the method of create
      Method m =
        objLocalHome.getClass().getDeclaredMethod("create", new Class[0]);
      //invoke the create method
      Object obj = m.invoke (objLocalHome, new Object[0]);
      return obj;
    }
    catch (NoSuchMethodException ne) {
      throw new EJBException (ne);
    }
    catch (InvocationTargetException ie) {
      throw new EJBException (ie);
    }
    catch (IllegalAccessException iae) {
      throw new EJBException (iae);
    }
  }

 /**
  * <p>Get object.</p>
  *
  * @param pJndiLookupName
  * @param homeClass
  *
  * @return Object
  */
  public Object getObj(String pJndiLookupName, Class homeClass)
    throws EJBException, NamingException
  {
    try
    {
      EJBHome objLocalHome = lookupHome(pJndiLookupName, homeClass);
      //get the method of create
      Method m = objLocalHome.getClass().getDeclaredMethod("create", new Class[0]);
      //invoke the create method
      Object obj = m.invoke (objLocalHome, new Object[0]);
      return obj;
    }
    catch (NoSuchMethodException ne) {
      throw new EJBException (ne);
    }
    catch (InvocationTargetException ie) {
      throw new EJBException (ie);
    }
    catch (IllegalAccessException iae) {
      throw new EJBException (iae);
    }
  }
}
