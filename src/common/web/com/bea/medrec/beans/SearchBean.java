package com.bea.medrec.beans;

import com.bea.medrec.value.Search;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

/**
 * Form bean for the patient search page.
 * This form has the following fields:
 * <ul>
 * <li><b>last name</b> - Last name search option
 * <li><b>ssn</b> - Social security number search option
 * </ul>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class SearchBean extends BaseBean {
  private static Logger logger = Logger.getLogger(SearchBean.class.getName());

  // Instance Variables
  private String lastName = "";
  private String ssn = "";

  // Constuctors
  public SearchBean() {
  }

  public SearchBean(String lastName, String ssn) {
    this.lastName = lastName;
    this.ssn = ssn;
  }

  // Getters
  public String getLastName() {
    return this.lastName;
  }

  public String getSsn() {
    return this.ssn;
  }

  // Setters
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  /**
   * <p>Validate search.</p>
   *
   * @return ActionErrors
   */
  public ActionErrors validate() {
    logger.debug("Validating search bean.");
    logger.debug(toString());

    ActionErrors errors = new ActionErrors();

    if ((isEmpty(lastName) && isEmpty(ssn))
        || (isNotEmpty(lastName) && isNotEmpty(ssn))) {
      logger.warn("Entires emtpy or all populated.");
      errors.add("multipleEntries", new ActionError("enter.one.option.only"));
    } else {
      if (isEmpty(lastName) && !isValidSsn(ssn)) {
        errors.add("ssn", new ActionError("invalid.ssn"));
      }
    }

    if (!errors.isEmpty()) {
      logger.warn("SearchBean invalid. " + errors.size() + " errors found.");
    }

    return errors;
  }

  /**
   * <p>Converts search presentation bean to search value object.</p>
   *
   * @return RecordWS
   */
  public Search toSearch() {
    return new Search(getLastName(), getSsn());
  }

  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("Search [");
    str.append("LastName: " + lastName);
    str.append(" | SSN: " + ssn);
    str.append("]");

    return str.toString();
  }

}
