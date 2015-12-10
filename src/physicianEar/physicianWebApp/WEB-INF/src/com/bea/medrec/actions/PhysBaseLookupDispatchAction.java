package com.bea.medrec.actions;

import com.bea.medrec.controller.PhysicianSession;
import com.bea.medrec.controller.PhysicianSessionHome;
import com.bea.medrec.utils.*;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Base lookup dispatch action encapsulating
 * common physician webapp functionality.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public abstract class PhysBaseLookupDispatchAction
  extends BaseLookupDispatchAction
{
  private static Logger logger =
    Logger.getLogger(PhysBaseLookupDispatchAction.class.getName());

  protected InitialContext ctx = null;
  private PhysicianSession physicianSession;

 /**
  * <p>Calls base lookup dispatch action.
  * First checks for valid session</p>
  *
  * @param mapping  The ActionMapping used to select this instance
  * @param form  The optional ActionForm bean for this request (if any)
  * @param request  The HTTP request we are processing
  * @param response  The HTTP response we are creating
  */
  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
    throws Exception, ClientException
  {
    logger.debug("PhysBaseLookupDispatchAction execute.");
    ActionForward forward;
    try {
      // Minimal check for valid session.
      checkSession(request, mapping);
      forward = super.execute(mapping, form, request, response);
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>Get physician session.</p>
  */
  protected PhysicianSession getPhysicianSession() throws Exception {
    if (ctx == null) ctx = new InitialContext();
    if (physicianSession == null) {
      logger.debug("Getting new physician session.");
      this.physicianSession = getPhysicianSessionHome();
    }
    return this.physicianSession;
  }

      //   P R I V A T E   M E T H O D S
 /**
  * Get PhysicianSession EJB
  */
  private PhysicianSession getPhysicianSessionHome() throws Exception {
    PhysicianSessionHome home = (PhysicianSessionHome)ctx.lookup(
     "java:/comp/env/PhysicianSessionEJB");
    return (PhysicianSession)home.create();
  }

 /**
  * <p>Check for valid session.</p>
  */
  protected void checkSession(HttpServletRequest request, ActionMapping mapping)
    throws ClientException
  {
    logger.debug("Validating session.");
    if (getSessionAttribute(request, PhysConstants.PHYSICIAN_BEAN) == null) {
      String errMsg = getMessage(request, "message.session.expired");
      String redirect =
          MedRecWebAppUtils.getServletName(mapping, "login.home.redirect");
      throw new ClientException(errMsg, redirect);
    }
  }
}
