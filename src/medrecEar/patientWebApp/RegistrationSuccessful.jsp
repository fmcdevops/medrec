<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:html locale="true">
<HEAD>
  <html:base/>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- Patient Header Begin -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
  <td bgcolor="#006666" align=left>
    <img src="images/patient_top_banner3.gif">
  </td>
  <td bgcolor=#006666>
    <div class="pageheaderpatient">
      <bean:message key="page.title.patient.registration"/>
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
<!-- Patient Header End -->

<!-- Padding for Content Start -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10"><TR><TD>

<!-- Content -->
<p class="pagetitle-patient"><bean:message key="pagetitle.registration.successful"/></p>
<br>
<bean:message key="message.registration.successful"/>
<br><br>
<html:link href="/start.jsp"><bean:message key="link.return.home"/></html:link>
<!-- Content END -->
</TD>
</TR>
</TABLE>
</BODY>
</html:html>
