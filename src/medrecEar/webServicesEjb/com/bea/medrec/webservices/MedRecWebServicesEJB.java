package com.bea.medrec.webservices;

import com.bea.medrec.value.Patient;
import com.bea.medrec.value.Record;
import com.bea.medrec.value.RecordsSummary;
import com.bea.medrec.controller.PatientSession;
import com.bea.medrec.controller.RecordSession;
import com.bea.medrec.utils.JNDINames;
import com.bea.medrec.utils.ServiceLocator;
import java.rmi.RemoteException;
import java.util.Collection;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.rpc.soap.SOAPFaultException;
import javax.xml.soap.Detail;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import org.apache.log4j.Logger;
import weblogic.ejb.GenericSessionBean;

/**
 * <p>MedRecWebServices provides an interface for all MedRec Web services.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecWebServicesEJB extends GenericSessionBean
{
  private static Logger logger =
    Logger.getLogger(MedRecWebServicesEJB.class.getName());

    //  A D D   R E C O R D
  /**
  * <p>Accesses MedRec Web service adding a record, including
  * vital signs and prescriptions.</p>
  *
  * @param pRecordWS
  *
  * @exception RemoteException
  * @exception SOAPFaultException
  */
  public void addRecord(RecordWS pRecordWS)
    throws RemoteException, SOAPFaultException
  {
    logger.info("Adding record.");
    logger.debug(pRecordWS.toString());

    // Declare local variables.
    RecordSession recordSession = null;
    Record record = null;

    try {
      recordSession = getRecordSession();
      record = MedRecWebServicesUtils.toRecord(pRecordWS);
      recordSession.addRecord(record);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
  }

      //  G E T   A   R E C O R D
  /**
  * <p>Retrieves all information for a visit given a record id.
  * This includes vital signs, the record, and any prescriptions.</p>
  *
  * @param pId    Id of the record.
  *
  * @return RecordWS
  *
  * @exception RemoteException
  * @exception SOAPFaultException
  */
  public RecordWS getRecord(Integer pId)
    throws RemoteException, SOAPFaultException
  {
    logger.info("Getting record.");
    logger.debug("RecId: "+pId);

    // Declare local variables.
    RecordSession recordSession = null;
    Record record = null;
    RecordWS recordWS = null;

    try {
      recordSession = getRecordSession();
      record = recordSession.getRecord(pId);
      recordWS = MedRecWebServicesUtils.toRecordWS(record);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
    return recordWS;
  }

      //  G E T   R E C O R D S   S U M M A R Y
  /**
  * <p>Retrieves all medical record summary.
  * This includes a collection of abbreviated records, and
  * a collection of current and recent prescriptions.</p>
  *
  * @param pId    Id of the record.
  *
  * @return RecordsSummaryWS
  *
  * @exception RemoteException
  * @exception SOAPFaultException
  */
  public RecordsSummaryWS getRecordsSummary(Integer pId)
    throws RemoteException, SOAPFaultException
  {
    logger.info("Getting records summary.");
    logger.debug("PatId: "+pId);

    // Declare local variables.
    RecordSession recordClient = null;
    RecordsSummary recSum = null;
    RecordsSummaryWS recSumWS = null;

    try {
      recordClient = getRecordSession();
      recSum = recordClient.getRecordsSummary(pId);
      recSumWS = MedRecWebServicesUtils.toRecordsSummaryWS(recSum);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
    return recSumWS;
  }

        //  G E T   P A T I E N T
  /**
  * <p>Find patient by last name.  Wild card search- %lastName%.</p>
  *
  * @param pLastname   Last name substring
  *
  * @return PatientWS[]  Array of patient web service value objects.
  *
  * @exception RemoteException
  * @exception SOAPFaultException
  */
  public PatientWS[] findPatientByLastNameWild(String pLastname)
    throws RemoteException, SOAPFaultException
  {
    logger.info("Finding patient by wildcard last name.");
    logger.debug("Last name: "+pLastname);

    // Declare local variables.
    PatientSession patientClient = null;
    PatientWS[] patients = null;
    Collection patientCol = null;

    try {
      patientClient = getPatientSession();
      patientCol = patientClient.findPatientByLastNameWild(pLastname);
      patients = MedRecWebServicesUtils.toPatientWSArray(patientCol);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
    return patients;
  }

        //  G E T   P A T I E N T   B Y   S S N
 /**
  * <p>Get patient by patient id.</p>
  *
  * @param pId    SSN of patient.
  *
  * @return PatientWS
  *
  * @exception RemoteException
  * @exception SOAPFaultException
  */
  public PatientWS findPatientBySsn(String pId)
     throws RemoteException, SOAPFaultException
  {
    logger.info("Finding patient by ssn.");
    logger.debug("SSN: "+pId);

    // Declare local variables.
    PatientSession patientClient = null;
    Patient patient = null;
    PatientWS patientWS = null;

    try {
      // Get handle on patient session bean.
      patientClient = getPatientSession();

      // Find patient.
      patient = patientClient.findPatientBySsn(pId);

      // Call ssn finder method.  Convert to patient web service object.
      patientWS = MedRecWebServicesUtils.toPatientWS(patient);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
    return patientWS;
  }

        //  R E G I S T E R   P A T I E N T
  /**
  * <p>Accesses MedRec Web service adding a new active patient.</p>
  *
  * @param pPatientWS
  *
  * @exception RemoteException
  * @exception SOAPFaultException
  */
  public void registerPatient(PatientWS pPatientWS, String pPassword)
    throws RemoteException, SOAPFaultException
  {
    logger.info("Adding patient.");
    logger.debug(pPatientWS.toString());

    // Declare local variables.
    PatientSession patientSession = null;
    Patient patient = null;

    try {
      patientSession = getPatientSession();
      if (patientSession.findPatientByEmail(pPatientWS.getEmail()) != null) {
        throw new Exception("User "+pPatientWS.getEmail()+" already exists.");
      }
      logger.debug("Creating new account for patient.");
      patient = MedRecWebServicesUtils.toPatient(pPatientWS);
      patientSession.processActiveRegistration(patient, pPassword);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
  }

    //  U P D A T E   P A T I E N T
 /**
  * <p>Accesses MedRec Web service to update patient info.</p>
  *
  * @param pPatientWS
  *
  * @exception SOAPFaultException
  */
  public void updatePatient(PatientWS pPatientWS) {
    logger.info("Updating patient.");
    logger.debug(pPatientWS.toString());

    // Declare local variables.
    PatientSession patientSession = null;
    Patient patient = null;

    try {
      patientSession = getPatientSession();
      patient = MedRecWebServicesUtils.toPatient(pPatientWS);
      patientSession.updatePatient(patient);
    }
    catch(NamingException ne) {
      logger.error(ne.getMessage());
      throwSoapFault(ne.getMessage());
    }
    catch(RemoteException re) {
      logger.error(re.getMessage());
      throwSoapFault(re.getMessage());
    }
    catch(Exception e) {
      logger.error(e.getMessage());
      throwSoapFault(e.getMessage());
    }
  }

      //   U T I L I T Y   M E T H O D S
 /**
  * Get PatientSession
  */
  private PatientSession getPatientSession() throws NamingException  {
    ServiceLocator locator = ServiceLocator.getInstance();
    Object obj = locator.getObj(JNDINames.PATIENT_SESSION_REMOTE_HOME,
      com.bea.medrec.controller.PatientSessionHome.class);
    return (PatientSession)obj;
  }

 /**
  * Get RecordSession
  */
  private RecordSession getRecordSession() throws NamingException {
    ServiceLocator locator = ServiceLocator.getInstance();
    Object obj = locator.getObj(JNDINames.RECORD_SESSION_REMOTE_HOME,
      com.bea.medrec.controller.RecordSessionHome.class);
    return (RecordSession)obj;
  }

 /**
  * Construct and throw javax.xml.rpc.soap.SOAPFaultException;
  */
  private void throwSoapFault(String pErrMsg) throws SOAPFaultException {
    // Declare and initialize local variables.
    String faultActor = "MedRec";
    SOAPFactory factory = null;
    Detail detail = null;

    try {
      factory = SOAPFactory.newInstance();
      detail = factory.createDetail();
      // add the detail to the detail using
      // detail.addChildElement( ... );
      throw new SOAPFaultException(
      new QName("http://schemas.xmlsoap.org/soap/envelope/", "Server"),
         pErrMsg, faultActor, detail);
    }
    catch (SOAPException e) {
      logger.error("Error occurred while constructing SOAPFaultException: "+
        pErrMsg, e);
    }
  }
}
