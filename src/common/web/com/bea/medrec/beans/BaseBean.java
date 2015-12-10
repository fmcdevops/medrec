package com.bea.medrec.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.struts.validator.ValidatorForm;

/**
 * <p>Base bean encapsulating common bean attributes and functionality.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
abstract public class BaseBean extends ValidatorForm {
  private static Logger logger = Logger.getLogger(BaseBean.class.getName());

  // Instance Variables
  private String action = "";
  private String id = "";

  public BaseBean() {
  }

  // Getters
  public String getAction() {
    return this.action;
  }

  public String getId() {
    return this.id;
  }

  // Setters
  public void setAction(String action) {
    this.action = action;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setId(Integer id) {
    this.id = toStr(id);
  }

  // Utility
  protected String setDateStr(String str) {
    SimpleDateFormat sdf = null;
    String dateStr = "";
    try {
      sdf = new SimpleDateFormat("MM/dd/yyyy");
      Date d = sdf.parse(str);
      dateStr = sdf.format(d);
    } catch (ParseException e) {
    }
    return dateStr;
  }

  protected static String toStr(Object obj) {
    if (obj != null)
      return obj.toString();
    else
      return null;
  }

  // Validation
  /**
   * <p>String null check.</p>
   *
   * @param str
   */
  protected boolean isNotEmpty(String str) {
    return str != null && str.length() > 0;
  }

  /**
   * <p>String null check.</p>
   *
   * @param str
   */
  protected boolean isEmpty(String str) {
    return !(isNotEmpty(str));
  }

  /**
   * <p>Check for valid social security number. Format: 999999999</p>
   *
   * @param ssn
   * @return boolean
   */
  protected boolean isValidSsn(String ssn) {
    boolean valid = true;
    if (isEmpty(ssn)) {
      logger.warn("SSN: invalid");
      valid = false;
    } else if (ssn.length() != 9) {
      logger.warn("SSN: invalid");
      valid = false;
    }
    return valid;
  }
}
