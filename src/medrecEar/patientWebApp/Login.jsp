<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

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
	<td bgcolor=#006666>
		<div class="pageheaderpatient">
			<bean:message key="page.title.patient.login"/>
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
<!-- Content Start -->
<BR>
<html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/login.do" focus="username">
  <TABLE align=center class="tableborder" cellpadding=10 cellspacing=0>
  <TR>
    <TD>
      <TABLE ALIGN="center" BORDER="0" CELLSPACING="1" CELLPADDING="1">
      <TR>
        <TD>&nbsp;</TD>
        <TD>
          <html:messages id="error" property="invalidLogin">
            <bean:write name="error" filter="false"/>
          </html:messages>
        </TD>
      </TR>
      <TR>
      </TR>
      <TR>
        <TD class="label"><bean:message key="Username"/></TD>
        <TD>
          <html:text
           property="username"
           size="20"
           maxlength="45"
           tabindex="1"/>
        </TD>
      </TR>
      <TR>
        <TD></TD>
        <TD align="left">
          <html:messages id="error" property="username">
            <bean:write name="error" filter="false"/>
          </html:messages>
        </TD>
      </TR>
      <TR>
        <TD class="label"><bean:message key="Password"/></TD>
        <TD>
         <html:password
          property="password"
          size="20"
          maxlength="10"
          redisplay="false"
          tabindex="2"/>
        </TD>
      </TR>
      <TR>
        <TD></TD>
        <TD align="left">
          <html:messages id="error" property="password">
            <bean:write name="error" filter="false"/>
          </html:messages>
        </TD>
      </TR>
      <TR>
        <TD></TD>
        <TD>
          <BR>
          <html:submit property="action" tabindex="3" styleclass="profilebutton">
           <bean:message key="button.Login"/>
          </html:submit>
          <html:cancel tabindex="4" styleclass="cancelbutton">
            <bean:message key="button.Cancel"/>
          </html:cancel>
        </TD>
      </TR>
      </TABLE>
    </TD>
  </TR>
  </TABLE>
</html:form>
<!-- Content End -->

</BODY>
</html:html>
