<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.beans.RecordBean" %>
<%@ page session="true"%>

<%
  RecordBean r = (RecordBean)session.getAttribute("recordBean");
  System.out.println("R: "+(r != null ? r.toString() : "null"));
%>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.physician.app"/></TITLE>
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
    <!-- START Breadcrumbs -->
    <A href="search.do" >
      <span class="pagetitle-md">
        <bean:message key="page.title.physcian.home"/>
      </span>
    </A> &gt;
    <A href="medicalrecord.do" >
      <span class="pagetitle-md">
        <bean:message key="page.title.patient.record"/>
      </span>
    </A> &gt;
    <span class="pagetitle-md"><bean:message key="page.title.new.visit.confirmation"/></span>
    <br><br>
    <bean:message key="message.record.saved"/>
    <!-- END Breadcrumbs -->
  </td>
</tr>
</table>
<!-- END Content -->

</BODY>
</html:html>
