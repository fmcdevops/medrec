package com.bea.medrec.utils;

/**
 * <p>Error constants.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ErrorConstants {

  // Generic Messages
  public static final String SYS_UNVAIL =
    "System unavailable.  Please try again.";

  // EJBs
  public static final String ADMIN_NOT_FOUND = "Admin not found.";
  public static final String USER_NOT_FOUND = "User not found.";
  public static final String PATIENT_NOT_FOUND = "Patient not found.";

  // XML
  public static final String XML_PROCESS_ERROR =
    "Unable to process XML document.  Please try again.";
  public static final String XML_ERRONEOUS_DATA =
    "Erroneous data found within XML document.  Please fix and try again.";
  public static final String XML_ANOMALY =
    "Anomaly found in XML document.  Please fix the XML and try again.";
  public static final String XML_INVALID =
    "Invalid start element.  Cannot handle this xml document.";

}

