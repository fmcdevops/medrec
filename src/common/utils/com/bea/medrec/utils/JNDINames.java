package com.bea.medrec.utils;

/**
 * <p>Encapsulates all JNDI names for EJBs, JMS, JDBC, etc.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class JNDINames {

 /**
  * Catalog Home
  */
  public static final String CATALOG_HOME = "java:comp/env/";

 /**
  * Entity EJB reference names
  */
  public static final String ADDRESS_LOCAL_HOME =
   CATALOG_HOME+"ejb/local/address";
  public static final String ADMIN_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/admin";
  public static final String GROUP_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/group";
  public static final String PATIENT_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/patient";
  public static final String PHYSICIAN_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/physician";
  public static final String PRESCRIPTION_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/prescription";
  public static final String RECORD_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/record";
  public static final String USER_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/user";
  public static final String VITALSIGNS_LOCAL_HOME =
    CATALOG_HOME+"ejb/local/vitalsigns";

 /**
  * Session EJB reference names
  */
  public static final String ADMIN_SESSION_HOME =
    CATALOG_HOME+"ejb/adminsession";
  public static final String MAIL_SESSION_HOME =
    CATALOG_HOME+"ejb/mailsession";
  public static final String PATIENT_SESSION_HOME =
    CATALOG_HOME+"ejb/patientsession";
  public static final String RECORD_SESSION_HOME =
    CATALOG_HOME+"ejb/recordsession";

 /**
  * Remote EJB home names
  */
  public static final String ADMIN_SESSION_REMOTE_HOME =
    "AdminSessionEJB.AdminSessionHome";
  public static final String MAIL_SESSION_REMOTE_HOME =
    "MailSessionEJB.MailSessionHome";
  public static final String PATIENT_SESSION_REMOTE_HOME =
    "PatientSessionEJB.PatientSessionHome";
  public static final String RECORD_SESSION_REMOTE_HOME =
    "RecordSessionEJB.RecordSessionHome";
  public static final String PHYS_SESSION_REMOTE_HOME =
    "PhysicianSessionEJB.PhysicianSessionHome";

 /**
  * JMS connection and queue names
  */
  public static final String QUEUE_CONNECTION_FACTORY =
   CATALOG_HOME+"jms/MedRecQueueConnectionFactory";
  public static final String REGISTRATION_MDB_QUEUE =
    CATALOG_HOME+"jms/REGISTRATION_MDB_QUEUE";
  public static final String MAIL_MDB_QUEUE =
    CATALOG_HOME+"jms/MAIL_MDB_QUEUE";
  public static final String XML_UPLOAD_MDB_QUEUE =
    CATALOG_HOME+"jms/XML_UPLOAD_MDB_QUEUE";

 /**
  * JDBC connection and queue names
  */
  public static final String MEDREC_TX_DATASOURCE = "MedRecTxDataSource";

 /**
  * Mail session
  */
  public static final String MAIL_SESSION = "mail/MedRecMailSession";
}
