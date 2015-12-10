package com.bea.medrec.actions;

import com.bea.medrec.beans.RecordBean;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.utils.PhysConstants;
import com.bea.medrec.value.Record;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Controller to retrieve a particular medical record.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class PhysViewRecordAction extends PhysBaseAction
{
  private static Logger logger =
    Logger.getLogger(PhysViewRecordAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Retrieves a particular record.
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

    Record record = null;
    RecordBean recordBean = null;
    String recordId = request.getParameter("id");
    logger.debug("RecId: "+recordId);

    try {
      if (MedRecWebAppUtils.isEmpty(recordId)) {
        logger.error("Record id null.");
        throw new ClientException("Record not found.");
      }

      // Pass record id to be retrieved.
      record = getPhysicianSession().getRecord(Integer.valueOf(recordId));

      if (record == null) {
        logger.error("Record not found.");
        throw new ClientException("Record not found.");
      }
    }
    catch(Exception e) {
      throwClientException(e, mapping, "view.records.summary");
    }

    // Convert record value object to presentation object.
    recordBean = new RecordBean(record);

    // Let's see the record.
    logger.debug(recordBean.toString());

    // Set on request to be display by view.
    request.setAttribute(PhysConstants.RECORD_BEAN, recordBean);

    return mapping.findForward("view.record");
  }
}
