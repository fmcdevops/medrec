package com.bea.medrec.utils;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 * <p>Utility methods used through MedRec.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecUtils
{
  private static Logger logger = Logger.getLogger(MedRecUtils.class.getName());

 /**
  * <p>String null check.</p>
  *
  * @param str
  */
  public static boolean isNotEmpty(String str) {
    return str != null && str.length() > 0;
  }

 /**
  * <p>String null check.</p>
  *
  * @param str
  */
  public static boolean isEmpty(String str) { return !(isNotEmpty(str)); }

 /**
  * <p>Convert Date to Calendar.</p>
  *
  * @param date
  * @return Calendar
  */
  public static Calendar convertSqlDate2Calendar(java.sql.Date date)
  {
    Calendar cal = null;
    if (date != null) {
      cal = GregorianCalendar.getInstance();
      cal.setTime(date);
    }
    return cal;
  }

 /**
  * <p>String date representation of a converted long date.</p>
  *
  * @param pLong
  * @return String
  */
  public static String long2DateStr(long pLong)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(pLong);
    return getDisplayDate(cal);
  }

 /**
  * <p>Get common display date from Calendar.</p>
  *
  * @param pCalendar
  * @return String
  */
  public static String getDisplayDate(Calendar pCalendar)
  {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    if (pCalendar != null) return format.format(pCalendar.getTime());
    else return "";
  }

 /**
  * <p>Print contents of collection.</p>
  *
  * @param col
  */
  public static void printCollection(Collection col)
  {
    if (col != null) {
      Iterator itr = col.iterator();
      logger.debug("#: "+col.size());
      while(itr.hasNext()) {
        logger.debug(itr.next().toString());
        System.out.println(itr.next().toString());
      }
    }
  }

 /**
  * <p>Get Date from Calendar.</p>
  *
  * @param pCal
  */
  public static Date getDate(Calendar pCal)
  {
    java.util.Date date = pCal.getTime();
    return new java.sql.Date(date.getTime());
  }

 /**
  * <p>Group assignments.</p>
  *
  * @param groupType
  * @return String[]
  */
  public static String[] getGroupArray(int groupType)
  {
    String[] groupNames = null;

    switch (groupType) {
      case MedRecConstants.ADMIN_GROUP_TYPE:
        groupNames = new String[1];
        groupNames[0] = MedRecConstants.ADMIN_GROUP_NAME;
        return groupNames;
      case MedRecConstants.PATIENT_GROUP_TYPE:
        groupNames = new String[1];
        groupNames[0] = MedRecConstants.PATIENT_GROUP_NAME;
        return groupNames;
      case MedRecConstants.PHYSICIAN_GROUP_TYPE:
        groupNames = new String[1];
        groupNames[0] = MedRecConstants.PHYSICIAN_GROUP_NAME;
        return groupNames;
      default           :
          return groupNames;
    }
  }

  //   U T I L I T Y   M E T H O D S
  /**
   * <p>Convert Calendar to String.</p>
   */
  public static String cal2Str(Calendar pCal)
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
}
