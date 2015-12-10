<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="Header.jsp" flush="true"/>
<!-- END Header -->

<!-- Start Padding for Content -->
<table WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
  <tr>
    <td>

<!-- Content -->
<!-- START Breadcrumbs  -->
<A HREF="home.do"><SPAN CLASS="pagetitle-admin"><bean:message key="link.home"/></SPAN></A> &gt;
<SPAN CLASS="pagetitle-admin"><bean:message key="page.title.view.pending.requests"/></SPAN>
<!-- END Breadcrumbs -->
<br><br>

<table BORDER="0" CELLSPACING="0" CELLPADDING="2">
  <tr><td>&nbsp;&nbsp;</td><td>
  <table BORDER="1" CELLSPACING="0" CELLPADDING="2">
    <TR>
      <TD class="coltitle-admin"><bean:message key="From"/></TD>
      <TD class="coltitle-admin"><bean:message key="UserType"/></TD>
      <TD class="coltitle-admin"><bean:message key="RequestType"/></TD>
      <TD class="coltitle-admin"><bean:message key="Action"/></TD>
    </TR>
    <bean:define id="patientApprovalBeans" name="PatientApprovalBeans" scope="request"/>
    <bean:size id="size" name="patientApprovalBeans"/>
    <logic:equal name="size" value="0" >
      <TR><TD colspan=4><bean:message key="message.no.records"/></TD></TR>
    </logic:equal>
    <logic:greaterThan name="size" value="0" >
      <logic:iterate id="patientApprovalBean" name="PatientApprovalBeans" type="com.bea.medrec.beans.PatientApprovalBean">
        <TR>
          <TD>
            <bean:write name="patientApprovalBean" property="name"/>
          </TD>
          <TD>
            <bean:message key="Patient"/>
          </TD>
          <TD>
            <bean:message key="NewUserRegistration"/>
          </TD>
          <TD>
            <html:link page="/viewpatientrequest.do" paramId="patientId"
              paramName="patientApprovalBean" paramProperty="id">
              <bean:message key="link.view.request"/>
            </html:link>
          </TD>
        </TR>
      </logic:iterate>
    </logic:greaterThan>

  </table>
</table>
<!-- Content END -->

<!-- End Padding for Content -->
  </tr>
</table>
</BODY>
</html:html>
