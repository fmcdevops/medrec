package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecMessageProperties;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.value.Patient;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Form bean for the register and patient webapps.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>firstName</b> - Entered firstName value
 * <li><b>middleName</b> - Entered firstName value
 * <li><b>lastName</b> - Entered firstName value
 * <li><b>dob</b> - Entered date of birth value
 * <li><b>ssn</b> - Entered social security number value
 * <li><b>email</b> - Entered email value
 * <li><b>phone</b> - Entered phone value
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class PatientBean extends BaseBean {
  private static Logger logger = Logger.getLogger(PatientBean.class.getName());

  // Gender
  public static final String MALE = "Male";
  public static final String FEMALE = "Female";

  // Instance Variables
  private String firstName = null;;
  private String middleName = null;;
  private String lastName = null;;
  private String dob = null;;
  private String gender = null;;
  private String ssn = null;;
  private String phone = null;;
  private String email = null;;
  private AddressBean address = new AddressBean();

  // Constructors
  public PatientBean() {
  }

  public PatientBean(Patient patient) {
    String displayDate =
        MedRecWebAppUtils.getDisplayDate(patient.getDateOfBirth());
    super.setId(patient.getId());
    this.firstName = patient.getFirstName();
    this.middleName = patient.getMiddleName();
    this.lastName = patient.getLastName();
    this.dob = displayDate;
    this.gender = patient.getGender();
    this.ssn = patient.getSsn();
    this.phone = patient.getPhone();
    this.email = patient.getEmail();
    this.address = new AddressBean(patient.getAddress());
  }

  public PatientBean(String id,
                     String firstName,
                     String middleName,
                     String lastName,
                     String dob,
                     String gender,
                     String ssn,
                     String phone,
                     String email,
                     AddressBean address) {
    super.setId(id);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dob = dob;
    this.gender = gender;
    this.ssn = ssn;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  public PatientBean(Integer id,
                     String firstName,
                     String middleName,
                     String lastName,
                     String dob,
                     String gender,
                     String ssn,
                     String phone,
                     String email,
                     AddressBean address) {
    super.setId(id);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dob = dob;
    this.gender = gender;
    this.ssn = ssn;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  // Getters
  public String getFirstName() {
    return this.firstName;
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getDob() {
    return this.dob;
  }

  public String getGender() {
    return this.gender;
  }

  public String getSsn() {
    return this.ssn;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getEmail() {
    return this.email;
  }

  public AddressBean getAddress() {
    return this.address;
  }

  // Setters
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDob(String dob) {
    this.dob = setDateStr(dob);
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAddress(AddressBean address) {
    this.address = address;
  }

  // Public Methods
  public void reset() {
    this.firstName = null;
    this.middleName = null;
    this.lastName = null;
    this.dob = null;
    this.gender = null;
    this.ssn = null;
    this.phone = null;
    this.email = null;
  }

  /**
   * <p>Validate patient.</p>
   *
   * @param mapping
   * @param request
   *
   * @return ActionErrors
   */
  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    ActionErrors errors = new ActionErrors();
    // only validate if the user has clicked "Save"
    if ("Save".equals(request.getParameter("action"))) {
      errors = super.validate(mapping, request);
    }
    return errors;
  }

  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("Patient[Id: " + super.getId());
    str.append(" | Name: " + firstName);
    str.append(" " + middleName);
    str.append(" " + lastName);
    str.append(" | DOB: " + dob);
    str.append(" | Gender: " + gender);
    str.append(" | SSN: " + ssn);
    str.append(" | Phone: " + phone);
    str.append(" | Email: " + email);
    str.append(" | " + ((address == null) ? "Address: null" : address.toString()));
    str.append("]");

    return str.toString();
  }

  /**
   * <p>Converts patient presentation bean to patient value object.</p>
   *
   * @return Patient
   */
  public Patient toPatient() {
    return new Patient(getId(),
        getFirstName(),
        getMiddleName(),
        getLastName(),
        MedRecWebAppUtils.str2Calendar(getDob()),
        getGender(),
        getSsn(),
        getPhone(),
        getEmail(),
        getAddress().toAddress());
  }
}
