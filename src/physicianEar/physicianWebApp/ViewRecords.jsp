<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.bea.medrec.utils.PhysConstants" %>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.physician.app"/></TITLE>
  <link rel="stylesheet" type="text/css" HREF="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="PatientHeader.jsp" flush="true"/>
<!-- END Header -->

<!-- START Padding for Content -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<TR>
  <TD>

  <!-- START Breadcrumbs -->
  <a href="search.do"><span class="pagetitle-md"><bean:message key="page.title.physcian.home"/></span></a> &gt;
  <span class="pagetitle-md"><bean:message key="page.title.patient.record"/></span>
  <!-- END Breadcrumbs -->

  <!-- START Content -->
  <br><br>
  <a href="visit.do?<%=PhysConstants.ACTION%>=<%=PhysConstants.NEW_VISIT%>"><bean:message key="link.new.visit"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="search.do"><bean:message key="link.close.chart"/></a>
  <br><br>
  <!-- START Visit Content -->
  <p CLASS="title"><bean:message key="Visits"/></p>
  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2">
  <TR>
    <TD>&nbsp;&nbsp;</TD>
    <TD>
      <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
        <!-- Visit Title -->
        <!-- Note that the style 'coltitle-patient' should vary
            (coltitle-patient, coltitle-patient) for the user type -->
        <TR>
          <TD class="patientbanner2"><bean:message key="Date"/></TD>
          <TD class="patientbanner2"><bean:message key="VisitReason"/></TD>
          <TD class="patientbanner2"><bean:message key="Physician"/></TD>
        </TR>
        <!-- START Dynamic Record Content -->
        <bean:define id="recordBeans" name="<%=PhysConstants.RECORD_COLLECTION%>" scope="request"/>
        <bean:size id="recSize" name="recordBeans"/>
        <logic:equal name="recSize" value="0" >
          <TR><TD colspan="3"><bean:message key="message.no.records.found"/></TD></TR>
        </logic:equal>
        <logic:greaterThan name="recSize" value="0" >
          <logic:iterate id="recordBean" name="recordBeans" type="com.bea.medrec.beans.RecordBean">
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
  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2"><TR><TD>&nbsp;&nbsp;</TD><TD>
  <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
  <TR>
    <TD class="patientbanner2"><bean:message key="DateStarted"/></TD>
    <TD class="patientbanner2"><bean:message key="Rx"/></TD>
    <TD class="patientbanner2"><bean:message key="Dosage"/></TD>
    <TD class="patientbanner2"><bean:message key="Frequency"/></TD>
    <TD class="patientbanner2"><bean:message key="Refills"/></TD>
    <TD class="patientbanner2"><bean:message key="Instructions"/></TD>
  </TR>
   <!-- START Dynamic Prescription Content -->
  <bean:define id="prescriptionBeans" name="<%=PhysConstants.RX_COLLECTION%>" scope="request"/>
  <bean:size id="rxSize" name="prescriptionBeans"/>
  <logic:equal name="rxSize" value="0" >
    <TR><TD colspan="6"><bean:message key="message.no.prescriptions.prescribed"/></TD></TR>
  </logic:equal>
  <logic:greaterThan name="rxSize" value="0" >
    <logic:iterate id="prescriptionBean" name="prescriptionBeans" type="com.bea.medrec.beans.PrescriptionBean">
    <TR>
      <TD><bean:write name="prescriptionBean" property="datePrescribed"/></TD>
      <TD><bean:write name="prescriptionBean" property="drug"/></TD>
      <TD><bean:write name="prescriptionBean" property="dosage"/></TD>
      <TD><bean:write name="prescriptionBean" property="frequency"/></TD>
      <TD><bean:write name="prescriptionBean" property="refillsRemaining"/></TD>
      <TD><bean:write name="prescriptionBean" property="instructions"/>&nbsp</TD>
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
