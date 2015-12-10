<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils,
                 com.bea.medrec.utils.PhysConstants" %>

<html:html locale="true">
<HEAD>
<META>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.physician.app"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
  <html:base/>
</HEAD>

<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="PatientHeader.jsp" flush="true"/>
<!-- END Header -->

<!-- START Content -->
<table WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<tr>
  <td>
    <!-- START Breadcrumbs -->
    <A href="search.do" ><span class="pagetitle-md"><bean:message key="page.title.physcian.home"/></span></A> &gt;
    <A href="medicalrecord.do" ><span class="pagetitle-md"><bean:message key="page.title.patient.record"/></span></A> &gt;
    <span class="pagetitle-md"><bean:message key="page.title.new.visit"/></span>
    <!-- END Breadcrumbs -->
    <br><br>

    <!-- START Form -->
    <html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/visit.do" focus="symptoms">
      <TABLE width="100%" BORDER="0" CELLSPACING="1" CELLPADDING="1">
      <!-- START Date -->
      <TR>
        <TD class="label"><bean:message key="Date"/></TD>
        <TD>
          <%=MedRecWebAppUtils.getCurrentDate()%>
        </TD>
      </TR>
      <!-- END Date -->
      <!-- START Reason for Visit -->
      <TR>
        <TD class="label"><bean:message key="VisitReason"/></TD>
      <TD>
        <html:text
          name="recordBean"
          property="symptoms"
          style="WIDTH: 420px; HEIGHT: 22px" size="77"
          maxlength="77"
          tabindex="1"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>&nbsp;
          <html:messages id="error" property="symptoms">
            <bean:write name="error" filter="false"/>
          </html:messages>
        </TD>
      </TR>
      <!-- END Reason for Visit -->
      <!-- START Vital Signs -->
      <TR>
        <TD class="label"><bean:message key="VitalSigns"/></TD>
        <TD>
            <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="1">
            <TR>
              <!-- START Temperature -->
              <TD align="right" class="label" align="left">
                <bean:message key="Temperature"/>&nbsp;<font size=2pt>(F)</font>
                &nbsp;
              </TD>
              <TD><html:text
                   name="recordBean"
                   property="vitalSignsBean.temperature"
                   style="WIDTH: 55px; HEIGHT: 22px" size="7"
                   maxlength="5"
                   tabindex="2"/>
              </TD>
              <!-- END Temperature -->
              <!-- START Weight -->
              <TD align="right" class="label" align="left">
                <bean:message key="Weight"/>&nbsp;<font size=2pt>(lbs)</font>
                &nbsp;
              </TD>
              <TD><html:text
                   name="recordBean"
                   property="vitalSignsBean.weight"
                   style="WIDTH: 55px; HEIGHT: 22px" size="7"
                   maxlength="3"
                   tabindex="3"/>
              </TD>
              <!-- END Weight -->
            </TR>
            <TR>
              <!-- START Temperature Errors -->
              <TD colspan="2" align="right">&nbsp;
                <html:messages id="error" property="vitalSignsBean.temperature">
                  <bean:write name="error" filter="false"/>
                </html:messages>
              </TD>
              <!-- END Temperature Errors -->
              <!-- START Weight Errors -->
              <TD colspan="2" align="right">
                &nbsp;<html:messages id="error" property="vitalSignsBean.weight">
                  <bean:write name="error" filter="false"/>
                </html:messages>
              </TD>
              <!-- END Weight Errors -->
            </TR>
            <TR>
              <!-- START Pulse -->
              <TD align="right" class="label" align="left">
                <bean:message key="Pulse"/>&nbsp;<font size=2pt>(bpm)</font>
                &nbsp;
              </TD>
              <TD><html:text
                   name="recordBean"
                   property="vitalSignsBean.pulse"
                   style="WIDTH: 55px; HEIGHT: 22px" size="7"
                   maxlength="3"
                   tabindex="4"/>
              </TD>
              <!-- END Pulse -->
              <!-- START Height -->
              <TD align="right" class="label" align="left">
                <bean:message key="Height"/>&nbsp;<font size=2pt>(inches)</font>
                &nbsp;
              </TD>
              <TD><html:text
                   name="recordBean"
                   property="vitalSignsBean.height"
                   style="WIDTH: 55px; HEIGHT: 22px" size="7"
                   maxlength="2"
                   tabindex="5"/>
              </TD>
              <!-- END Height -->
            </TR>
            <TR>
              <!-- START Pulse Errors -->
              <TD colspan="2" align="right">&nbsp;
                <html:messages id="error" property="vitalSignsBean.pulse">
                  <bean:write name="error" filter="false"/>
                </html:messages>
              </TD>
              <!-- END Pulse Errors -->
              <!-- START Height Errors -->
              <TD colspan="2" align="right">&nbsp;
                <html:messages id="error" property="vitalSignsBean.height">
                  <bean:write name="error" filter="false"/>
                </html:messages>
              </TD>
              <!-- END Height Errors -->
            </TR>
            <TR>
              <!-- START Blood Pressure -->
              <TD align="right" class="label" align="left">
                <bean:message key="BloodPressure"/>&nbsp;<font size=2pt>(systolic/diastolic)</font>
                &nbsp;
              </TD>
              <TD><html:text
                   name="recordBean"
                   property="vitalSignsBean.bloodPressure"
                   style="WIDTH: 66px; HEIGHT: 22px" size="9s"
                   maxlength="7"
                   tabindex="6"/>
              </TD>
              <!-- END Blood Pressure -->
              <TD colspan="2">&nbsp;</TD>
            <TR>
              <!-- START Blood Pressure Errors -->
              <TD colspan="2" align="right">&nbsp;
                <html:messages id="error" property="vitalSignsBean.bloodPressure">
                  <bean:write name="error" filter="false"/>
                </html:messages>
              </TD>
              <!-- END Blood Pressure Errors -->
              <TD colspan="2">&nbsp;</TD>
            </TR>
            </TR>
            </TABLE>
        </TD>
      </TR>
      <!-- END Vital Signs -->
      <!-- START Exam Notes -->
      <TR>
        <TD class="label" valign="top"><bean:message key="ExamNotes"/></TD>
        <TD>
          <html:textarea
           name="recordBean"
           style="WIDTH: 420px; HEIGHT: 90px"
           rows="15" cols="77"
           property="notes"
           tabindex="7"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>&nbsp;
          <html:messages id="error" property="notes">
            <bean:write name="error" filter="false"/>
          </html:messages>
        </TD>
      </TR>
      <!-- END Exam Notes -->
      <!-- START Diagnosis -->
      <TR>
        <TD class="label" valign="top"><bean:message key="Diagnosis"/></TD>
        <TD>
          <html:textarea
           name="recordBean"
           style="WIDTH: 420px; HEIGHT: 90px"
           rows="15" cols="77"
           property="diagnosis"
           tabindex="8"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>&nbsp;
          <html:messages id="error" property="diagnosis">
            <bean:write name="error" filter="false"/>
          </html:messages>
        </TD>
      </TR>
      <!-- END Diagnosis -->
      <TR>
        <TD class="label"><bean:message key="MedicationsPrescribed"/></TD>
        <TD>
          <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
          <TR>
            <TD class="patientbanner2"><bean:message key="DateStarted"/></TD>
            <TD class="patientbanner2"><bean:message key="Rx"/></TD>
            <TD class="patientbanner2"><bean:message key="Dosage"/></TD>
            <TD class="patientbanner2"><bean:message key="Frequency"/></TD>
            <TD class="patientbanner2"><bean:message key="Refills"/></TD>
            <TD class="patientbanner2"><bean:message key="Instructions"/></TD>
            <TD class="patientbanner2"><bean:message key="Keep"/></TD>
          </TR>
          <bean:define id="prescriptionBeans" name="recordBean"
            property="prescriptionBeans" scope="session"/>
          <bean:size id="size" name="prescriptionBeans"/>
          <logic:equal name="size" value="0" >
            <TR>
              <TD colspan="7"><bean:message key="message.no.prescriptions.prescribed"/></TD>
            </TR>
          </logic:equal>
          <logic:greaterThan name="size" value="0" >
            <% int i=0; %>
            <logic:iterate id="prescriptionBean" name="recordBean"
              property="prescriptionBeans"
              type="com.bea.medrec.beans.PrescriptionBean" scope="session">
              <TR>
                <td><bean:write name="prescriptionBean" property="datePrescribed"/></td>
                <td><bean:write name="prescriptionBean" property="drug"/></td>
                <td><bean:write name="prescriptionBean" property="dosage"/></td>
                <td><bean:write name="prescriptionBean" property="frequency"/></td>
                <td><bean:write name="prescriptionBean" property="refillsRemaining"/></td>
                <td><bean:write name="prescriptionBean" property="instructions"/>&nbsp;</td>
                <td><a href="visit.do?<%=PhysConstants.ACTION%>=<%=PhysConstants.DELETE_RX%>&<%=PhysConstants.RX_ID%>=<%=i++%>">Delete</a></td>
              </TR>
            </logic:iterate>
          </logic:greaterThan>
          </TABLE>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <br>
          <html:submit property="action" tabindex="9">
            <bean:message key="button.Prescribe.Medication"/>
          </html:submit>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <br>
          <html:submit property="action" tabindex="10" styleClass="graybutton">
            <bean:message key="button.Save"/>
          </html:submit>
          <html:submit property="action" tabindex="11" styleClass="graybutton">
            <bean:message key="button.Reset"/>
          </html:submit>
          <html:submit property="action" tabindex="12" styleClass="graybutton">
            <bean:message key="button.Cancel"/>
          </html:submit>

        </TD>
      </TR>
    </TABLE>
  </html:form>
  <!-- END Form -->

  </td>
</tr>
</table>
<br>
<br>
<!-- END Content -->

</BODY>
</html:html>
