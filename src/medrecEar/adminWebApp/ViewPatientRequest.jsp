<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils"%>
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
      <A HREF="viewrequests.do"><SPAN CLASS="pagetitle-admin"><bean:message key="link.view.pending.requests"/></SPAN></A> &gt;
      <SPAN CLASS="pagetitle-admin"><bean:message key="page.title.patient.profile"/></SPAN>
      <!-- END Breadcrumbs -->
<br><br>


<TABLE BORDER="0" CELLSPACING="2" CELLPADDING="3">
  <TR class="row1">
    <TD class="label"><bean:message key="FirstName"/></TD>
    <TD><bean:write name="patientBean" property="firstName"/></TD>
  </TR>
  <TR class="row2">
    <TD class="label"><bean:message key="LastName"/></TD>
    <TD><bean:write name="patientBean" property="lastName"/></TD>
  </TR>
  <TR class="row1">
    <TD class="label"><bean:message key="MiddleName"/></TD>
    <TD><bean:write name="patientBean" property="middleName"/></TD>
  </TR>
  <TR class="row2">
    <TD class="label"><bean:message key="Gender"/></TD>
    <TD><bean:write name="patientBean" property="gender"/></TD>
  </TR>
  <TR class="row1">
    <TD class="label"><bean:message key="DateOfBirth"/></TD>
    <TD><bean:write name="patientBean" property="dob"/></TD>
  </TR>
  <TR class="row2">
    <TD class="label"><bean:message key="SocialSecurityNumber"/></TD>
    <TD><bean:write name="patientBean" property="ssn"/></TD>
  </TR>
  <TR class="row1">
    <TD class="label"><bean:message key="Phone"/></TD>
    <TD><bean:write name="patientBean" property="phone"/></TD>
  </TR>
  <TR class="row2">
    <TD class="label"><bean:message key="Email"/></TD>
    <TD><bean:write name="patientBean" property="email"/></TD>
  </TR>
  <TR class="row1">
    <TD class="label"><bean:message key="Address"/></TD>
    <TD><bean:write name="patientBean" property="address.streetName1"/>
    <br><bean:write name="patientBean" property="address.streetName2"/>
    <br><bean:write name="patientBean" property="address.city"/>
    ,&nbsp;<bean:write name="patientBean" property="address.state"/>
    &nbsp;&nbsp;<bean:write name="patientBean" property="address.zipCode"/>
    <br><bean:write name="patientBean" property="address.country"/>
    </TD>
  </TR>
  <TR>
    <TD></TD>
    <TD colspan="2" align="center">
      <br>
      <html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/approve.do">
        <html:submit property="action">
          <bean:message key="button.Approve"/>
        </html:submit>
        <html:submit property="action">
          <bean:message key="button.Deny"/>
        </html:submit>
        <html:cancel property="action">
          <bean:message key="button.Cancel"/>
        </html:cancel>
      </html:form>
    </TD>
  </TR>
</TABLE>


<!-- Content END -->

<!-- End Padding for Content -->
  </tr>
</table>
</BODY>
</html:html>
