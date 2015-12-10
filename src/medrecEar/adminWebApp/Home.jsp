<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="Header.jsp" flush="true"/>
<!-- END Header -->

<table WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
  <tr>
    <td>
      <SPAN CLASS="pagetitle-admin"><bean:message key="link.home"/></SPAN>
      <br>
      <br>
      <table BORDER="0" CELLSPACING="1" CELLPADDING="0">
        <tr>
          <td><b><bean:message key="page.title.administration.tasks"/></b></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;<A HREF="viewrequests.do"><bean:message key="link.view.pending.requests"/></A>
          <br>&nbsp;&nbsp;<bean:message key="desc.approve.deny.registration"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;<A HREF="viewimports.do"><bean:message key="link.import.records"/></A>
          <br>&nbsp;&nbsp;<bean:message key="desc.upload.records"/></td>
        </tr>
      </table>

    </td>
  </tr>
</table>
</BODY>
</html:html>
