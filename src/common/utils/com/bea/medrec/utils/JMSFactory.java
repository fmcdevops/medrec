package com.bea.medrec.utils;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 * <p>Encapsulates service lookup and creation processes for JMS.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class JMSFactory {

  private static Logger logger = Logger.getLogger(JMSFactory.class.getName());
  private InitialContext ic = null;
  private static JMSFactory instance = null;

 /**
  * Constructor
  */
  private JMSFactory()  throws NamingException
  {
    try {
      ic = new InitialContext();
    }
    catch(NamingException ne) {
      this.logger.error(ne);
      throw ne;
    }
  }

  private JMSFactory(InitialContext iCtx) { ic = iCtx; }

 /**
  * <p>Get instance of JMSFactory.</p>
  *
  * @return JMSFactory
  */
  public static JMSFactory getFactory() throws NamingException
  {
    if (instance == null) instance = new JMSFactory();
    return instance;
  }

 /**
  * <p>Get instance of JMSFactory.</p>
  *
  * @param iCtx
  *
  * @return JMSFactory
  */
  public static JMSFactory getFactory(InitialContext iCtx)
  {
    if (instance == null) instance = new JMSFactory(iCtx);
    return instance;
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
    try {
      queConnFactory = (QueueConnectionFactory)ic.lookup(name);
    } catch (NamingException ne) {
      this.logger.error(ne);
      throw ne;
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
  public Queue lookupQueue(String name) throws NamingException {
    Queue que = null;
    try {
      que = (Queue)ic.lookup(name);
    } catch (NamingException ne) {
      this.logger.error(ne);
      throw ne;
    }
    return que;
  }
}
