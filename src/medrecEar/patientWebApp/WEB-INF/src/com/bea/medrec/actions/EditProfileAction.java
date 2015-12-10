package com.bea.medrec.actions;

import com.bea.medrec.beans.PatientBean;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.PatientConstants;
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
 * <p>Controller to update user profile requests.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class EditProfileAction extends PatientBaseLookupDispatchAction
{
  private static Logger logger =
      Logger.getLogger(EditProfileAction.class.getName());

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
    logger.debug("Default method execution.");
    // First time thru.
    return mapping.findForward("edit.profile");
  }

 /**
  * <p>Cancel edit patient profile.</p>
  *
  * @return ActionForward
  */
  public ActionForward cancel(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception
  {
    logger.info("Cancel edit profile.");
    ActionForward forward = null;
    try {
      form.reset(mapping, request);
      forward = (mapping.findForward("view.profile"));
    }
    catch(Exception e) {
      return handleException(e, request, mapping);
    }
    return forward;
  }

 /**
  * <p>Save updated patient profile.</p>
  *
  * @return ActionForward
  */
  public ActionForward save(ActionMapping mapping,
                            ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response)
    throws ClientException, Exception
  {
    logger.debug("Save updated profile.");
    PatientBean origPatient = null;
    PatientBean newPatient = null;
    ActionErrors errors = null;

    // Get original patient from session.
    origPatient = (PatientBean)getSessionAttribute(request,
      PatientConstants.PATIENT_BEAN);
    // Populate new patient from form, set original id values.
    newPatient = (PatientBean)form;
    newPatient.setId(origPatient.getId());
    newPatient.getAddress().setId(origPatient.getAddress().getId());

    // Set new patient on session.  View presentation comes from session.
    setSessionAttribute(request, PatientConstants.PATIENT_BEAN, newPatient);

    try {
      // Update presist patient information with new data.
      getPatientSession().updatePatient(newPatient.toPatient());
    }
    catch(Exception e) {
      throwClientException(e, mapping, "view.profile");
    }

    return mapping.findForward("view.profile");
   }
}
