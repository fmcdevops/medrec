package com.bea.medrec.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Utility methods used by web applications.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecWebAppUtils
{
  private static Logger logger =
    Logger.getLogger(MedRecWebAppUtils.class.getName());

      //   L O C A L I Z A T I O N
 /**
  * <p>Get locale cookie.</p>
  *
  * @param request
  * @return Locale
  */
  public static Locale getLocaleFromCookie(HttpServletRequest request) {
    Locale locale = null;
    Cookie cookie = null;
    String country = null;
    String language = null;
    Cookie[] cookies = request.getCookies();

    // Start locale processing.
    if (cookies != null) {
      for (int i=0; i < cookies.length; i++) {
        cookie = cookies[i];
        if ("Language".equals(cookie.getName())) {
          language = cookie.getValue();
          logger.debug("Found language cookie with value = "+language);
        } else if (cookie.getName().equals("Country")) {
          country = cookie.getValue();
          logger.debug("Found country cookie with value = "+country);
        }
      }
    }

    if (country != null && language != null) {
      locale = new Locale(language, country);
    }

    return locale;
  }

 /**
  * <p>String null check.</p>
  *
  * @param locale
  * @return boolean
  */
  public static boolean isValidLocale(Locale locale) {
    return (locale.getLanguage().equals("ja")
            || locale.getLanguage().equals("en")
            || locale.getLanguage().equals("es"));
  }

      //   S T R I N G   M A N I P U L A T I O N
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
  * <p>Return trim, non-null representation of object.</p>
  *
  * @param obj
  * @return String
  */
  public static String cleanParam(Object obj) {
    String str = String.valueOf(obj);
    if (str == null || str.equals("null")) str = "";
    return str.trim();
  }

 /**
  * <p>Check for valid string, no special chars including:
  * "#","&","^","%","*","/","\\","(",")"</p>
  *
  * @param str
  * @return boolean
  */
  public static boolean isValidString(String str) {
    boolean valid = true;
    if (valid) {
      String invalid[] = {"#","&","^","%","*","/","\\","(",")"};
      for(int i = 0; i < invalid.length; i++) {
       if(str.indexOf(invalid[i]) >= 0) { valid = false; }
      }
    }
    return valid;
  }

 /**
  * <p>Trim textbox to 40 chars.  Remaining display with elipse.</p>
  *
  * @param str
  * @return String
  */
  public static String trimToSummaryStr(String str) {
    int endLength = 40;

    if (isEmpty(str)) return str;

    if (str.length() > endLength) {
      String tmpStr = str.substring(0, str.indexOf(" ", endLength));
      tmpStr += "...";
      return tmpStr;
    }
    else {
      return str;
    }
  }

         //   D A T E   M A N I P U L A T I O N
 /**
  * <p>String representation of a calendar. Format: MM/DD/YYYY</p>
  *
  * @param pCalendar
  * @return String
  */
  public static String getDisplayDate(Calendar pCalendar) {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    if (pCalendar != null) return format.format(pCalendar.getTime());
    else return "";
  }

 /**
  * <p>Convert string representation of a date to calendar.</p>
  *
  * @param str
  * @return Calendar
  */
  public static Calendar str2Calendar(String str) {
    Calendar cal = null;
    if (!isEmpty(str)) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date d = sdf.parse(str);
        cal = Calendar.getInstance();
        cal.setTime(d);
      }
      catch(ParseException e) {   }
    }
    return cal;
  }

 /**
  * <p>String representation of current date. Format: MM/DD/YYYY</p>
  *
  * @return String
  */
  public static String getCurrentDate() {
    return getDisplayDate(GregorianCalendar.getInstance());
  }

        //   M I S C E L L A N E O U S
 /**
  * <p>Print collection contents.</p>
  *
  * @param col
  * @param type
  * @return String
  */
  public static String col2Str(Collection col, String type) {
    StringBuffer str = new StringBuffer();
    if (col != null && !col.isEmpty()) {
      str.append(" Num of "+type+": "+col.size()+" |");
      Iterator itr = col.iterator();
      while(itr.hasNext()) {
        Object obj = itr.next();
        str.append(" "+obj.toString());
      }
    }
    else {
      str.append(type+": [null]");
    }
    return str.toString();
  }

 /**
  * <p>Get HTPP method- POST or GET. This is used to debug the query string.</p>
  *
  * @return String
  */
  public static String getHttpMethod() {
    if (logger.isDebugEnabled()) return "GET";
    else return "POST";
  }

 /**
  * <p>Get full url path,
  * i.e. http://localhost:port/<webapp context>
  * </p>
  *
  * @param request
  * @return String
  */
  public static String getUrlPath(HttpServletRequest request) {
    return getUrlRoot(request)+request.getContextPath();
  }

 /**
  * <p>Get localhost and port url root, i.e. http://localhost:port</p>
  *
  * @param request
  * @return String
  */
  public static String getUrlRoot(HttpServletRequest request) {
    String serverName = request.getServerName();
    int serverPort = request.getServerPort();
    String nextPage = "http://"+serverName+":"+serverPort;
    logger.debug("URL root: "+nextPage);
    return nextPage;
  }

 /**
  * <p>Get servlet name from given mapping.</p>
  *
  * @param mapping
  * @param name
  * @return String
  */
  public static String getFullUrlPath(HttpServletRequest request,
                                      ActionMapping mapping, String name)
  {
    return getUrlPath(request)+"/"+getServletName(mapping, name);
  }

 /**
  * <p>Get servlet name from given mapping.</p>
  *
  * @param mapping
  * @param name
  * @return String
  */
  public static String getServletName(ActionMapping mapping, String name) {
    ActionForward forward = mapping.findForward(name);
    String path = forward.getPath();
    return path.substring(path.indexOf("/")+1);
  }

 /**
  * <p>Get full URL of MedRec start page (http://localhost:port/start.jsp).</p>
  *
  * @param mapping
  * @return String
  */
  public static String getMedRecHomeURL(ActionMapping mapping,
                                        HttpServletRequest request)
  {
    String homePage = MedRecWebAppUtils.getServletName(mapping,
      "medrec.startpage");
    return MedRecWebAppUtils.getUrlRoot(request)+"/"+homePage;
  }

 /**
  * <p>Parse exception stack finding the orignial exception message.</p>
  *
  * @param th
  * @return String
  */
  public static String getRootErrMsg(Throwable th) {
    if (th.getCause() != null)
      return getRootError(th.getCause()).getLocalizedMessage();
    else
      return th.getLocalizedMessage();
  }

 /**
  * <p>Parse exception stack finding the orignial exception.</p>
  *
  * @param th
  * @return Throwable
  */
  public static Throwable getRootError(Throwable th) {
    if (th instanceof javax.ejb.EJBException) {
      Throwable nested = ((javax.ejb.EJBException)th).getCausedByException();
      if (nested != null) return getRootError(nested);
      else return th;
    }
    if (th instanceof java.rmi.RemoteException) {
      Throwable nested = th.getCause();
      if (nested != null) return getRootError(nested);
      else return th;
    }
    if (th instanceof java.rmi.AccessException) {
      Throwable nested = th.getCause();
      if (nested != null) return getRootError(nested);
      else return th;
    }
    if (th instanceof java.sql.SQLException) {
      Throwable nested = th.getCause();
      if (nested != null) return getRootError(nested);
      else return th;
    }
    if (th instanceof weblogic.webservice.tools.wsdlp.WSDLParseException) {
      Throwable nested = th.getCause();
      if (nested != null) return getRootError(nested);
      else return th;
    }
    if (th instanceof com.bea.medrec.exceptions.MedRecException) {
      Throwable nested = th.getCause();
      if (nested != null) return getRootError(nested);
      else return th;
    }
    else return th;
  }
}
