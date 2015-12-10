<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.utils.AdminConstants"%>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="Header.jsp" flush="true"/>
<!-- END Header -->

<!-- Start Padding for Content -->
<table WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
  <tr>
    <td>

<!-- Content -->
  <!-- START Breadcrumbs  -->
  <A HREF="home.do"><SPAN CLASS="pagetitle-admin"><bean:message key="link.home"/></SPAN></A> &gt;
  <A HREF="viewimports.do"><SPAN CLASS="pagetitle-admin"><bean:message key="page.title.view.pending.xml"/></SPAN></A> &gt;
  <SPAN CLASS="pagetitle-admin"><bean:message key="page.title.import.confirmation"/></SPAN>
  <!-- END Breadcrumbs -->

  <!-- START Content -->
  <br><br>
  <!-- START Import Content -->
  <p CLASS="title"></p>
  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2">
  <TR>
    <TD>&nbsp;&nbsp;<bean:message key="message.xml.import.complete"/></TD>
  </tr>
  </table>
<!-- END Import Content -->
<!-- END Padding for Content -->

</BODY>
</TABLE>
</html:html>
