package com.bea.medrec.actions;

import com.bea.medrec.controller.*;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;

/**
 * <p>Base lookup dispatch action encapsulating
 * common patient webapp functionality.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public abstract class PatientBaseLookupDispatchAction
  extends BaseLookupDispatchAction
{
  private static Logger logger =
    Logger.getLogger(PatientBaseLookupDispatchAction.class.getName());

  protected InitialContext ctx = null;
  private AdminSession adminSession;
  private PatientSession patientSession;
  private RecordSession recordSession;

 /**
  * <p>Retrives AdminSession home.
  * If instance does not exist, retrieve a new instance.<p>
  *
  * @return AdminSession
  */
  protected AdminSession getAdminSession() throws Exception {
    if (ctx == null) ctx = new InitialContext();
    if (adminSession == null) {
      logger.debug("Getting new admin session.");
      this.adminSession = getAdminSessionHome();
    }
    return this.adminSession;
  }

 /**
  * <p>Retrives PatientSession home.
  * If instance does not exist, retrieve a new instance.<p>
  *
  * @return PatientSession
  */
  protected PatientSession getPatientSession() throws Exception {
    if (ctx == null) ctx = new InitialContext();
    if (patientSession == null) {
      logger.debug("Getting new patient session.");
      this.patientSession = getPatientSessionHome();
    }
    return this.patientSession;
  }

 /**
  * <p>Retrives RecordSession home.
  * If instance does not exist, retrieve a new instance.<p>
  *
  * @return RecordSession
  */
  protected RecordSession getRecordSession() throws Exception {
    if (ctx == null) ctx = new InitialContext();
    if (recordSession == null) {
      logger.debug("Getting new record session.");
      this.recordSession = getRecordSessionHome();
    }
    return this.recordSession;
  }

      //   P R I V A T E   M E T H O D S
 /**
  * <p>Get AdminSession</p>
  *
  * @return AdminSession
  */
  private AdminSession getAdminSessionHome() throws Exception {
   AdminSessionHome home = (AdminSessionHome)ctx.lookup(
     "java:/comp/env/AdminSessionEJB");
   return (AdminSession)home.create();
  }

 /**
  * <p>Get PatientSession EJB</p>
  *
  * @return PatientSession
  */
  private PatientSession getPatientSessionHome() throws Exception {
    PatientSessionHome home = (PatientSessionHome)ctx.lookup(
     "java:/comp/env/PatientSessionEJB");
   return (PatientSession)home.create();
  }

 /**
  * <p>Get RecordSession EJB</p>
  *
  * @return RecordSession
  */
  private RecordSession getRecordSessionHome() throws Exception {
   RecordSessionHome home = (RecordSessionHome)ctx.lookup(
     "java:/comp/env/RecordSessionEJB");
   return (RecordSession)home.create();
  }
}
