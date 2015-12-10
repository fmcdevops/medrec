<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html locale="true">
<HEAD>
  <html:base/>
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
<a href="medicalrecord.do"><span class="pagetitle-patient"><bean:message key="link.home"/></span></a> &gt;
<span class="pagetitle-patient"><bean:message key="page.title.patient.profile"/></span>
<br><br>




<TABLE BORDER="0" CELLSPACING="1" CELLPADDING="6" align=center class="tableborder">
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
    <TD class="label"><bean:message key="DOB"/></TD>
    <TD><bean:write name="patientBean" property="dob"/></TD>
  </TR>
  <TR class="row2">
    <TD class="label"><bean:message key="SSN"/></TD>
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
    ,&nbsp<bean:write name="patientBean" property="address.state"/>
    &nbsp&nbsp<bean:write name="patientBean" property="address.zipCode"/>
    <br><bean:write name="patientBean" property="address.country"/>
    </TD>
  </TR>
  <TR>
    <TD></TD>
    <TD>
      <br>
      <a href="editprofile.do" class="profilebutton"><bean:message key="link.edit.profile"/></a>
    </TD>
  </TR>
</TABLE>
<!-- END Content -->

  </tr>
</table>
<!-- END Padding for Content -->

</BODY>
</html:html>
