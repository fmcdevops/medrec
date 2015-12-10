package com.bea.medrec.utils;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;
import org.apache.log4j.Logger;
import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * <p>Central service locator that encapsulates all service lookup and
 * creation processes for EJBs, JMS, Transactions, Datasources, etc.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ServiceLocator
{
  static Logger logger = Logger.getLogger(ServiceLocator.class.getName());

  private InitialContext ic = null;
  private Map cache = null;
  private static ServiceLocator instance;
  private final static String JNDI_FACTORY =
    "weblogic.jndi.WLInitialContextFactory";

 /**
  * Constructors
  */
  private ServiceLocator() throws NamingException
  {
    try {
      ic = new InitialContext();
      cache = Collections.synchronizedMap(new HashMap());
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throw ne;
    }
  }

  private ServiceLocator(String pUrl)
    throws NamingException
  {
    try {
      ic = getInitialContext(pUrl);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throw ne;
    }
  }

  private ServiceLocator(InitialContext iCtx) { ic = iCtx; }

 /**
  * <p>Get instance of ServiceLocator.</p>
  *
  * @return ServiceLocator
  */
  public static ServiceLocator getInstance() throws NamingException
  {
    if (instance == null) instance = new ServiceLocator();
    return instance;
  }

 /**
  * <p>Get instance of ServiceLocator.</p>
  *
  * @param pUrl
  *
  * @return ServiceLocator
  */
  public static ServiceLocator getInstance(String pUrl) throws NamingException
  {
    if (instance == null) instance = new ServiceLocator(pUrl);
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
    EJBLocalHome home = null;

    if (cache.containsKey(name)) {
      logger.debug("Getting cached local home for "+name);
      home = (EJBLocalHome)cache.get(name);
    }
    else {
      logger.debug("Getting new local home for "+name);
      home = EJBHomeFactory.getFactory(ic).lookupLocalHome(name, homeClass);
      cache.put(name, home);
    }
    return home;
  }

 /**
  * <p>Lookup EJB remote home.</p>
  *
  * @param name
  * @param homeClass
  *
  * @return EJBLocalHome
  */
  public EJBHome lookupHome(String name, Class homeClass)
    throws NamingException, ClassCastException
  {
    EJBHome home = null;

    if (cache.containsKey(name)) {
      logger.debug("Getting cached remote home for "+name);
      home = (EJBHome)cache.get(name);
    }
    else {
      logger.debug("Getting new remote home for "+name);
      home = EJBHomeFactory.getFactory(ic).lookupHome(name, homeClass);
      cache.put(name, home);
    }
    return home;
  }

 /**
  * <p>Get local object.</p>
  *
  * @param name
  * @param homeClass
  *
  * @return Object
  */
  public Object getLocalObj(String name, Class homeClass)
    throws NamingException, ClassCastException
  {
    return EJBHomeFactory.getFactory(ic).getLocalObj(name, homeClass);
  }

 /**
  * <p>Get local object.</p>
  *
  * @param name
  * @param homeClass
  *
  * @return Object
  */
  public Object getObj(String name, Class homeClass)
    throws NamingException, ClassCastException
  {
    return EJBHomeFactory.getFactory(ic).getObj(name, homeClass);
  }

 /**
  * <p>Lookup QueueConnectionFactory.</p>
  *
  * @param name
  *
  * @return QueueConnectionFactory
  */
  public QueueConnectionFactory lookupQCFactory(String name)
    throws NamingException
  {
    QueueConnectionFactory queConnFactory = null;

    if (cache.containsKey(name)) {
      queConnFactory = (QueueConnectionFactory)cache.get(name);
    }
    else {
      queConnFactory = JMSFactory.getFactory(ic).lookupQCFactory(name);
      cache.put(name, queConnFactory);
    }
    return queConnFactory;
  }

 /**
  * <p>Lookup queue.</p>
  *
  * @param  name
  *
  * @return Queue
  */
  public Queue lookupQueue(String name) throws NamingException
  {
    Queue que = null;

    if (cache.containsKey(name)) {
      que = (Queue)cache.get(name);
    }
    else {
      que = JMSFactory.getFactory(ic).lookupQueue(name);
      cache.put(name, que);
    }
    return que;
  }

 /**
	* <p>Utility to get the InitialContext of a given server.</p>
	*
	* @param url URL of the server.

	* @exception NamingException Thrown on naming errors.
	*/
  public static InitialContext getInitialContext(String url)
       throws NamingException
  {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, url);
    return new InitialContext(env);
  }
}
