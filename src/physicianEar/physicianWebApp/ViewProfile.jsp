<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils,
                 com.bea.medrec.utils.PhysConstants" %>

<html:html locale="true">
<HEAD>
<META>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.patient.profile"/></TITLE>
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
    <A href="search.do" ><span class="pagetitle-md"><bean:message key="link.search"/></span></A> &gt;
    <A href="medicalrecord.do" ><span class="pagetitle-md"><bean:message key="page.title.patient.record"/></span></A> &gt;
    <span class="pagetitle-md"><bean:message key="page.title.patient.profile"/></span>
    <br><br>

    <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="6" align=center class="tableborder">
      <TR class="row1">
        <TD class="label"><bean:message key="FirstName"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="firstName"/></TD>
      </TR>
      <TR class="row2">
        <TD class="label"><bean:message key="LastName"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="lastName"/></TD>
      </TR>
      <TR class="row1">
        <TD class="label"><bean:message key="MiddleName"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="middleName"/></TD>
      </TR>
      <TR class="row2">
        <TD class="label"><bean:message key="Gender"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="gender"/></TD>
      </TR>
      <TR class="row1">
        <TD class="label"><bean:message key="DateOfBirth"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="dob"/></TD>
      </TR>
      <TR class="row2">
        <TD class="label"><bean:message key="SocialSecurityNumber"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="ssn"/></TD>
      </TR>
      <TR class="row1">
        <TD class="label"><bean:message key="Phone"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="phone"/></TD>
      </TR>
      <TR class="row2">
        <TD class="label"><bean:message key="Email"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="email"/></TD>
      </TR>
      <TR class="row1">
        <TD class="label"><bean:message key="Address"/></TD>
        <TD><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="address.streetName1"/>
        <br><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="address.streetName2"/>
        <br><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="address.city"/>
        ,&nbsp;<bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="address.state"/>
        &nbsp;&nbsp;<bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="address.zipCode"/>
        <br><bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="address.country"/>
        </TD>
      </TR>
    </table>
  </td>
</tr>
</table>
<!-- END Content -->

</BODY>
</html:html>
