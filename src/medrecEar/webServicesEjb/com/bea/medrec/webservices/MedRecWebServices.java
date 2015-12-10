package com.bea.medrec.webservices;

import javax.ejb.EJBObject;
import javax.xml.rpc.soap.SOAPFaultException;
import java.rmi.RemoteException;

/**
 *
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public interface MedRecWebServices extends EJBObject {

  public void addRecord(RecordWS record)
    throws RemoteException, SOAPFaultException;

  public RecordWS getRecord(Integer id)
    throws RemoteException, SOAPFaultException;

  public RecordsSummaryWS getRecordsSummary(Integer id)
    throws RemoteException, SOAPFaultException;

  public PatientWS[] findPatientByLastNameWild(String lastName)
    throws RemoteException, SOAPFaultException;

  public PatientWS findPatientBySsn(String id)
    throws RemoteException, SOAPFaultException;

  public void registerPatient(PatientWS patient, String password)
    throws RemoteException, SOAPFaultException;

  public void updatePatient(PatientWS patient)
    throws RemoteException, SOAPFaultException;
}
