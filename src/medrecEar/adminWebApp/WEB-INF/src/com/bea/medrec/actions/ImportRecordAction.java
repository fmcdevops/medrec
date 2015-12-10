package com.bea.medrec.actions;

import com.bea.medrec.utils.AdminConstants;
import com.bea.medrec.utils.ClientException;
import com.bea.medrec.utils.MedRecWebAppUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Import XML controller.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ImportRecordAction extends AdminBaseAction
{
  private static Logger logger =
    Logger.getLogger(ImportRecordAction.class.getName());

 /**
  * <p>Process the specified HTTP request, and create the corresponding HTTP
  * response (or forward to another web component that will create it).
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * <br>
  * Handles incoming XML medical record requests.
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
    // Declare local variables.
    String action = request.getParameter(AdminConstants.ACTION);
    String filename = null;
    logger.debug("Action: "+action);

    if (!MedRecWebAppUtils.isEmpty(action)
      && action.equals(AdminConstants.XML_UPLOAD)) {
      filename = request.getParameter(AdminConstants.XML_FILE);
      logger.debug("XML filename: "+filename);
      if (MedRecWebAppUtils.isEmpty(filename)) {
        throw new ClientException("File "+filename+" not found.");
      }

      try {
        // Send registration to be processed.
        getAdminSession().processXMLUpload(filename);
      }
      catch(Exception e) {
        throwClientException(e, mapping, "view.import.files");
      }
    }
    return mapping.findForward("confirm.import");
  }
}
