<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.bea.medrec.utils.PhysConstants" %>

<html:html locale="true">
<!-- START Title Bar -->
<table width=100% cellpadding=0 cellspacing=0 border=0>


<tr>
  <td bgcolor="#CC3300" align=left><img src="images/physician_top_banner3.gif"></td>
  <td bgcolor="#CC3300"><div class="pageheaderphysician">Dr.&nbsp;<bean:write name="<%=PhysConstants.PHYSICIAN_BEAN%>" property="firstName" scope="session"/>&nbsp;<bean:write name="<%=PhysConstants.PHYSICIAN_BEAN%>" property="lastName" scope="session"/></div></td>
</tr>


<tr><td bgcolor="#FFFFCC"><img src="images/physician_banner_photo.jpg" width="354" height="65"></td>
<td bgcolor="#FFFFCC"><BR></td></tr>

<tr><td bgcolor="#FFFFFF" height=1 colspan=2><img src="images/clear.gif" width="100" height="1" alt="" border="0"></td>
</tr></table>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
  <td class="patientname2" valign=middle align=left>Patient Info: <bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="lastName" scope="session"/>,
                <bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="firstName" scope="session"/>
                <bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="middleName" scope="session"/>
</td>
  <td class="patientdob2">DOB: <bean:write name="<%=PhysConstants.PATIENT_BEAN%>" property="dob" scope="session"/></td>
  <td class="patientbanner2" valign=middle><table width=100% cellpadding=0 cellspacing=4 border=0>
    <tr>
      <td><img src="images/clear.gif" width="10" height="1" alt="" border="0"></td>
      <td class="profilebutton"><A HREF="profile.do" class="profilebutton"><bean:message key="link.profile"/></A></td>
      <td><img src="images/clear.gif" width="5" height="1" alt="" border="0"></td><td class="logoutbutton">
      <a class="logoutbutton" href="logout.do"><bean:message key="link.logout"/></a></td><td><img src="images/clear.gif" width="10" height="1" alt="" border="0"></td>
</tr></table></td></tr>

</table>
</html:html>
