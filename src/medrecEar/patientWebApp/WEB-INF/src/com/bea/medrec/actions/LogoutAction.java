package com.bea.medrec.actions;

import com.bea.medrec.utils.MedRecWebAppUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import weblogic.servlet.security.ServletAuthentication;

/**
 * <p>Login controller.  Handles all request during the logout
 * process.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class LogoutAction extends PatientBaseAction
{
  private static Logger logger = Logger.getLogger(LogoutAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Invalidates session and redirects to MedRec homepage.
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
    logger.info("Logging out user.");

    String nextPage = null;

    logger.info("Invalidating session.");
    ServletAuthentication.invalidateAll(request);

    // Return to MedRec start page.
    nextPage = MedRecWebAppUtils.getMedRecHomeURL(mapping, request);
    return new ActionForward(nextPage, true);
  }

}
