package com.bea.medrec.utils;

/**
 * <p>Constants used throughout MedRec.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecConstants {
  // Gender
  public static final String MALE = "Male";
  public static final String FEMALE = "Female";

  // Patient/Physician Status
  public static final String USER_NEW       = "NEW";
  public static final String USER_DENIED    = "DENIED";
  public static final String USER_ACTIVE    = "ACTIVE";
  public static final String USER_INACTIVE  = "INACTIVE";
  public static final String USER_DECEASED  = "DECEASED";

  // Assigned password
  public static final String TEMP_PASSWORD  = "weblogic";

  // XML parsing
  public static final String MEDICAL_RECORD = "medicalRecord";
  public static final String SRC_ID         = "srcId";
  public static final String SRC_NAME       = "srcName";
  public static final String MEDICAL_VISIT  = "medicalVisit";
  public static final String PATIENT        = "patient";
  public static final String PATIENT_NAME   = "patientName";
  public static final String FIRST_NAME     = "firstName";
  public static final String MIDDLE_NAME    = "middleName";
  public static final String LAST_NAME      = "lastName";
  public static final String SSN            = "ssn";
  public static final String DOB            = "dob";
  public static final String GENDER         = "gender";
  public static final String EMAIL          = "email";
  public static final String PHONE          = "phone";
  public static final String ADDRESS        = "address";
  public static final String STREET1        = "streetName1";
  public static final String STREET2        = "streetName2";
  public static final String CITY           = "city";
  public static final String STATE          = "state";
  public static final String ZIP            = "zip";
  public static final String COUNTRY        = "country";
  public static final String RECORD         = "record";
  public static final String DATE           = "date";
  public static final String SYMPTOMS       = "symptoms";
  public static final String DIAGNOSIS      = "diagnosis";
  public static final String NOTES          = "notes";
  public static final String VITALSIGNS     = "vitalSigns";
  public static final String WEIGHT         = "weight";
  public static final String HEIGHT         = "height";
  public static final String TEMP           = "temperature";
  public static final String PULSE          = "pulse";
  public static final String BLDPRESS       = "bloodPressure";
  public static final String SYSTOLIC       = "systolic";
  public static final String DIASTOLIC      = "diastolic";
  public static final String PRESCRIPTION   = "rx";
  public static final String DRUG           = "drug";
  public static final String DOSAGE         = "dosage";
  public static final String FREQUENCY      = "frequency";
  public static final String REFILLS        = "refills";
  public static final String INSTRUCTIONS   = "instructions";
  public static final String AMOUNT         = "amount";
  public static final String UOM            = "uom";

  // MedRec groups
  public static final int ADMIN_GROUP_TYPE        = 1;
  public static final int PATIENT_GROUP_TYPE      = 2;
  public static final int PHYSICIAN_GROUP_TYPE    = 3;
  public static final String ADMIN_GROUP_NAME     = "MedRecAdmins";
  public static final String PATIENT_GROUP_NAME   = "MedRecPatients";
  public static final String PHYSICIAN_GROUP_NAME = "MedRecPhysicians";

  // Mail
  public static final String MEDREC_CUSTOMER_SUPPORT =
    "customer_support@avitek.com";
}

