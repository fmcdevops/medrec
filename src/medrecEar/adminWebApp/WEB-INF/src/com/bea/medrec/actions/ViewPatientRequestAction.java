package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.utils.AdminConstants;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.value.Patient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Controller to view pending new patient registrations.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ViewPatientRequestAction extends AdminBaseAction
{
  private static Logger logger =
    Logger.getLogger(ViewPatientRequestAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * View pending patient registration information.
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

    String patientId  = request.getParameter("patientId");
    Patient patient = null;
    PatientBean patientBean = null;

   try {
      patient = getAdminSession().findPatientById(new Integer(patientId));
      patientBean = new PatientBean(patient);
      setSessionAttribute(request, AdminConstants.PATIENT_BEAN, patientBean);
    } catch (Exception e) {
      throwClientException(e, mapping, "home");
    }

    return mapping.findForward("view.patient.request");

  }
}
