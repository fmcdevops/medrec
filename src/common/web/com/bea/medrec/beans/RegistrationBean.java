package com.bea.medrec.beans;

import com.bea.medrec.value.Registration;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Form bean for the user registration pages.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>userbean</b> - Instance of UserBean
 * <li><b>patientbean</b> - Instance of PatientBean
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class RegistrationBean extends BaseBean {
  private PatientBean patientBean = new PatientBean();
  private UserBean userBean = new UserBean();

  public RegistrationBean() {
  }

  public PatientBean getPatientBean() {
    return this.patientBean;
  }

  public UserBean getUserBean() {
    return this.userBean;
  }

  public void setPatientBean(PatientBean pPatientBean) {
    this.patientBean = pPatientBean;
  }

  public void setUserBean(UserBean pUserBean) {
    this.userBean = pUserBean;
  }

  /**
   * <p>Validate registration.</p>
   *
   * @param mapping
   * @param request
   *
   * @return ActionErrors
   */
  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    ActionErrors errors = new ActionErrors();
    // only validate if the user has clicked "Register"
    if ("Register".equals(request.getParameter("action"))) {
      errors = super.validate(mapping, request);
    }
    return errors;
  }

/**
 * <p>Converts registration presentation bean
 * to registration value object.</p>
 *
 * @return Registration
 */
public Registration toRegistration()
  {
    return new Registration(getPatientBean().toPatient(),
      getUserBean().toUser());
}
}
