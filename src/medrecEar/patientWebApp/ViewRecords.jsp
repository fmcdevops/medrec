<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.utils.PatientConstants" %>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" HREF="stylesheet.css">
</HEAD>

<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="Header.jsp" flush="true"/>
<!-- END Header -->

<!-- START Padding for Content -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<TR>
  <TD>

<!-- START Content -->
<SPAN CLASS="pagetitle-patient"><bean:message key="page.title.home"/></SPAN>
<br>
<br>

<!-- START Visit Content -->
<p CLASS="title"><bean:message key="page.title.visits"/></p>
<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2">
  <TR>
    <TD>&nbsp;&nbsp;</TD>
    <TD>
      <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
      <!-- Visit Title -->
      <!-- Note that the style 'coltitle-patient' should vary
          (coltitle-patient, coltitle-patient) for the user type -->
        <TR>
          <TD CLASS=coltitle-patient><bean:message key="Date"/></TD>
          <TD CLASS=coltitle-patient><bean:message key="VisitReason"/></TD>
          <TD CLASS=coltitle-patient><bean:message key="Physician"/></TD>
        </TR>

      <!-- START Dynamic Record Content -->
      <bean:define id="recordBeans" name="<%=PatientConstants.RECORD_BEANS%>" scope="request"/>
      <bean:size id="recSize" name="recordBeans"/>
      <logic:equal name="recSize" value="0" >
        <TR>
          <TD colspan="3"><bean:message key="message.no.records"/></TD>
        </TR>
      </logic:equal>
      <logic:greaterThan name="recSize" value="0" >
        <logic:iterate id="recordBean" name="recordBeans" type="com.bea.medrec.beans.RecordBean">
            <TR>
              <TD>
                <html:link page="/record.do" paramId="id"
                  paramName="recordBean" paramProperty="id">
                  <bean:write name="recordBean" property="date"/>
                </html:link>
              </TD>
              <TD><bean:write name="recordBean" property="symptoms"/></TD>
              <TD><bean:write name="recordBean" property="physicianName"/></TD>
            </TR>
        </logic:iterate>
      </logic:greaterThan>
      <!-- END Dynamic Record Content -->
    </TABLE>
  </TD>
  </TR>
</TABLE>

<!-- END Visit Content -->
<br>
<br>
<br>

<!-- START Prescriptions -->
<p CLASS="title"><bean:message key="Prescriptions"/></p>
<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2">
  <TR><TD>&nbsp;&nbsp;</TD><TD>
  <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
    <TR>
      <TD CLASS=coltitle-patient><bean:message key="DateStarted"/></TD>
        <TD CLASS=coltitle-patient><bean:message key="Rx"/></TD>
        <TD CLASS=coltitle-patient><bean:message key="Dosage"/></TD>
        <TD CLASS=coltitle-patient><bean:message key="Frequency"/></TD>
        <TD CLASS=coltitle-patient><bean:message key="Refills"/></TD>
        <TD CLASS=coltitle-patient><bean:message key="Instructions"/></TD>
      </TR>

      <!-- START Dynamic Prescription Content -->
      <bean:define id="prescriptionBeans" name="<%=PatientConstants.RX_BEANS%>" scope="request"/>
      <bean:size id="rxSize" name="prescriptionBeans"/>
      <logic:equal name="rxSize" value="0" >
        <TR><TD colspan="6"><bean:message key="message.no.prescriptions.prescribed"/></TD></TR>
      </logic:equal>
      <logic:greaterThan name="rxSize" value="0" >
        <logic:iterate id="rxBean" name="prescriptionBeans" type="com.bea.medrec.beans.PrescriptionBean">
          <TR>
            <td><bean:write name="rxBean" property="datePrescribed"/></td>
            <td><bean:write name="rxBean" property="drug"/></td>
            <td><bean:write name="rxBean" property="dosage"/></td>
            <td><bean:write name="rxBean" property="frequency"/></td>
            <td><bean:write name="rxBean" property="refillsRemaining"/></td>
            <td><bean:write name="rxBean" property="instructions"/>&nbsp;</td>
          </TR>
        </logic:iterate>
      </logic:greaterThan>
      <!-- END Dynamic Record Content -->
    </TABLE>
  </TD>
  </TR>
</TABLE>


<!-- END Content -->
</TD>
</TR>
</TABLE>
<!-- END Padding for Content -->

</BODY>
</html:html>
