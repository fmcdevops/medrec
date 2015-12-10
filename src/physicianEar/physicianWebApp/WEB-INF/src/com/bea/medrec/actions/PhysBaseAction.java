package com.bea.medrec.actions;

import com.bea.medrec.controller.PhysicianSession;
import com.bea.medrec.controller.PhysicianSessionHome;
import com.bea.medrec.utils.*;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Base servlet encapsulating common servlet functionality.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public abstract class PhysBaseAction extends BaseAction
{
  private static Logger logger =
    Logger.getLogger(PhysBaseAction.class.getName());

  protected InitialContext ctx = null;
  private PhysicianSession physicianSession;

 /**
  * <p>Retrives PhysicianSession home.
  * If instance does not exist, retrieve a new instance.<p>
  *
  * @return PhysicianSession
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
  * <p>Get PhysicianSession EJB</p>
  *
  * @return PhysicianSession
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
    if (getSessionAttribute(request, PhysConstants.PHYSICIAN_BEAN) == null) {
      String errMsg = getMessage(request, "message.session.expired");
      String redirect =
          MedRecWebAppUtils.getServletName(mapping, "login.home.redirect");
      throw new ClientException(errMsg, redirect);
    }
  }
}
