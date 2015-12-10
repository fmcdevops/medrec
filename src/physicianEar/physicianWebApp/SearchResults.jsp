<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page session="true"%>

<% response.setHeader("Expires", "0"); %>

<html:html locale="true">
<HEAD>
<META>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.physician.app"/> - <bean:message key="title.patient.search.results"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
  <html:base/>
</meta>
</HEAD>

<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="PhysicianHeader.jsp" flush="true"/>
<!-- START Logout -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
  <tr>
    <td class="physicianbanner3" valign=middle><img src="images/clear.gif" height=1 width=450></td>
    <td class="physicianbanner3"><img src="images/clear.gif"><table width=100% cellpadding=0 cellspacing=4 border=0>
        <tr>
          <td><img src="images/clear.gif" width=10 height=1></td>
          <td><img src="images/clear.gif" width="5" height="1" alt="" border="0"></td>
          <td class="logoutbutton"><A CLASS="logoutbutton" HREF="logout.do"><bean:message key="button.Logout"/></A></TD>
          <td><img src="images/clear.gif" width="10" height="1" alt="" border="0"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- END Logout -->
<!-- END Header -->

<!-- START Padding for Content -->
<table WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<tr>
  <td>

  <!-- START Breadcrumbs -->
  <a href="search.do"><span class="pagetitle-md"><bean:message key="page.title.physcian.home"/></span></a> &gt;
  <span class="pagetitle-md"><bean:message key="page.title.seach.results"/></span>
  <!-- END Breadcrumbs -->
  <br><br>

<br>
<br>

<!-- START Content -->
<p class="title"><bean:message key="page.title.seach.results"/></p>
  <table BORDER="0" CELLSPACING="0" CELLPADDING="2" ALIGN="CENTER">
  <tr><td>&nbsp;&nbsp;</td>
    <td>
      <table BORDER="1" CELLSPACING="0" CELLPADDING="3">
        <TR>
          <TD class="patientbanner2"><bean:message key="Result"/></TD>
          <TD class="patientbanner2"><bean:message key="LastName"/></TD>
          <TD class="patientbanner2"><bean:message key="FirstName"/></TD>
          <TD class="patientbanner2"><bean:message key="MiddleName"/></TD>
          <TD class="patientbanner2"><bean:message key="Gender"/></TD>
          <TD class="patientbanner2"><bean:message key="DOB"/></TD>
          <TD class="patientbanner2"><bean:message key="SSN"/></TD>
        </TR>
        <bean:size id="size" name="patientCollection"/>
        <logic:equal name="size" value="0" >
          <TR>
            <TD colspan=7><bean:message key="message.no.patients.found"/></TD>
          </TR>
        </logic:equal>
        <logic:greaterThan name="size" value="0" >
          <% int i=0; %>
          <logic:iterate id="patientBean" name="patientCollection" type="com.bea.medrec.beans.PatientBean" scope="session">
            <TR>
              <td><center><%=++i%></center></td>
              <td>
                <html:link page="/medicalrecord.do" paramId="id"
                  paramName="patientBean" paramProperty="id">
                  <bean:write name="patientBean" property="lastName"/>
                </html:link>
              &nbsp;</td>
              <td><bean:write name="patientBean" property="firstName"/>&nbsp;</td>
              <td><bean:write name="patientBean" property="middleName"/>&nbsp;</td>
              <td><bean:write name="patientBean" property="gender"/>&nbsp;</td>
              <td><bean:write name="patientBean" property="dob"/>&nbsp;</td>
              <td><bean:write name="patientBean" property="ssn"/>&nbsp;</td>
            </TR>
          </logic:iterate>
         </logic:greaterThan>
      </table>
    </td>
  </tr>
  </table>
<!-- END Content -->

</td>
</tr>
</table>
<!-- END Padding for Content -->


</BODY>
</html:html>
