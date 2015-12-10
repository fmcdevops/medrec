package com.bea.medrec.actions;

import com.bea.medrec.utils.BeanHelper;
import com.bea.medrec.utils.ClientException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Controller displaying pending registration requests.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ViewRequestsAction extends AdminBaseAction
{
  private static Logger logger =
    Logger.getLogger(ViewRequestsAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Displays pending registration requests.
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
    throws ClientException, Exception {

    try {
      // Retrieve new patients to approve/deny.
      Collection col = getAdminSession().findNewUsers();
      Collection patientApprovalBeans =
          BeanHelper.toPatientApprovalBeanCollection(col);
      logger.debug("Num of Patients to Approve: " + patientApprovalBeans.size());
      request.setAttribute("PatientApprovalBeans", patientApprovalBeans);
    }
    catch(Exception e) {
      throwClientException(e, mapping, "home");
    }

    return mapping.findForward("view.requests");
  }

}
