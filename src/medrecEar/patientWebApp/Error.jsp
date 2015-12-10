<%@ page import="com.bea.medrec.beans.ErrorBean"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<jsp:useBean id="errorBean" scope="request" type="com.bea.medrec.beans.ErrorBean"/>

<html:html locale="true">
<HEAD>
  <html:base/>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgCOLOR="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- Patient Header Begin -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
  <td bgcolor="#006666" align=left>
    <img src="images/patient_top_banner3.gif">
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

<!-- START Padding for Content  -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<TR><TD>

  <!-- START Content  -->
  <p class="pagetitle-patient"><bean:message key="message.error"/></p>
  <BR>
  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="5">
  <TR>
    <TD>
    <%
      ErrorBean eBean = (ErrorBean)request.getAttribute("errorBean");
      String errMsg = eBean.getErrMessage();
    %>
    &nbsp;
    <%=(errMsg == null || errMsg.equals("") ? "Unknown error." : errMsg)%>
      <br><br>
      &nbsp;<a href="<jsp:getProperty name="errorBean" property="link"/>"><bean:message key="link.return"/></a>
    </TD>
  </TR>
  </TABLE>
  <!-- END Content  -->

  </TD></TR>
</TABLE>
<!-- END Padding for Content -->

</BODY>
</html:html>
