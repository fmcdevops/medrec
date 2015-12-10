package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.value.Mail;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Patient registration approval controller.
 * Handles requests to approve a patient registration.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ApprovePatientRequestAction extends AdminBaseLookupDispatchAction
{
  private static Logger logger =
    Logger.getLogger(ApprovePatientRequestAction.class.getName());

 /**
  * <p>Determines method calling based on select submit button.</p>
  */
  protected Map getKeyMethodMap()
  {
    Map map = new HashMap();
    map.put("button.Approve", "approve");
    map.put("button.Deny", "deny");
    map.put("button.Cancel", "cancel");
    return map;
  }

 /**
  * <p>Default action method.</p>
  *
  * @return ActionForward
  */
  public ActionForward defaultMethod(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    throws Exception
  {
    return mapping.findForward("patient.approval.success");
  }

 /**
  * <p>User selected patient approve button.</p>
  *
  * @return ActionForward
  */
  public ActionForward approve(ActionMapping mapping,
                            ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response)
    throws ClientException, Exception
  {
    // Declare and initialize local variables.
    Mail mail = null;
    PatientBean patient =
      (PatientBean)getSessionAttribute(request, "patientBean");
    try {
      mail = composeMail(patient.getEmail(), "account.approved.subject",
        "account.approved.message", request);
      getAdminSession().activateAccountStatus(patient.getEmail(), mail);
    } catch (Exception e) {
      throwClientException(e, mapping, "patient.approval.failure");
    }
    return mapping.findForward("patient.approval.success");
  }

 /**
  * <p>User selected cancel button.</p>
  *
  * @return ActionForward
  */
  public ActionForward cancel(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception
  {
    logger.info("Cancel approve patient registration.");
    ActionForward forward = null;
    try {
      forward = (mapping.findForward("patient.approval.success"));
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>User selected patient deny button.</p>
  *
  * @return ActionForward
  */
  public ActionForward deny(ActionMapping mapping,
                            ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response)
    throws ClientException, Exception
  {
    Mail mail = null;
    PatientBean patient =
      (PatientBean)getSessionAttribute(request, "patientBean");
    try {
      mail = composeMail(patient.getEmail(), "account.denied.subject",
        "account.denied.message", request);
      getAdminSession().denyAccountStatus(patient.getEmail(), mail);
    } catch(Exception e) {
      throwClientException(e, mapping, "patient.approval.failure");
    }
    return mapping.findForward("patient.approval.success");
  }

 /**
  * <p>Composes localized mail message with given mail attributes.</p>
  */
  private Mail composeMail(String email,
                           String subject,
                           String msg,
                           HttpServletRequest request)
    throws Exception
  {
    Mail mail = null;

    // Compose mail message.
    mail = new Mail();
    mail.setTo(email);
    mail.setSubject(getMessage(request, subject));
    mail.setMessage(getMessage(request, msg));
    return mail;
  }
}
