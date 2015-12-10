package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.utils.BeanHelper;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.utils.PatientConstants;
import com.bea.medrec.value.RecordsSummary;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Retrieves all records for a patient.  Records are returned
 * in abbreviated form in order to display a summary of
 * each record.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ViewRecordsSummaryAction extends PatientBaseAction
{
  private static Logger logger =
    Logger.getLogger(ViewRecordsSummaryAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Retrieves a patients entire medical record history.
  * Information includes summarized views of records and prescriptions.
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
    throws ClientException, Exception
  {
    PatientBean patientBean = (PatientBean)getSessionAttribute(request,
      PatientConstants.PATIENT_BEAN);
    Integer patientId = null;
    RecordsSummary recordsSummary = null;

    if (MedRecWebAppUtils.isNotEmpty(patientBean.getId())) {
      logger.debug("PatId: "+patientBean.getId());

      try {
        patientId = Integer.valueOf(patientBean.getId());
        recordsSummary = getRecordSession().getRecordsSummary(patientId);
      }
      catch(Exception e) {
        throwClientException(e, mapping, "search.results");
      }

      request.setAttribute(PatientConstants.RECORD_BEANS,
        BeanHelper.toRecordBeanCollection(recordsSummary.getRecords()));
      request.setAttribute(PatientConstants.RX_BEANS,
        BeanHelper.toRxBeanCollection(recordsSummary.getPrescriptions()));
    }

    return mapping.findForward("view.records.summary");
  }
}
