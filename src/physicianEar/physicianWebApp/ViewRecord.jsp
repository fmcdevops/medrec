<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.utils.PhysConstants" %>

<jsp:useBean id="recordBean" class="com.bea.medrec.beans.RecordBean" scope="request"/>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.physician.app"/></TITLE>
  <Link rel="stylesheet" type="text/css" href="stylesheet.css">
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
    <a href="medicalrecord.do"><span class="pagetitle-md"><bean:message key="page.title.patient.record"/></span></a> &gt;
    <span class="pagetitle-md"><bean:message key="page.title.visit.summary"/></span>
    <!-- END Breadcrumbs -->
    <br>
    <br>
    <!-- START Content -->
    <!-- START Record Table -->
    <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="2">
      <!-- START Date -->
      <TR class="row1">
  <TD class="label"><bean:message key="Date"/></TD>
  <TD><bean:write name="<%=PhysConstants.RECORD_BEAN%>" property="date"/></TD>
      </TR>
      <!-- END Date -->
      <!-- START Reason for Visit -->
      <TR class="row2">
  <TD class="label"><bean:message key="VisitReason"/></TD>
  <TD><bean:write name="<%=PhysConstants.RECORD_BEAN%>" property="symptoms"/></TD>
      </TR>
      <!-- END Reason for Visit -->
      <TR>
        <TD></TD>
        <TD>&nbsp;</TD>
      </TR>
      <!-- START Vital Signs -->
      <TR class="row1">
        <TD class="label"><bean:message key="VitalSigns"/></TD>
        <TD>
          <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="3">
            <bean:define id="vitalSignsBean" name="<%=PhysConstants.RECORD_BEAN%>" property="vitalSignsBean" scope="request"/>
            <TR>
              <TD ALIGN="right"><bean:message key="Temperature"/>:</TD>
              <TD><bean:write name="vitalSignsBean" property="temperature"/>&nbsp;F</TD>
              <TD ALIGN="right"><bean:message key="Weight"/>:</TD>
              <TD><bean:write name="vitalSignsBean" property="weight"/>&nbsp;lbs</TD>
            </TR>
            <TR>
              <TD ALIGN="right"><bean:message key="Pulse"/>:</TD>
              <TD><bean:write name="vitalSignsBean" property="pulse"/>&nbsp;bmp</TD>
              <TD ALIGN="right"><bean:message key="Height"/>:</TD>
              <TD><bean:write name="vitalSignsBean" property="height"/>&nbsp;inches</TD>
            </TR>
            <TR>
              <TD ALIGN="right">&nbsp;&nbsp;<bean:message key="BloodPressure"/>:</TD>
              <TD><bean:write name="vitalSignsBean" property="bloodPressure"/>&nbsp;</TD>
              <TD ALIGN="right">&nbsp;</TD>
              <TD>&nbsp;</TD>
            </TR>
          </TABLE>
        <br>
        </TD>
      </TR>
      <!-- END Vital Signs -->
      <!-- START Exam Notes -->
      <TR class="row2">
        <TD class="label" valign="top"><bean:message key="ExamNotes"/></TD>
        <TD>
          <bean:write name="<%=PhysConstants.RECORD_BEAN%>" property="notes"/>
          <br>
          <br>
        </TD>
      </TR>
      <!-- END Exam Notes -->
      <!-- START Diagnosis -->
      <TR class="row1">
        <TD class="label" vALIGN="top"><bean:message key="Diagnosis"/></TD>
        <TD>
          <bean:write name="<%=PhysConstants.RECORD_BEAN%>" property="diagnosis"/>
        </TD>
      </TR>
      <!-- Diagnosis End -->
      <TR><TD>&nbsp;</TD></TR>
      <!-- START Medications Prescribed -->
      <TR class="row2">
        <TD class="label"><bean:message key="MedicationsPrescribed"/></TD>
        <TD>
          <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2">
          <TR>
            <TD>&nbsp;&nbsp;</TD>
            <TD>
              <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
                <TR>
                  <TD class="patientbanner2"><bean:message key="DateStarted"/></TD>
                  <TD class="patientbanner2"><bean:message key="Rx"/></TD>
                  <TD class="patientbanner2"><bean:message key="Dosage"/></TD>
                  <TD class="patientbanner2"><bean:message key="Frequency"/></TD>
                  <TD class="patientbanner2"><bean:message key="Refills"/></TD>
                  <TD class="patientbanner2"><bean:message key="Instructions"/></TD>
                </TR>
                <bean:define id="prescriptionBeans" name="<%=PhysConstants.RECORD_BEAN%>" property="prescriptionBeans" scope="request"/>
                <bean:size id="size" name="prescriptionBeans"/>
                <logic:equal name="size" value="0" >
                  <TR>
                   <TD colspan="6"><bean:message key="message.no.prescriptions.prescribed"/></TD>
                  </TR>
                </logic:equal>
                <logic:greaterThan name="size" value="0" >
                  <logic:iterate id="rxBean" name="<%=PhysConstants.RECORD_BEAN%>" property="prescriptionBeans" type="com.bea.medrec.beans.PrescriptionBean">
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
              </TABLE>
            </TD>
          </TR>
        </TABLE>
      </TD>
    </TR>
    <!-- END Medications Prescribed -->
  </TABLE>
  <!-- END Record Table -->
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
