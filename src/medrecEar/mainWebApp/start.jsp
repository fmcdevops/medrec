<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.Locale,
                 org.apache.struts.Globals" %>

<%
  Locale locale = null;
  Cookie cookie = null;
  String country = null;
  String language = null;

  Cookie[] cookies = request.getCookies();
  Cookie languageCookie = null;
  Cookie countryCookie = null;

  if (cookies != null) {
    for (int i=0; i < cookies.length; i++) {
      cookie = cookies[i];
      if (cookie.getName().equals("Language")) language = cookie.getValue();
      else if (cookie.getName().equals("Country")) country = cookie.getValue();
    }
  }

  if (country != null && language != null) {
    locale = new Locale(language, country);
  } else {
    locale = request.getLocale();
    languageCookie = new Cookie("Language", locale.getLanguage());
    countryCookie = new Cookie("Country", locale.getCountry());
    languageCookie.setMaxAge(Integer.MAX_VALUE);
    countryCookie.setMaxAge(Integer.MAX_VALUE);
    response.addCookie(languageCookie);
    response.addCookie(countryCookie);
  }

  if (!(locale.getLanguage().equals("ja") ||
        locale.getLanguage().equals("en") ||
        locale.getLanguage().equals("es"))) {
    locale = new Locale("en", "US");
    languageCookie = new Cookie("Language", "en");
    countryCookie = new Cookie("Country", "US");
    languageCookie.setMaxAge(Integer.MAX_VALUE);
    countryCookie.setMaxAge(Integer.MAX_VALUE);
    response.addCookie(languageCookie);
    response.addCookie(countryCookie);
  }

  request.getSession().setAttribute(Globals.LOCALE_KEY, locale);
%>

<html:html locale="true">
<HEAD>
  <html:base/>
  <TITLE><bean:message key="title.MedRec"/>:  <bean:message key="subtitle.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">


<!-- Title Bar Start -->
<TABLE class="pagetop" WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
  <TR><TD colspan=2 bgcolor="#02669A"><img src="/images/banner_login.gif"></TD></TR>
  <TR><TD colspan=2 bgcolor="#FFFFFF"><img src="/Images/clear.gif" width=1 height=1></TD></TR>
  <TR><TD class="pagetop" valign="middle">&nbsp;&nbsp;<bean:message key="subtitle.MedRec"/></TD>
  <TD valign=middle align=left bgcolor="#C8C8C8"><table width=100% cellpadding=0 cellspacing=6 border=0 align=center valign=middle>
  <tr><td><a href="changelocale.do?Language=en&Country=US" class="languagebutton"><bean:message key="English"/></a></td>
    <td><img src="images/clear.gif" width=10></td>
    <td><a href="changelocale.do?Language=ja&Country=JP" class="languagebutton"><bean:message key="Japanese"/></a></TD>
  </tr></table></td>
</TR>
<TR><TD colspan=2 bgcolor=#8B8B8B><img src="/images/clear.gif" width=1 height=2></TD></TR>
<TR><TD colspan=2 bgcolor=#FFFFFF><img src="/images/clear.gif" width=1 height=2></TD></TR>
</TABLE>
<!-- Title Bar End -->


<BR>
<TABLE WIDTH="90%" BORDER="0" CELLSPACING="0" CELLPADDING="0" align=center class="loginborder">
<TR><td bgcolor=#E9F4F0 align=left><img src="/images/login_patient.gif"></td>
<td align=left class="pagetitlePatient"><bean:message key="Patients"/><img src="images/clear.gif" height=1 width=400></td></TR>
<TR><TD colspan=2 bgcolor=#C8C8C8><img src="images/clear.gif" width=1 height=2></TD></TR>
<TR><TD colspan=2 class="logincontent">
<BR>
<A HREF="/patient/register.do"><b><bean:message key="Register"/></b></A> - <bean:message key="descr.patient.register"/>
<br>
<A HREF="/patient/login.do"><b><bean:message key="Login"/></b></A> <bean:message key="descr.patient.login"/>
<br><br>
</TD></TR></TABLE>

<br>

<TABLE WIDTH="90%" BORDER="0" CELLSPACING="0" CELLPADDING="0" align=center class="loginborder">
<TR><td bgcolor=#D1FFFE align=left><img src="images/login_admin.gif"></td>
<td align=left valign=middle class="pagetitleAdmin"><bean:message key="Administrators"/><img src="images/clear.gif" height=1 width=400></td>
</tr>
<TR><TD colspan=2 bgcolor=#C8C8C8><img src="images/clear.gif" width=1 height=2></TD></TR>
<TR><TD colspan=2 class="logincontent">
<br>
<A HREF="/admin/login.do"><b><bean:message key="Login"/></b></A> <bean:message key="descr.admin.login"/>
<br><br><br>
</TD></TR></TABLE>


<BR>
<TABLE WIDTH="90%" BORDER="0" CELLSPACING="0" CELLPADDING="0" align=center class="loginborder">
<TR><td bgcolor=#FFFFCF align=left><img src="images/login_physician.gif"></td>
<td align=left valign=middle class="pagetitleMd"><bean:message key="Physicians"/><img src="images/clear.gif" height=1 width=400></td></tr>
<TR><TD colspan=2 bgcolor=#C8C8C8><img src="physicianwebApp/images/clear.gif" width=1 height=2></TD></TR>
<TR><TD colspan=2 class="logincontent">
<br>
<A HREF="/physician/login.do"><b> <bean:message key="Login"/></b></A> <bean:message key="descr.physician.login"/>
<br><br><br>
</TD></TR>
</TABLE>
</BODY>
</html:html>
