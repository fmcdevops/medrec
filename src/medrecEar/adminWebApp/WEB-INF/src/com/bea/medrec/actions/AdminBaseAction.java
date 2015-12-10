package com.bea.medrec.actions;

import com.bea.medrec.controller.AdminSession;
import com.bea.medrec.controller.AdminSessionHome;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;

/**
 * <p>Base servlet encapsulating common servlet functionality.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public abstract class AdminBaseAction extends BaseAction
{
  private static Logger logger =
      Logger.getLogger(AdminBaseAction.class.getName());

  protected InitialContext ctx = null;
  private AdminSession adminSession;

 /**
  * <p>Retrives AdminSession home.
  * If instance does not exist, retrieve a new instance.<p>
  *
  * @return AdminSession
  */
  protected AdminSession getAdminSession() throws Exception {
    if (ctx == null) ctx = new InitialContext();
    if (adminSession == null) {
      logger.debug("Getting new admin session.");
      this.adminSession = getAdminSessionHome();
    }
    return this.adminSession;
  }

      //   P R I V A T E   M E T H O D S
 /**
  * <p>Get AdminSession</p>
  *
  * @return AdminSession
  */
  private AdminSession getAdminSessionHome() throws Exception {
   AdminSessionHome home = (AdminSessionHome)ctx.lookup(
     "java:/comp/env/AdminSessionEJB");
   return (AdminSession)home.create();
  }
}
