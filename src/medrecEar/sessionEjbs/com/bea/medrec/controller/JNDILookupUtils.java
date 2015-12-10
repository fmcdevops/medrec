package com.bea.medrec.controller;

import com.bea.medrec.entities.*;
import com.bea.medrec.utils.JNDINames;
import com.bea.medrec.utils.ServiceLocator;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.NamingException;

/**
 * <p>Utility to lookup entity and session beans and JMS queues.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class JNDILookupUtils
{
  //  E J B   &   Q U E U E   L O O K U P S

  //  E N T I T I E S
  /**
  * <p>Get Address Entity EJB local home.</p>
  *
  * @return  AddressLocalHome
  *
  * @exception NamingException
  */
  public static AddressLocalHome getAddressLocalHome() throws NamingException {
    return (AddressLocalHome)lookUpLocalHome(JNDINames.ADDRESS_LOCAL_HOME,
      AddressLocalHome.class);
  }

 /**
  * <p>Get Group Entity EJB local home.</p>
  *
  * @return  GroupLocalHome
  *
  * @exception NamingException
  */
  public static GroupLocalHome getGroupLocalHome() throws NamingException {
    return (GroupLocalHome)lookUpLocalHome(JNDINames.GROUP_LOCAL_HOME,
      GroupLocalHome.class);
  }

  /**
  * <p>Get Patient Entity EJB local home.</p>
  *
  * @return  PatientLocalHome
  *
  * @exception NamingException
  */
  public static PatientLocalHome getPatientLocalHome() throws NamingException {
    return (PatientLocalHome)lookUpLocalHome(JNDINames.PATIENT_LOCAL_HOME,
      PatientLocalHome.class);
  }

  /**
  * <p>Get Rx Entity EJB local home.</p>
  *
  * @return  PrescriptionLocalHome
  *
  * @exception NamingException
  */
  public static PrescriptionLocalHome getRxLocalHome() throws NamingException {
    return (PrescriptionLocalHome)lookUpLocalHome(JNDINames.PRESCRIPTION_LOCAL_HOME,
      PrescriptionLocalHome.class);
  }

  /**
  * <p>Get Record Entity EJB local home.</p>
  *
  * @return  RecordLocalHome
  *
  * @exception NamingException
  */
  public static RecordLocalHome getRecordLocalHome() throws NamingException {
    return (RecordLocalHome)lookUpLocalHome(JNDINames.RECORD_LOCAL_HOME,
      RecordLocalHome.class);
  }

  /**
  * <p>Get User Entity EJB local home.</p>
  *
  * @return  UserLocalHome
  *
  * @exception NamingException
  */
  public static UserLocalHome getUserLocalHome() throws NamingException {
    return (UserLocalHome)lookUpLocalHome(JNDINames.USER_LOCAL_HOME,
      UserLocalHome.class);
  }

  /**
  * <p>Get Vital Signs Entity EJB local home.</p>
  *
  * @return  VitalSignsLocalHome
  *
  * @exception NamingException
  */
  public static VitalSignsLocalHome getVitalSignsLocalHome() throws NamingException {
    return (VitalSignsLocalHome)lookUpLocalHome(JNDINames.VITALSIGNS_LOCAL_HOME,
      VitalSignsLocalHome.class);
  }


  //  Q U E U E S
  /**
  * <p>Get Mail JMS Queue.</p>
  *
  * @return  Queue
  *
  * @exception NamingException
  */
  public static Queue getJMailQueue() throws NamingException {
    return (Queue)
      ServiceLocator.getInstance().lookupQueue(JNDINames.MAIL_MDB_QUEUE);
  }

 /**
  * <p>Get Registration JMS Queue.</p>
  *
  * @return  Queue
  *
  * @exception NamingException
  */
  public static Queue getRegQueue() throws NamingException {
    return (Queue)
      ServiceLocator.getInstance().lookupQueue(JNDINames.REGISTRATION_MDB_QUEUE);
  }

 /**
  * <p>Get XML Upload JMS Queue.</p>
  *
  * @return  Queue
  *
  * @exception NamingException
  */
  public static Queue getXMLQueue() throws NamingException {
    return (Queue)
      ServiceLocator.getInstance().lookupQueue(JNDINames.XML_UPLOAD_MDB_QUEUE);
  }

  /**
  * <p>Get JMS Queue Connection Factory.</p>
  *
  * @return  QueueConnectionFactory
  *
  * @exception NamingException
  */
  public static QueueConnectionFactory getQCFactory() throws NamingException {
    return (QueueConnectionFactory)
      ServiceLocator.getInstance().lookupQCFactory(JNDINames.QUEUE_CONNECTION_FACTORY);
  }

      //   S E S S I O N   B E A N S
 /**
  * <p>Get MailSession.</p>
  *
  * @return  MailSession
  *
  * @exception NamingException
  */
  public static MailSession getMailSession()
   throws NamingException
  {
    ServiceLocator locator = ServiceLocator.getInstance();
    Object obj = locator.getObj(JNDINames.MAIL_SESSION_REMOTE_HOME,
      com.bea.medrec.controller.MailSessionHome.class);
    return (MailSession)obj;
  }

 /**
  * <p>Get AdminSession.</p>
  *
  * @return  AdminSession
  *
  * @exception NamingException
  */
  public static AdminSession getAdminSession()
   throws NamingException
  {
    ServiceLocator locator = ServiceLocator.getInstance();
    Object obj = locator.getObj(JNDINames.ADMIN_SESSION_REMOTE_HOME,
      com.bea.medrec.controller.AdminSessionHome.class);
    return (AdminSession)obj;
  }

  //  L O O K U P   U T I L S
  /**
  * <p>Get local home.</p>
  *
  * @return  Object
  *
  * @exception NamingException
  */
  public static Object lookUpHome(String pHome, Class pClazz)
    throws NamingException {
    ServiceLocator locator = ServiceLocator.getInstance();
    Object obj = locator.lookupHome(pHome, pClazz);
    return obj;
  }

  /**
  * <p>Get local home.</p>
  *
  * @return  Object
  *
  * @exception NamingException
  */
  public static Object lookUpLocalHome(String pHome, Class pClazz)
    throws NamingException {
    ServiceLocator locator = ServiceLocator.getInstance();
    Object obj = locator.lookupLocalHome(pHome, pClazz);
    return obj;
  }
}
