<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils" %>

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
    <!--  FIXME - Cancel alert needed.  -->
    <!-- START Breadcrumbs -->
    <A href="search.do" ><span class="pagetitle-md"><bean:message key="page.title.physcian.home"/></span></A> &gt;
    <A href="medicalrecord.do" ><span class="pagetitle-md"><bean:message key="page.title.patient.record"/></span></A> &gt;
    <A href="visit.do" ><span class="pagetitle-md"><bean:message key="page.title.new.visit"/></span></A> &gt;
    <span class="pagetitle-md"><bean:message key="page.title.new.prescription"/></span>
    <!-- END Breadcrumbs -->
    <br><br>

    <!-- START Form -->
    <html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="rx.do" focus="drug">
    <TABLE width="100%" BORDER="0" CELLSPACING="1" CELLPADDING="1">
      <TR>
        <TD class="label" colspan="2"><bean:message key="all.fields.required"/><br><br></TD>
      </TR>
      <!-- START Date -->
      <TR>
        <TD class="label">Date</TD>
        <TD>
          <%=MedRecWebAppUtils.getCurrentDate()%>
        </TD>
      </TR>
      <!-- END Date -->
      <!-- START Medication -->
      <TR>
        <TD class="label"><bean:message key="Medication"/></TD>
        <TD>
          <html:text
            property="drug"
            style="WIDTH: 250px; HEIGHT: 22px" size="30"
            maxlength="30"
            tabindex="1"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <html:messages id="error" property="drug">
            <bean:write name="error" filter="false"/>
          </html:messages>&nbsp;
        </TD>
      </TR>
      <!-- END Medication -->
      <!-- START Dosage -->
      <TR>
        <TD class="label"><bean:message key="Dosage"/></TD>
        <TD>
          <html:text
            property="dosage"
            style="WIDTH: 250px; HEIGHT: 22px" size="30"
            maxlength="30"
            tabindex="2"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <html:messages id="error" property="dosage">
            <bean:write name="error" filter="false"/>
          </html:messages>&nbsp;
        </TD>
      </TR>
      <!-- END Dosage -->
      <!-- START Frequency -->
      <TR>
        <TD class="label"><bean:message key="Frequency"/></TD>
        <TD>
          <html:text
            property="frequency"
            style="WIDTH: 250px; HEIGHT: 22px" size="30"
            maxlength="30"
            tabindex="3"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <html:messages id="error" property="frequency">
            <bean:write name="error" filter="false"/>
          </html:messages>&nbsp;
        </TD>
      </TR>
      <!-- END Frequency -->
      <!-- START Refills -->
      <TR>
        <TD class="label"><bean:message key="Refills"/>&nbsp;(#)</TD>
        <TD>
          <html:text
            property="refillsRemaining"
            style="WIDTH: 50px; HEIGHT: 22px" size="5"
            maxlength="5"
            tabindex="4"/>
        </TD>
      </TR>
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <html:messages id="error" property="refillsRemaining">
            <bean:write name="error" filter="false"/>
          </html:messages>
          &nbsp;
        </TD>
      </TR>
      <!-- END Refills -->
      <!-- START Instructions -->
      <TR>
        <TD class="label" valign="top"><bean:message key="Instructions"/>
          &nbsp;<font size=2pt>(<bean:message key="optional"/>)</font>
        </TD>
        <TD>
          <html:textarea
            style="WIDTH: 420px; HEIGHT: 90px"
            rows="15" cols="77"
            property="instructions"
            tabindex="5"/>
          <br>
        </TD>
      </TR>
      <!-- END Instructions -->
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <br><br>
          <html:submit property="actionRx" tabindex="6" styleClass="graybutton">
            <bean:message key="button.Save"/>
          </html:submit>
          <html:submit property="actionRx" tabindex="7" styleClass="graybutton">
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
<!-- END Content -->

</BODY>
</html:html>
