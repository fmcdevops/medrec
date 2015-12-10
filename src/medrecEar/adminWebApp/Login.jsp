<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<HEAD>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>

<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- Title Bar Start -->
<table width="100%" cellpadding=0 cellspacing=0 border=0>
  <tr>
    <td bgcolor="#669900" align=left><img src="images/admin_top_banner3.gif"></td>
    <td bgcolor=#669900><div class="pageheaderadmin"><bean:message key="page.title.admin.login"/></div></td>
  </tr>
  <tr>
    <td bgcolor="#D1FFFE"><img src="images/admin_banner_photo.jpg"></td>
    <td bgcolor="#D1FFFE">&nbsp;</td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF" height=1 colspan=2><img src="images/clear.gif" width="100" height="1" alt="" border="0"></td>
  </tr>

</table>
<!-- Title Bar End -->


<!-- Padding for Content Start -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
  <TR>
    <TD>
      <!-- Content -->
      <br>
      <html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="login.do" focus="username">
      <TABLE align=center class="tableborder" cellpadding=10 cellspacing=0 border=0>
        <TR>
          <TD>
            <TABLE ALIGN="center" BORDER="0" CELLSPACING="1" CELLPADDING="1">
              <TR>
                <TD></TD>
                <TD>
                  <html:messages id="error" property="invalidLogin">
                    <bean:write name="error" filter="false"/>
                  </html:messages>
                </TD>
              </TR>
              <TR>
                <TD class="label"><bean:message key="Username"/></TD>
                <TD>
                  <html:text property="username"
                    size="20"
                    maxlength="45"
                    tabindex="1"/>
                </TD>
                <TR>
                  <TD></TD>
                  <TD align="left">
                    <html:messages id="error" property="username">
                      <bean:write name="error" filter="false"/>
                    </html:messages>
                  </TD>
                </TR>
              </TR>
              <TR>
                <TD class="label"><bean:message key="Password"/></TD>
                <TD>
                  <html:password property="password"
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
                    <html:submit property="action" tabindex="3" styleClass="profilebutton">
                     <bean:message key="button.Login"/>
                    </html:submit>
                    <html:cancel tabindex="4" styleClass="cancelbutton">
                      <bean:message key="button.Cancel"/>
                    </html:cancel>
                </TD>
              </TR>
            </TABLE>
          </TD>
        </TR>
      </TABLE>
      </html:form>
    </td>
  </tr>
</table>



</BODY>
</html:html>
