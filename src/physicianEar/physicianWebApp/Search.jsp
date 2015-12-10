<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<HEAD>
<META>
  <TITLE><bean:message key="title.MedRec"/> - <bean:message key="title.physician.app"/> - <bean:message key="title.patient.search"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
  <SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
  </SCRIPT>
  <html:base/>
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
    <!-- Content -->
    <br>
    <br>
    <html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/searchresults.do" focus="lastName">
    <TD align=left class="pagetitle-admin"><bean:message key="patient.lookup"/></p>
    <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="6" align=center class="tableborder">
    <TR>
      <TD>
        <TABLE BORDER="0" CELLSPACING="1" CELLPADDING="1">
        <TR>
          <TD style=instructions colspan="2"><bean:message key="descr.patient.search"/>
            <br><br>
          </TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>
            <html:messages id="error" property="multipleEntries">
              <bean:write name="error" filter="false"/>
            </html:messages>
          </TD>
        <TR>
          <TD class="label"><bean:message key="LastName"/></TD>
          <TD>
            <html:text
              name="searchBean"
              property="lastName"
              style="WIDTH: 210px; HEIGHT: 22px" size="30"
              maxlength="30"
              tabindex="1"/>
          </TD>
        </TR>
        <TR>
          <TD></TD>
          <TD><bean:message key="or"/></TD>
        </TR>
        <TR>
          <TD class="label"><bean:message key="SocialSecurityNumber"/></TD>
          <TD>
            <html:text
              name="searchBean"
              property="ssn"
              style="WIDTH: 80px; HEIGHT: 22px" size="9"
              maxlength="9"
              tabindex="2"/>
          </TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>
            <html:messages id="error" property="ssn">
              <bean:write name="error" filter="false"/>
            </html:messages>
          </TD>
        </TR>
        <TR>
          <TD></TD>
          <TD>
            <html:submit property="action" tabindex="3" styleClass="profilebutton">
              <bean:message key="button.Search"/>
            </html:submit>
          </TD>
        </TR>
        </TABLE>
      </TD>
    </TR>
    </TABLE>
    </html:form>
    <!-- END Content -->

  </td>
</tr>
</table>
<!-- END Padding for Content -->


</BODY>
</html:html>
