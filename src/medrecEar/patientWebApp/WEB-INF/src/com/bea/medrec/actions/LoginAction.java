package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.beans.UserBean;
import com.bea.medrec.utils.*;
import com.bea.medrec.value.Patient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import weblogic.servlet.security.ServletAuthentication;

/**
 * <p>Login controller.  Handles all request during the login
 * process.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class LoginAction extends PatientBaseAction
{
  private static Logger logger =
    Logger.getLogger(LoginAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Handles incoming login requests.
  * </p>
  *
  * @param mapping  The ActionMapping used to select this instance
  * @param form  The optional ActionForm bean for this request (if any)
  * @param request  The HTTP request we are processing
  * @param response  The HTTP response we are creating
  */
  public ActionForward executeAction(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    throws Exception
  {
    // Set user's locale.
    setupLocale(request);

    // Declare and initial local variables.
    UserBean user = (UserBean)form;
    String action = request.getParameter(PatientConstants.ACTION);
    String loginSubmit = null;

    // Cancel login.  Redirect to start page.
    if (isCancelled(request)) {
      logger.info("Cancel login.");
      form.reset(mapping, request);
      // Return to MedRec start page.
      String nextPage = MedRecWebAppUtils.getMedRecHomeURL(mapping, request);
      return new ActionForward(nextPage, true);
    }

    // Login processing.
    loginSubmit = getMessage(request, "button.Login");
    logger.debug("Action: "+action);
    logger.debug("Button Message: "+loginSubmit);
    if (!MedRecWebAppUtils.isEmpty(action) && action.equals(loginSubmit)) {
      try {
        // Process login.
        return authenticate(user, mapping, request);
      }
      catch(Exception e) {
        throwClientException(e, mapping, "login.home.redirect");
      }
    }

    // First time thru.
    return mapping.findForward("login.home");
  }

 /**
  * <p>This method authenticates a given user containg a username and password.
  * Since MedRec contains an Admin and Patient appplication and
  * each application has its own specific authentication provider,
  * authentication is a two step process.  The first step the server validates
  * the username and password by using a authentication provider.  The
  * second step checks that meta-data is found within MedRec's database.</p>
  */
  private ActionForward authenticate(UserBean user,
                                    ActionMapping mapping,
                                    HttpServletRequest request)
    throws Exception {

    // Delcare local variables.
    ActionForward forward = null;

    // Returns an int value for AUTHENTICATED or FAILED_AUTHENTICATION
    // after using the username and password to authenticate the user
    // and setting that user information into the session.
    int auth = ServletAuthentication.weak(user.getUsername(),
                                          user.getPassword(),
                                          request);

    // check auth return value
    if (auth == ServletAuthentication.AUTHENTICATED
        && request.isUserInRole(PatientConstants.PATIENT_ROLE)) {
      logger.info("Login found.");
      logger.info("Looking up user data.");

      // Retrieve patient properties.
      Patient patient =
        getPatientSession().findPatientByEmail(user.getUsername());

      // Patient user found, but no meta-data found.
      // Disallow login.
      if (patient == null) {
        ServletAuthentication.invalidateAll(request);
        throw new ClientException(ErrorConstants.PATIENT_NOT_FOUND);
      }

      logger.info("Authentication success!");

      // Create new session.
      HttpSession session = request.getSession(true);

      // Set user on session to be used throughout the app.
      session.setAttribute(PatientConstants.PATIENT_BEAN,
        new PatientBean(patient));

      // Determine redirection.
      forward = forward = getRedirectPage(request, mapping);
    }
    else {
      logger.debug("Authentication failed!");

      // Reset login values.
      user.reset();

      // Create action error - invalid username and/or password.
      ActionErrors errors = new ActionErrors();
      errors.add("invalidLogin", new ActionError("invalid.username.password"));
      saveErrors(request, errors);

      // Return back to login page.
      forward = mapping.findForward("login.failure");
    }

    // Log where we are going next.
    logger.info("Redirecting to: "+forward.getPath());

    return forward;
  }
}
