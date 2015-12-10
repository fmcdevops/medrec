<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgCOLOR="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Title Bar  -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td bgcolor="#CC3300" align=left><img src="images/physician_top_banner3.gif"></td>
		<td bgcolor="#CC3300"><div class="pageheaderphysician"><bean:message key="page.title.physician.login"/></div></td>
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
<html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/login.do" focus="username">
	<br>
	<TABLE align=center class="tableborder" cellpadding=10 cellspacing=0>
	<TR>
	  <TD>&nbsp;</TD>
	  <TD>
	    <html:messages id="error" property="invalidLogin">
	      <bean:write name="error" filter="false"/>
	    </html:messages>
	  </TD>
	</TR>
  <TR>
	  <TD class="label"><bean:message key="Username"/></TD>
	  <TD><html:text
	        property="username"
	        size="20"
	        maxlength="45"
	        value="mary@md.com"
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
    <TD><html:password
          property="password"
          size="20"
          maxlength="10"
          redisplay="false"
          value="weblogic"
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
      <html:submit property="action" styleClass="profilebutton">
       <bean:message key="button.Login"/>
      </html:submit>
      <html:cancel styleClass="cancelbutton">
        <bean:message key="button.Cancel"/>
      </html:cancel>
    </TD>
  </TR>
  </TABLE>
</TABLE>
</html:form>
<!-- END Content  -->

</td></tr></table>
<!-- END Padding for Content -->


</BODY>
</html:html>
