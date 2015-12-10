package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.beans.RegistrationBean;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.MedRecWebAppUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Handles all request during the registration process.</p<
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class RegisterAction extends PatientBaseLookupDispatchAction {
  private static Logger logger =
      Logger.getLogger(RegisterAction.class.getName());

  /**
   * <p>Mapping of action to method.</p>
   *
   * @return Map
   */
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.Cancel", "cancel");
    map.put("button.Register", "register");
    return map;
  }

  /**
   * <p>Default behavior.</p>
   *
   * @return ActionForward
   */
  public ActionForward defaultMethod(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
      throws Exception {
    // First time thru.
    logger.debug("Default method executions.");
    setupLocale(request);
    return mapping.findForward("register.home");
  }

  /**
   * <p>Cancel edit patient profile.</p>
   *
   * @return ActionForward
   */
  public ActionForward cancel(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
      throws Exception {
    logger.info("Cancel registration.");
    ActionForward forward = null;
    try {
      form.reset(mapping, request);
      // Return to MedRec start page.
      String nextPage = MedRecWebAppUtils.getMedRecHomeURL(mapping, request);
      forward = new ActionForward(nextPage, true);
    } catch (Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

  /**
   * <p>Register new patient user.</p>
   *
   * @return ActionForward
   */
  public ActionForward register(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
      throws ClientException, Exception {
    logger.info("New user registration.");
    RegistrationBean registrationBean = (RegistrationBean) form;
    PatientBean patientBean = registrationBean.getPatientBean();

    // The email address is the username.
    patientBean.setEmail(registrationBean.getUserBean().getUsername());


    try {
      // Send registration to be processed.
      getAdminSession().processRegistration(registrationBean.toRegistration());
    } catch (Exception e) {
      throwClientException(e, mapping, "validate.registration.failure");
    }
    return mapping.findForward("register.success");
  }
}
