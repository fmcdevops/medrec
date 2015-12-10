package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.beans.PhysicianBean;
import com.bea.medrec.beans.PrescriptionBean;
import com.bea.medrec.beans.RecordBean;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.utils.PhysConstants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Controller to handle the creation of a prescriptions.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class CreateRxAction extends PhysBaseLookupDispatchAction
{
  private static Logger logger =
    Logger.getLogger(CreateRxAction.class.getName());

 /**
  * <p>Mapping of action to method.</p>
  *
  * @return Map
  */
  protected Map getKeyMethodMap()
  {
    Map map = new HashMap();
    map.put("button.Save", "save");
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
    // First timers
    // Set new token for dup form submission check.
    logger.info("Default action method.");
    logger.debug("setting token.");
    saveToken(request);
    return mapping.findForward("create.rx");
  }


 /**
  * <p>Cancel prescribe medication.</p>
  *
  * @return ActionForward
  */
  public ActionForward cancel(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception
  {
    logger.info("Cancel create rx.");
    ActionForward forward = null;
    try {
      forward = mapping.findForward("cancel.rx");
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>Save prescription to be recorded.</p>
  *
  * @return ActionForward
  */
  public ActionForward save(ActionMapping mapping,
                            ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response)
    throws Exception
  {
    logger.info("Save prescription.");
    PrescriptionBean rxBean = (PrescriptionBean)form;
    RecordBean recordBean = null;
    Collection rxCol = null;

    // Set patient and physician ids and current date.
    setRxValues(request, rxBean);

    // Every's okay.  Get record from session,
    // add rx, then reattach to session.
    recordBean = (RecordBean)getSessionAttribute(request,
      PhysConstants.RECORD_BEAN);

    // Add new prescription to record.
    rxCol = recordBean.getPrescriptionBeans();
    rxCol.add(rxBean);
    logger.debug("Num of Rx: "+recordBean.getPrescriptionBeans().size());

    // Set record to session.
    setSessionAttribute(request, PhysConstants.RECORD_BEAN, recordBean);
    return mapping.findForward("save.rx.success");
  }

 /**
  * Sets known values from the session.
  */
  private void setRxValues(HttpServletRequest request,
                           PrescriptionBean rxBean)
  {
    logger.info("Setting patient and physician ids from session.");

    PatientBean patient =
      (PatientBean)getSessionAttribute(request, PhysConstants.PATIENT_BEAN);
    PhysicianBean physician =
      (PhysicianBean)getSessionAttribute(request, PhysConstants.PHYSICIAN_BEAN);

    rxBean.setPatientId(patient.getId());
    rxBean.setPhysicianId(physician.getId());
    rxBean.setDatePrescribed(MedRecWebAppUtils.getCurrentDate());
  }
}
