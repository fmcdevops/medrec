<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<HEAD>
  <html:base/>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <LInk rel="stylesheet" type="text/css" href="stylesheet.css">
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
    <!-- START Breadcrumbs  -->
    <A HREF="medicalrecord.do">
      <SPAN CLASS="pagetitle-patient">
        <bean:message key="link.home"/>
      </SPAN>
    </A> &gt;
    <SPAN CLASS="pagetitle-patient"><bean:message key="page.title.patient.visit.summary"/></SPAN>
    <!-- END Breadcrumbs -->
    <br>
    <br>
    <!-- START Record Table  -->
    <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="2">
    <!-- START Date -->
    <TR class="row1">
      <TD class="label"><bean:message key="Date"/></TD>
      <TD><bean:write name="recordBean" property="date"/></TD>
    </TR>
    <!-- END Date -->
    <!-- START Reason for Visit -->
    <TR class="row2">
      <TD class="label"><bean:message key="VisitReason"/></TD>
      <TD><bean:write name="recordBean" property="symptoms"/></TD>
    </TR>
    <!-- END Reason for Visit -->
    <TR>
      <TD></TD>
      <TD>&nbsp;</TD>
    </TR>
    <!-- START Vital Signs  -->
    <TR class="row1">
      <TD class="label"><bean:message key="VitalSigns"/></TD>
      <TD>
        <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="3">
      <bean:define id="vitalSignsBean" name="recordBean" property="vitalSignsBean" scope="request"/>
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
            <TD><bean:write name="vitalSignsBean" property="height"/>&nbsp; inches</TD>
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
      <TD  class="label" valign="top"><bean:message key="ExamNotes"/></TD>
      <TD>
        <bean:write name="recordBean" property="notes"/>
        <br>
        <br>
      </TD>
    </TR>
    <!-- END Exam Notes -->
    <!-- START Diagnosis -->
    <TR class="row1">
      <TD  class="label" valign="top"><bean:message key="Diagnosis"/></TD>
      <TD>
        <bean:write name="recordBean" property="diagnosis"/>
      </TD>
    </TR>
    <!-- END Diagnosis -->
    <TR>
      <TD>&nbsp;</TD>
    </TR>
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
                <TD class=coltitle-patient><bean:message key="DateStarted"/></TD>
                <TD class=coltitle-patient>Rx</TD>
                <TD class=coltitle-patient><bean:message key="Dosage"/></TD>
                <TD class=coltitle-patient><bean:message key="Frequency"/></TD>
                <TD class=coltitle-patient><bean:message key="Refills"/></TD>
                <TD class=coltitle-patient><bean:message key="Instructions"/></TD>
              </TR>
              <bean:define id="rxBeans" name="recordBean" property="prescriptionBeans" scope="request"/>
              <bean:size id="size" name="rxBeans"/>
              <logic:equal name="size" value="0" >
              <TR>
                <TD colspan="6"><bean:message key="message.no.prescriptions.prescribed"/></TD>
              </TR>
              </logic:equal>
              <logic:greaterThan name="size" value="0" >
                <logic:iterate id="rxBean" name="recordBean" property="prescriptionBeans" type="com.bea.medrec.beans.PrescriptionBean">
                <TR>
                  <td><bean:write name="rxBean" property="datePrescribed"/></td>
                  <td><bean:write name="rxBean" property="drug"/></td>
                  <td><bean:write name="rxBean" property="dosage"/></td>
                  <td><bean:write name="rxBean" property="frequency"/></td>
                  <td><bean:write name="rxBean" property="refillsRemaining"/></td>
                  <td><bean:write name="rxBean" property="instructions"/></td>
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
