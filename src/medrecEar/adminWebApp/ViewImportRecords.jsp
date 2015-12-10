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
  <SPAN CLASS="pagetitle-admin"><bean:message key="page.title.view.pending.xml"/></SPAN>
  <!-- END Breadcrumbs -->

  <!-- START Content -->
  <br><br>
  <!-- START Import Content -->
  <p CLASS="title"><bean:message key="PendingXMLFiles"/></p>
  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2">
  <TR>
    <TD>&nbsp;&nbsp;</TD>
    <TD>
      <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="2">
      <TR>
        <TD class="coltitle-admin"><bean:message key="Result"/></TD>
        <TD class="coltitle-admin"><bean:message key="File"/></TD>
        <TD class="coltitle-admin"><bean:message key="Date"/></TD>
        <TD class="coltitle-admin" align="center"><bean:message key="Size"/> (b)</TD>
        <TD class="coltitle-admin" colspan="2" align="center"><bean:message key="Action"/></TD>
      </TR>
      <!-- START Dynamic Import Content -->
      <bean:define id="xmlFileBeans" name="<%=AdminConstants.IMPORT_BEANS%>" scope="request"/>
      <bean:size id="numOfFile" name="xmlFileBeans"/>
      <logic:equal name="numOfFile" value="0" >
          <TR><TD colspan="6"><bean:message key="message.no.xml.files"/></TD></TR>
      </logic:equal>
      <logic:greaterThan name="numOfFile" value="0" >
        <% int i=0; %>
        <logic:iterate id="xmlFileBean" name="xmlFileBeans" type="com.bea.medrec.beans.XMLImportBean">
          <TR>
            <td><center><%=++i%></center></td>
            <td><bean:write name="xmlFileBean" property="filename"/>
            <td><bean:write name="xmlFileBean" property="date"/></td>
            <td align="right"><bean:write name="xmlFileBean" property="size"/></td>
            <td>
              <center>
              <html:link page="/import.do?action=upload" paramId="<%=AdminConstants.XML_FILE%>"
                paramName="xmlFileBean" paramProperty="filename">
                <bean:message key="link.upload"/>
              </html:link>
              </center>
            </td>
          </TR>
        </logic:iterate>
      </logic:greaterThan>
      <!-- END Dynamic Import Content -->
      </table>
    </td>
  </tr>
  </table>
<!-- END Visit Content -->
<!-- END Padding for Content -->
</TABLE>
</BODY>
</html:html>
