<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.beans.ErrorBean"%>

<jsp:useBean id="errorBean" scope="request" type="com.bea.medrec.beans.ErrorBean"/>

<html:html locale="true">
<HEAD>
  <html:base/>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="Error"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgCOLOR="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Title Bar  -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
  <tr>
    <td bgcolor="#CC3300" align=left><img src="images/physician_top_banner3.gif"></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFCC"><img src="images/physician_banner_photo.jpg" width="354" height="65"></td>
    <td bgcolor="#FFFFCC"><BR></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF" height=1 colspan=2><img src="images/clear.gif" width="100" height="1" alt="" border="0"></td>
  </tr>
</table>
<!-- Title Bar END -->

<!-- START Padding for Content  -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10"><TR><TD>

<!-- START Content  -->
<p class="pagetitle-md"><bean:message key="page.title.error.occured"/></p>
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

</td></tr></table>
<!-- END Padding for Content -->

</BODY>
</html:html>
