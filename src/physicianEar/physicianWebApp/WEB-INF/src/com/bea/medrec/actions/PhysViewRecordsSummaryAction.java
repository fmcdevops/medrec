package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.utils.BeanHelper;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.PhysConstants;
import com.bea.medrec.value.RecordsSummary;
import java.util.Collection;
import java.util.Iterator;
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
public class PhysViewRecordsSummaryAction extends PhysBaseAction
{
   private static Logger logger =
       Logger.getLogger(PhysViewRecordsSummaryAction.class.getName());

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
    // Minimal check for valid session.
    super.checkSession(request, mapping);

    String nextPage = "view.records.summary";
    String requestPatId = (String)request.getParameter("id");
    PatientBean sessionPatient = (PatientBean)super.getSessionAttribute(request,
      PhysConstants.PATIENT_BEAN);
    // Check for existing patient on session.
    if (sessionPatient != null) {
      // Check to see if request patient is the same as existing patient.
      if (requestPatId != null && !sessionPatient.getId().equals(requestPatId)){
        // New patient selected from results page.
        nextPage = "search.results";
      }
      else {
        try {
          // Get records summary for existing patient.
          getMedicalRecords(request, sessionPatient.getId());
        }
        catch(Exception e) {
          throwClientException(e, mapping, "search.results");
        }
      }
    }
    else if (requestPatId != null) {
      // First time thru with selected patient from search results.
      Collection patients = (Collection)super.getSessionAttribute(request,
        PhysConstants.PATIENT_COLLECTION);
      if (patients == null) {
        logger.info("Recreating results from previous search.");
        nextPage = "search.results";
      }
      else   {
        logger.info("Setting selected patient on session.");
        PatientBean patient = findPatientInCollection(requestPatId, patients);
        super.setSessionAttribute(request, PhysConstants.PATIENT_BEAN, patient);
        // This removed all search results from
        // session after a resultant has been choosen.
        super.removeSessionAttribute(request, PhysConstants.PATIENT_COLLECTION);
        getMedicalRecords(request, requestPatId);
      }
    }

    logger.debug("Redirecting to "+nextPage);
    return mapping.findForward(nextPage);
  }

 /**
  * Retrieves medical record summary fro a given patient id.
  */
  private void getMedicalRecords(HttpServletRequest request, String patId)
    throws Exception
  {
    RecordsSummary recordsSummary =
      getPhysicianSession().getRecordsSummary(Integer.valueOf(patId));
    request.setAttribute(PhysConstants.RECORD_COLLECTION,
      BeanHelper.toRecordBeanCollection(recordsSummary.getRecords()));
    request.setAttribute(PhysConstants.RX_COLLECTION,
      BeanHelper.toRxBeanCollection(recordsSummary.getPrescriptions()));
  }

 /**
  * User selects a patient from the search results collection.  This
  * method retrieves that patient from the collection.
  */
  private PatientBean findPatientInCollection(String id, Collection patients)
  {
    logger.info("Finding patient in session patient collection.");
    logger.debug("PatId to find: "+id);

    PatientBean patient = null;
    Iterator itr = patients.iterator();
    while(itr.hasNext()) {
      patient = (PatientBean)itr.next();
      logger.debug("Found PatId: "+patient.getId());
      if (patient.getId().equals(id))
        break;
    }
    return patient;
  }

}
