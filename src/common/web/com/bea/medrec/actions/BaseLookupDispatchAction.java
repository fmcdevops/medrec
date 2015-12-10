package com.bea.medrec.actions;

import com.bea.medrec.beans.ErrorBean;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.MedRecMessageProperties;
import com.bea.medrec.utils.MedRecWebAppUtils;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

/**
 * <p>Base Action encapsulating common lookup dispatch functionality.
 *    Use when form page contains mulitple submit buttons.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public abstract class BaseLookupDispatchAction extends LookupDispatchAction
{
  private static Logger logger =
      Logger.getLogger(BaseLookupDispatchAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Encapsulates common action functionality including error handling.
  * </p>
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
    throws Exception
  {
    ActionForward forward;
    try {
      logger.debug("Mapping parm = "+mapping.getParameter());
      logger.debug("Button selected = "+request.getParameter(mapping.getParameter()));
      if (request.getParameter(mapping.getParameter()) == null) {
        logger.debug("Default method call.");
        forward = defaultMethod(mapping, form, request, response);
      }
      else {
        logger.debug("Executing LookupDispatchAction.");
        forward = super.execute(mapping, form, request, response);
      }
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>All sub-classes must implement this method where if
  * not action is supplied the defaultMethod implementation
  * will be executed.</p>
  *
  * @param mapping  The ActionMapping used to select this instance
  * @param form  The optional ActionForm bean for this request (if any)
  * @param request  The HTTP request we are processing
  * @param response  The HTTP response we are creating
  */
  public abstract ActionForward defaultMethod(ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response)
    throws Exception;


  /**
   * <p>Removes an attribute from HttpSession.</p>
   *
   * @param req  The HTTP request we are processing
   * @param name  The name of the attribute to be removed.
   */
   protected void removeSessionAttribute(HttpServletRequest req, String name)
   {
     logger.debug("Removing "+name+" from session.");
     HttpSession session = req.getSession(false);
     if (session != null) session.removeAttribute(name);

   }

  /**
   * <p>Sets an object to the HttpSession</p>
   *
   * @param req  The HTTP request we are processing
   * @param name  The name key of object.
   * @param obj  The object to be set on HttpSession.
   */
   protected void setSessionAttribute(HttpServletRequest req,
                                      String name,
                                      Object obj)
   {
     logger.debug("Setting "+name+" of type "
       +obj.getClass().getName()+" on session.");
     HttpSession session = req.getSession(false);
     if (session != null) session.setAttribute(name, obj);
   }

  /**
   * <p>Retrieves an object from HttpSession.</p>
   *
   * @param req  The HTTP request we are processing
   * @param name  The name of the attribute to be retrieved.
   */
   protected Object getSessionAttribute(HttpServletRequest req, String name)
   {
     logger.debug("Getting "+name+" from session.");
     Object obj = null;
     HttpSession session = req.getSession(false);
     if (session != null) obj = session.getAttribute(name);
     return obj;
   }

  /**
   * <p>Prints values set on the current HttpSession.</p>
   *
   * @param req  The HTTP request we are processing
   */
   protected void printSession(HttpServletRequest req)
   {
     HttpSession session = req.getSession(false);
     if (session != null) {
       Enumeration enum = session.getAttributeNames();
       logger.debug("Session contents:");
       while(enum.hasMoreElements()) {
         logger.debug("     "+enum.nextElement());
       }
     }
   }

 /**
  * <p>Sets user's locale.</p>
  *
  * @param request  The HTTP request we are processing
  */
  protected void setupLocale(HttpServletRequest request)
  {
    logger.debug("Setup locale.");
    Locale locale = MedRecWebAppUtils.getLocaleFromCookie(request);
    if (locale == null) locale = getLocale(request);
    if (!MedRecWebAppUtils.isValidLocale(locale))
      locale = new Locale("en", "US");
   logger.debug("Locale: "+locale);
   setLocale(request, locale);
  }

 /**
  * <p>Get localize message.</p>
  *
  * @param key  The HTTP request we are processing
  */
  protected String getMessage(HttpServletRequest request, String key)
  {
    Locale locale = getLocale(request);
    return getResources(request).getMessage(locale, key);
  }

 /**
  * <p>Get instance of message properties.</p>
  *
  * @param request  The HTTP request we are processing
  */
  protected MedRecMessageProperties getMessageProps(HttpServletRequest request)
  {
    Locale locale = getLocale(request);
    return MedRecMessageProperties.getInstance(locale, getResources(request));
  }

  /**
   * <p>Formulates and throws client exception.<p>
   *
   * @param th
   * @param mapping
   * @param redirect
   * @throws ClientException
   */
  protected void throwClientException(Throwable th,
                                      ActionMapping mapping,
                                      String redirect)
    throws ClientException
  {
    logger.error(th.getMessage());
    String errorLink = MedRecWebAppUtils.getServletName(mapping, redirect);
    logger.debug("errorLink: "+errorLink);
    throw new ClientException(th,errorLink);
  }

 /**
  * <p>Uniform way of handling exceptions.<p>
  *
  * @param th
  * @param mapping
  * @param request
  *
  * @return ActionForward
  */
  protected ActionForward handleException(Throwable th,
                                          HttpServletRequest request,
                                          ActionMapping mapping)
  {
    if (th instanceof ClientException) {
      logger.error(th.getMessage());
      String redirectLink = ((ClientException)th).getLink();
      logger.info("Redirect link: "+redirectLink);
      ErrorBean errorBean = new ErrorBean(MedRecWebAppUtils.getRootErrMsg(th),
        redirectLink);
      request.setAttribute("errorBean", errorBean);
      return mapping.findForward("error");
    }
    else {
      logger.error(th.getMessage());
      String link = MedRecWebAppUtils.getServletName(mapping, "home");
      logger.info("Redirect link: "+link);
      ErrorBean errorBean = new ErrorBean(MedRecWebAppUtils.getRootErrMsg(th),
        link);
      request.setAttribute("errorBean", errorBean);
      return mapping.findForward("error");
    }
  }
}

