package com.bea.medrec.webservices.swing;

import com.bea.medrec.utils.MedRecUtils;
import com.bea.medrec.value.Address;
import com.bea.medrec.value.Patient;
import com.bea.medrec.webservices.AddressWS;
import com.bea.medrec.webservices.PatientWS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * <p>Util Class for Swing Client</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class Utils {
  private static Logger logger = Logger.getLogger(Utils.class.getName());


  /**
   * <p>Takes an address value object and return an AddressWS</p>
   *
   * @param pAddress Address to be converted to an AddressWS
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

  /**
   * <p>Take a Patient and return a PatientWS</p>
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
       patientWS.setDateOfBirth(MedRecUtils.cal2Str(pPatient.getDateOfBirth()));
       patientWS.setGender(pPatient.getGender());
       patientWS.setSsn(pPatient.getSsn());
       patientWS.setPhone(pPatient.getPhone());
       patientWS.setEmail(pPatient.getEmail());
       patientWS.setAddressWS(toAddressWS(pPatient.getAddress()));
     }
     return patientWS;
  }

  /**
   * <p>Take a string and return a Calendar object</p>
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

 /**
  * <p>Check for valid date, ignoring delimitator. Format: MM/DD/YYYY</p>
  *
  * @param date
  * @return boolean
  */
  public static boolean isValidDate(String date)
  {
    boolean valid = true;
    try {
      if (date.length() != 10) return false;
      String m = date.substring(0,2);
      String d = date.substring(3,5);
      String y = date.substring(6,10);
      Integer.parseInt(m);
      Integer.parseInt(d);
      Integer.parseInt(y);
    } catch(Exception e) {
      return false;
    }
    return valid;
  }

         //   D A T E   M A N I P U L A T I O N
 /**
  * <p>Format: MM/DD/YYYY</p>
  *
  * @param String
  * @return String
  */
  public static String getDisplayDate(String pDate)
  {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    Calendar calendar = str2Calendar(pDate);
    if (calendar != null) return format.format(calendar.getTime());
    else return "";
  }

}
