package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.beans.PhysicianBean;
import com.bea.medrec.beans.RecordBean;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.utils.PhysConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

/**
 * <p>Controller that handles the creation of a visit
 * containing a new record and, if applicable, prescriptions.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class CreateVisitAction extends PhysBaseLookupDispatchAction
{
  private static Logger logger =
    Logger.getLogger(CreateVisitAction.class.getName());

 /**
  * <p>The following is done because this Controller has three functions:
  * <ul>
  * <li>new visit (fresh form)
  * <li>delete existing rx
  * <li>submitting visit
  * </ul>
  * New visit and deleting an existing rx actions are triggered
  * by query params within a link, not through a form submit button,
  * thus need manual method redirection. Submitting
  * a new visit follows the standard dispatch action.</p>
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
    throws ClientException, Exception
  {
    logger.debug("Create visit execute.");
    // Check action param.
    String action = request.getParameter(mapping.getParameter());
    // If action is new visit or delete rx, redirect appropriately.
    if (PhysConstants.NEW_VISIT.equals(action))
      return newVisit( mapping, form, request, response );
    else if (PhysConstants.DELETE_RX.equals(action))
      return deleteRx( mapping, form, request, response );
    else
      return super.execute( mapping, form, request, response );
  }

 /**
  * <p>Mapping of action to method.</p>
  *
  * @return Map
  */
  protected Map getKeyMethodMap()
  {
    Map map = new HashMap();
    map.put("button.Prescribe.Medication", "prescribeRx");
    map.put("button.Save", "save");
    map.put("button.Reset", "reset");
    map.put("button.Cancel", "cancel");
    return map;
  }

 /**
  * <p>Default behavior.</p>
  *
  * @return ActionForward
  */
  public ActionForward defaultMethod(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    throws Exception
  {
    // Set new token for dup form submission check.
    logger.info("Default action method.");
    logger.debug("Setting token.");
    saveToken(request);
    return mapping.findForward("create.visit");
  }

 /**
  * <p>Cancel new visit creation.</p>
  *
  * @return ActionForward
  */
  public ActionForward cancel(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception
  {
    logger.info("Cancel create visit.");
    ActionForward forward = null;
    try {
      // Remove record from session.
      logger.info("Cleaning session of recordBean.");
      super.removeSessionAttribute(request, PhysConstants.RECORD_BEAN);
      forward = mapping.findForward("cancel.record.success");
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>Delete prescribed prescription.</p>
  *
  * @return ActionForward
  */
  public ActionForward deleteRx(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception
  {
    logger.info("Remove specified rx.");

    // Declare local variables
    String id = null;
    RecordBean record = null;

    // Remove prescribe rx from record.
    id = (String)request.getParameter(PhysConstants.RX_ID);
    if (!MedRecWebAppUtils.isEmpty(id)) {
      // Get record from session.
      record = (RecordBean)getSessionAttribute(request,
        PhysConstants.RECORD_BEAN);
      // Get Rxs from record.
      ArrayList rXs = (ArrayList)record.getPrescriptionBeans();
      // Remove specific Rx.
      if (rXs.size() > 1) rXs.remove(Integer.parseInt(id));
      else rXs.clear();
    }
    return mapping.findForward("create.visit");
  }

 /**
  * <p>Start creating new visit.</p>
  *
  * @return ActionForward
  */
  public ActionForward newVisit(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception
  {
    logger.info("Create new visit.");

    // Make sure session is clean.
    super.removeSessionAttribute(request, PhysConstants.RECORD_BEAN);

   // Set new token for dup form submission check.
    logger.debug("Setting token.");
    saveToken(request);

    return mapping.findForward("create.visit");
  }

 /**
  * <p>Prescribe medication during this visit.</p>
  *
  * @return ActionForward
  */
  public ActionForward prescribeRx(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    throws IOException
  {
    ActionForward forward = null;
    try {
      forward = prescribeRxMethod(mapping, request);
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>Prescribe medication during this visit implementation.</p>
  *
  * @return ActionForward
  */
  private ActionForward prescribeRxMethod(ActionMapping mapping,
                                          HttpServletRequest request)
    throws Exception
  {
    // Declare and initialize local variables
    RecordBean recordBean = (RecordBean)getSessionAttribute(request,
      PhysConstants.RECORD_BEAN);

    // Prescribe rx saves visit to session.
    logger.info("Setting recordBean to session then redirect to create Rx.");
    logger.debug(recordBean.toString());

    // Save to session.
    setSessionAttribute(request, PhysConstants.RECORD_BEAN, recordBean);
    return mapping.findForward("create.rx");
  }

 /**
  * <p>Reset visit information.</p>
  *
  * @return ActionForward
  */
  public ActionForward reset(ActionMapping mapping,
                             ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response)
    throws Exception
  {
    logger.info("Reset visit page.");
    // Reset record.
    super.removeSessionAttribute(request, PhysConstants.RECORD_BEAN);
    return mapping.findForward("create.visit");
  }

 /**
  * <p>Saves visit.</p>
  *
  * @return ActionForward
  */
  public ActionForward save(ActionMapping mapping,
                            ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response)
    throws ClientException, Exception
  {
    logger.info("Saving record.");

    // Declare and initialize local variables
    RecordBean recordBean = (RecordBean)getSessionAttribute(request,
      PhysConstants.RECORD_BEAN);
    ActionErrors errors = new ActionErrors();

    if (!isTokenValid(request)) {
      logger.warn("Token not valid.");
      errors.add("duplicate.submit", new ActionError("duplicate.submit"));
    }

    resetToken(request);
    if (!errors.isEmpty()) {
      saveErrors(request, errors);
      saveToken(request);
      return mapping.findForward("validate.record.failure");
    }

    // Save record to MedRec.
    try {
      // Set date, and patient and physician ids.
      PatientBean patient =
        (PatientBean)getSessionAttribute(request, PhysConstants.PATIENT_BEAN);
      PhysicianBean physician =
        (PhysicianBean)getSessionAttribute(request, PhysConstants.PHYSICIAN_BEAN);

      recordBean.setPatientId(patient.getId());
      recordBean.setPhysicianId(physician.getId());
      recordBean.setDate(MedRecWebAppUtils.getCurrentDate());

      // Let's see the record.
      logger.debug(recordBean.toString());

      // Make sure session is clean.
      super.removeSessionAttribute(request, PhysConstants.RECORD_BEAN);

      // Pass transformed record value object.
      getPhysicianSession().addRecord(recordBean.toRecord());

      // Clean session of record.
      super.removeSessionAttribute(request, PhysConstants.RECORD_BEAN);
    }
    catch(Exception e) {
      throwClientException(e, mapping, "validate.record.failure");
    }
    return mapping.findForward("save.record.success");
  }
}
