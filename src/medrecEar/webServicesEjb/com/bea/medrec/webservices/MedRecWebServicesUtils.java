package com.bea.medrec.webservices;

import com.bea.medrec.value.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * MedRec web service utility class.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecWebServicesUtils
{
  private static Logger logger =
    Logger.getLogger(MedRecWebServicesUtils.class.getName());

      //   A D D R E S S
  /**
  * <p>Convert Web service address to an internal address representation.</p>
  *
  * @param pAddressWS AddressWS
  * @return Address
  */
  public static Address toAddress(AddressWS pAddressWS)
  {
    Address address = null;
    if (pAddressWS != null) {
      logger.debug(pAddressWS.toString());
      address = new Address(pAddressWS.getId(),
                            pAddressWS.getStreetName1(),
                            pAddressWS.getStreetName2(),
                            pAddressWS.getCity(),
                            pAddressWS.getState(),
                            pAddressWS.getZipCode(),
                            pAddressWS.getCountry());
    }
    return address;
  }

 /**
  * <p>Convert internal address representation to
  * Web service address.</p>
  *
  * @param pAddress
  * @return AddressWS
  */
  public static AddressWS toAddressWS(Address pAddress)
  {
    AddressWS addressWS = null;
    if (pAddress != null) {
      logger.debug(pAddress.toString());
      addressWS = new AddressWS();
      addressWS.setId(pAddress.getId());
      addressWS.setStreetName1(pAddress.getStreetName1());
      addressWS.setStreetName2(pAddress.getStreetName2());
      addressWS.setCity(pAddress.getCity());
      addressWS.setState(pAddress.getState());
      addressWS.setZipCode(pAddress.getZipCode());
      addressWS.setCountry(pAddress.getCountry());
    }
    return addressWS;
  }

      //   P A T I E N T
 /**
  * <p>Convert Web service patient to an internal patient representation.</p>
  *
  * @param pPatientWS
  * @return Patient
  */
  public static Patient toPatient(PatientWS pPatientWS)
  {
    Patient patient = null;
    if (pPatientWS != null) {
      logger.debug(pPatientWS.toString());
      patient = new Patient(pPatientWS.getId(),
                            pPatientWS.getFirstName(),
                            pPatientWS.getMiddleName(),
                            pPatientWS.getLastName(),
                            str2Calendar(pPatientWS.getDateOfBirth()),
                            pPatientWS.getGender(),
                            pPatientWS.getSsn(),
                            pPatientWS.getPhone(),
                            pPatientWS.getEmail(),
                            toAddress(pPatientWS.getAddressWS()));
    }
    return patient;
  }

 /**
  * <p>Convert array of Web service patients to an
  * internal collection of patients representation.</p>
  *
  * @param pPatients
  * @return Collection Collection of Records
  */
  public static Collection toPatientCollection(PatientWS[] pPatients)
  {
    Collection array = new ArrayList();
    if (pPatients != null && pPatients.length > 0) {
      logger.debug("Num of patients: "+pPatients.length);
      for (int i=0; i<pPatients.length; i++) {
        Patient patient = toPatient(pPatients[i]);
        logger.debug(patient.toString());
        array.add(patient);
      }
    }
    return array;
  }

  /**
   * <p>Convert Web service patient to a collection
   * of internal patient representation.</p>
   *
   * @param pPatient
   * @return Collection Collection of Records
   */
   public static Collection toPatientCollection(PatientWS pPatient)
   {
     Collection array = new ArrayList();
     if (pPatient != null) {
       Patient patient = toPatient(pPatient);
       logger.debug(patient.toString());
       array.add(patient);
     }
     return array;
   }


 /**
  * <p>Convert collection of records representation to
  * array of Web service records.</p>
  *
  * @param pPatients  Collection of patients
  * @return PatientWS[]
  */
  public static PatientWS[] toPatientWSArray(Collection pPatients)
  {
    PatientWS[] patientsWS = null;
    if (pPatients != null) {
      logger.debug("size: "+pPatients.size());
      patientsWS = new PatientWS[pPatients.size()];
      Iterator itr = pPatients.iterator();
      int i=0;
      while (itr.hasNext()) {
        patientsWS[i++] = toPatientWS((Patient)itr.next());
      }
    }
    return patientsWS;
  }

 /**
  * <p>Convert internal patient representation to
  * Web service patient.</p>
  *
  * @param pPatient Patient Value Object
  * @return PatientWS
  */
  public static PatientWS toPatientWS(Patient pPatient)
  {
    PatientWS patientWS = null;
    if (pPatient != null) {
      logger.debug(pPatient.toString());
      patientWS = new PatientWS();
      patientWS.setId(pPatient.getId());
      patientWS.setFirstName(pPatient.getFirstName());
      patientWS.setMiddleName(pPatient.getMiddleName());
      patientWS.setLastName(pPatient.getLastName());
      patientWS.setDateOfBirth(cal2Str(pPatient.getDateOfBirth()));
      patientWS.setGender(pPatient.getGender());
      patientWS.setSsn(pPatient.getSsn());
      patientWS.setPhone(pPatient.getPhone());
      patientWS.setEmail(pPatient.getEmail());
      patientWS.setAddressWS(toAddressWS(pPatient.getAddress()));
    }
    return patientWS;
  }

      //   P R E S C R I P T I O N
 /**
  * <p>Convert Web service address to an internal address representation.</p>
  *
  * @param pRxWS
  * @return PrescriptionBean
  */
  public static Prescription toPrescription(PrescriptionWS pRxWS)
  {
    Prescription rX = null;
    if (pRxWS != null) {
      logger.debug(pRxWS.toString());
      rX = new Prescription(pRxWS.getPatientId(),
                            pRxWS.getPhysicianId(),
                            pRxWS.getRecordId(),
                            str2Calendar(pRxWS.getDatePrescribed()),
                            pRxWS.getDrug(),
                            pRxWS.getDosage(),
                            pRxWS.getFrequency(),
                            pRxWS.getRefillsRemaining(),
                            pRxWS.getInstructions());
    }
    return rX;
  }

 /**
  * <p>Convert internal prescription representation
  * to Web service prescription.</p>
  *
  * @param pRx
  * @return PrescriptionBean
  */
   public static PrescriptionWS toRxWS(Prescription pRx)
   {
     PrescriptionWS rXWS = null;
     if (pRx != null) {
       logger.debug(pRx.toString());
       rXWS = new PrescriptionWS();
       rXWS.setId(pRx.getId());
       rXWS.setPatientId(Integer.valueOf(pRx.getPatientId()));
       rXWS.setPhysicianId(Integer.valueOf(pRx.getPhysicianId()));
       rXWS.setDatePrescribed(cal2Str(pRx.getDatePrescribed()));
       rXWS.setDrug(pRx.getDrug());
       rXWS.setDosage(pRx.getDosage());
       rXWS.setFrequency(pRx.getFrequency());
       rXWS.setRefillsRemaining(pRx.getRefillsRemaining());
       rXWS.setInstructions(pRx.getInstructions());
     }
     return rXWS;
   }

 /**
  * <p>Convert array of Web service prescriptions to an
  * internal collection of prescriptions representation.</p>
  *
  * @param pRxWS
  * @return Collection Collection of Records
  */
  public static Collection toRxCollection(PrescriptionWS[] pRxWS)
  {
    Collection rX = new ArrayList();
    if (pRxWS != null) {
      logger.debug("PrescriptionWS[] len: "+pRxWS.length);
      for (int i=0; i<pRxWS.length; i++) {
        rX.add(toPrescription(pRxWS[i]));
      }
    }
    return rX;
  }

 /**
  * <p>Convert collection of prescriptions representation to
  * array of Web service prescriptions.</p>
  *
  * @param pRxs  Collection of Prescriptions
  * @return PrescriptionWS[]
  */
  public static PrescriptionWS[] toRxWSArray(Collection pRxs)
  {
    PrescriptionWS[] rxWS = null;
    if (pRxs != null) {
      logger.debug("size: "+pRxs.size());
      rxWS = new PrescriptionWS[pRxs.size()];
      Iterator itr = pRxs.iterator();
      int i=0;
      while (itr.hasNext()) {
        rxWS[i++] = toRxWS((Prescription)itr.next());
       }
    }
    return rxWS;
  }

      //   R E C O R D
 /**
  * <p>Convert Web service record to an
  * internal record representation.</p>
  *
  * @param pRecordWS
  * @return RecordWS
  */
  public static Record toRecord(RecordWS pRecordWS)
  {
    Record record = null;
    if (pRecordWS != null) {
      logger.debug(pRecordWS.toString());
      record = new Record(pRecordWS.getId(),
                          pRecordWS.getPatientId(),
                          pRecordWS.getPhysicianId(),
                          str2Calendar(pRecordWS.getDate()),
                          pRecordWS.getSymptoms(),
                          pRecordWS.getDiagnosis(),
                          pRecordWS.getNotes(),
                          toVitalSigns(pRecordWS.getVitalSigns()),
                          toRxCollection(pRecordWS.getPrescriptions()));
    }
    return record;
  }

 /**
  * <p>Convert array of Web service records to an
  * internal collection of records representation.</p>
  *
  * @param pRecordWS
  * @return Collection Collection of Records
  */
  public static Collection toRecordCollection(RecordWS[] pRecordWS)
  {
    Collection records = null;
    if (pRecordWS != null) {
      records = new ArrayList();
      logger.debug("RecordWS[] len: "+pRecordWS.length);
      for (int i=0; i<pRecordWS.length; i++) {
        records.add(toRecordLite(pRecordWS[i]));
      }
    }
    return records;
  }

 /**
  * <p>Convert Web service address to an internal address representation.</p>
  *
  * @param pRecordWS
  * @return RecordWS
  */
  public static Record toRecordLite(RecordWS pRecordWS)
  {
    Record record = null;
    if (pRecordWS != null) {
      logger.debug(pRecordWS.toString());
      record = new Record();
      record.setId(pRecordWS.getId());
      record.setDate(str2Calendar(pRecordWS.getDate()));
      record.setSymptoms(pRecordWS.getSymptoms());
      record.setDiagnosis(pRecordWS.getDiagnosis());
    }
    return record;
  }

 /**
  * <p>Convert internal record representation
  * to Web service record.</p>
  *
  * @param pRecord
  * @return RecordWS
  */
  public static RecordWS toRecordWS(Record pRecord)
  {
    RecordWS recordWS = null;
    if (pRecord != null) {
      logger.debug(pRecord.toString());
      recordWS = new RecordWS();
      recordWS.setId(pRecord.getId());
      recordWS.setPatientId(pRecord.getPatientId());
      recordWS.setPhysicianId(pRecord.getPhysicianId());
      recordWS.setDate(cal2Str(pRecord.getDate()));
      recordWS.setSymptoms(pRecord.getSymptoms());
      recordWS.setDiagnosis(pRecord.getDiagnosis());
      recordWS.setNotes(pRecord.getNotes());
      recordWS.setVitalSigns(toVitalSignsWS(pRecord.getVitalSigns()));
      recordWS.setPrescriptions(toRxWSArray(pRecord.getPrescriptions()));
    }
    return recordWS;
  }

 /**
  * <p>Convert collection of record representation to
  * array of Web service record.</p>
  *
  * @param pRecords  Collection of Records
  * @return RecordWS[]
  */
  public static RecordWS[] toRecordWSArray(Collection pRecords)
  {
    RecordWS[] recWS = null;
    if (pRecords != null) {
      recWS = new RecordWS[pRecords.size()];
      Iterator itr = pRecords.iterator();
      int i=0;
      while (itr.hasNext()) {
        recWS[i++] = toRecordWSLite((Record)itr.next());
      }
    }
    return recWS;
  }

 /**
  * <p>Convert internal record representation to
  * Web service record lite.</p>
  *
  * @param pRecord
  * @return RecordWS
  */
  public static RecordWS toRecordWSLite(Record pRecord)
  {
    RecordWS recordWS = null;
    if (pRecord != null) {
      logger.debug(pRecord.toString());
      recordWS = new RecordWS();
      recordWS.setId(pRecord.getId());
      recordWS.setDate(cal2Str(pRecord.getDate()));
      recordWS.setSymptoms(pRecord.getSymptoms());
      recordWS.setDiagnosis(pRecord.getDiagnosis());
    }
    return recordWS;
  }

      //   R E C O R D    S U M M A R Y
 /**
  * <p>Convert internal record summary representation to
  * Web service record summary.</p>
  *
  * @param pRecSummary
  * @return RecordsSummaryWS
  */
  public static RecordsSummaryWS toRecordsSummaryWS(RecordsSummary pRecSummary)
  {
    RecordsSummaryWS recSummaryWS = null;
    if (pRecSummary != null) {
      logger.debug(pRecSummary.toString());
      recSummaryWS = new RecordsSummaryWS();
      recSummaryWS.setRecords(toRecordWSArray(pRecSummary.getRecords()));
      recSummaryWS.setPrescriptions(toRxWSArray(pRecSummary.getPrescriptions()));
    }
    return recSummaryWS;
  }

 /**
  * <p>Convert Web service record summary
  * to an internal record summary representation.</p>
  *
  * @param pRecSummaryWS
  * @return RecordsSummary
  */
  public static RecordsSummary toRecordsSummary(RecordsSummaryWS pRecSummaryWS)
  {
    RecordsSummary recSummary = null;
    if (pRecSummaryWS != null) {
      logger.debug(pRecSummaryWS.toString());
      recSummary = new RecordsSummary(toRecordCollection(pRecSummaryWS.getRecords()),
                                      toRxCollection(pRecSummaryWS.getPrescriptions()));
    }
    return recSummary;
  }
      //   V I T A L    S I G N S
 /**
  * <p>Convert Web service vital signs to
  * an internal vital signs representation.</p>
  *
  * @param pVitalSignsWS
  * @return VitalSignsWS
  */
  public static VitalSigns toVitalSigns(VitalSignsWS pVitalSignsWS)
  {
    VitalSigns vitalSigns = null;
    if (pVitalSignsWS != null) {
      logger.debug(pVitalSignsWS.toString());
      vitalSigns =  new VitalSigns(pVitalSignsWS.getTemperature(),
                                   pVitalSignsWS.getBloodPressure(),
                                   pVitalSignsWS.getPulse(),
                                   pVitalSignsWS.getWeight(),
                                   pVitalSignsWS.getHeight());
    }
    return vitalSigns;
  }

 /**
  * <p>Convert internal vital signs representation to
  * Web service vital signs.</p>
  *
  * @param pVitalSigns
  * @return VitalSignsWS
  */
  public static VitalSignsWS toVitalSignsWS(VitalSigns pVitalSigns)
  {
    VitalSignsWS vitalSignsWS = null;
    if (pVitalSigns != null) {
      logger.debug(pVitalSigns.toString());
      vitalSignsWS = new VitalSignsWS();
      vitalSignsWS.setId(pVitalSigns.getId());
      vitalSignsWS.setTemperature(pVitalSigns.getTemperature());
      vitalSignsWS.setBloodPressure(pVitalSigns.getBloodPressure());
      vitalSignsWS.setPulse(pVitalSigns.getPulse());
      vitalSignsWS.setWeight(pVitalSigns.getWeight());
      vitalSignsWS.setHeight(pVitalSigns.getHeight());
    }
    return vitalSignsWS;
  }

      //   U T I L I T Y   M E T H O D S
 /**
  * <p>Gets string representation of given Calendar.</p>
  */
  private static String cal2Str(Calendar pCal)
  {
    StringBuffer str = new StringBuffer();
    if (pCal != null) {
      int month = pCal.get(Calendar.MONTH)+1;
      int date = pCal.get(Calendar.DATE);
      int year = pCal.get(Calendar.YEAR);
      str.append(month);
      str.append("/"+date);
      str.append("/"+year);
    }

    return str.toString();
  }

 /**
  * <p>Converts a string to a Calendar object</p>
  *
  * @param pString A string representing a Calendar
  * @return Calendar
  */
  public static Calendar str2Calendar(String pString)
  {
    Calendar cal = null;
    if (pString != null) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date d = sdf.parse(pString);
        cal = Calendar.getInstance();
        cal.setTime(d);
      }
      catch(ParseException e) {   }
    }
    return cal;
  }
}
