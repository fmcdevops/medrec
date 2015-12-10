<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.utils.PatientConstants" %>

<html:html locale="true">
<!-- Patient Header Begin -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
  <td bgcolor="#006666" align=left>
    <img src="images/patient_top_banner3.gif">
  </td>
  <td bgcolor=#006666>
    <div class="pageheaderpatient">
      <bean:message key="patient.info"/>
    </div>
  </td>
</tr>
<tr>
  <td bgcolor=#E9F4F0>
    <img src="images/patient_banner_photo.jpg">
  </td>
  <td bgcolor=#E9F4F0>
    <BR>
  </td>
</tr>
</table>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
  <td class="patientname" valign=middle align=left>
    Patient Info:
    <bean:write name="<%=PatientConstants.PATIENT_BEAN%>" property="lastName" scope="session"/>,&nbsp;
    <bean:write name="<%=PatientConstants.PATIENT_BEAN%>" property="firstName" scope="session"/>&nbsp;
    <bean:write name="<%=PatientConstants.PATIENT_BEAN%>" property="middleName" scope="session"/>
  </td>
  <td class="patientdob">DOB:&nbsp;<bean:write name="<%=PatientConstants.PATIENT_BEAN%>" property="dob" scope="session"/></td>
  <td class="patientbanner" valign=middle>
    <table width=100% cellpadding=0 cellspacing=4 border=0>
      <tr>
        <td><img src="images/clear.gif" width=10 height=1></td>
        <td class="profilebutton"><html:link page="/viewprofile.do" styleclass="profilebutton"><bean:message key="link.profile"/></html:link></td>
        <td><img src="images/clear.gif" width="5" height="1" alt="" border="0"></td>
        <td class="logoutbutton"><A CLASS="logoutbutton" HREF="logout.do"><bean:message key="link.logout"/></A></td>

        <td><img src="images/clear.gif" width="10" height="1" alt="" border="0"></td>
      </tr>
    </table>
  </td>
</tr>
</table>

<!-- Patient Header End -->

<TABLE CLASS="tab-color" WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
  <TR>
    <TD><IMG SRC="images/transparent.gif" WIDTH="1" HEIGHT="4"/></TD>
  </TR>
</TABLE>
</body>



</html:html>
