package com.bea.medrec.actions;

import com.bea.medrec.beans.PhysicianBean;
import com.bea.medrec.beans.UserBean;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.utils.PhysConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

/**
 * Physician login controller.  Handles all request during the login
 * process. Currently no authentication done for the physician application.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class PhysLoginAction extends PhysBaseAction
{
  private static Logger logger =
    Logger.getLogger(PhysLoginAction.class.getName());

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
    String action = request.getParameter(PhysConstants.ACTION);
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
      // Process login.
      if (authenticate(request))
        return mapping.findForward("login.success");
    }

    // First time thru.
    return mapping.findForward("login.home");
  }

 /**
  * <p>Currently no authentication done for the physician application.</p>
  */
  private boolean authenticate(HttpServletRequest request)
    throws Exception
  {
    boolean success = true;

    // Create new session.
    HttpSession session = request.getSession(true);

    // No authentication for the physician
    // application at this time.
    PhysicianBean physician = new PhysicianBean("101",
                                               "Mary",
                                               "J.",
                                               "Blige",
                                               "mary@md.com",
                                               "555-555-5555",
                                               null);
    session.setAttribute(PhysConstants.PHYSICIAN_BEAN, physician);

    return success;
  }

}
