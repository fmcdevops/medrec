<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page import="com.bea.medrec.utils.MedRecWebAppUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<HEAD>
  <html:base/>
  <title><bean:message key="title.MedRec"/></title>
  <link rel="stylesheet" type="text/css" href="stylesheet.css">
</HEAD>
<BODY bgColor="white" leftMargin="0" topMargin="0" rightMargin="0" MARGINHEIGHT="0" MARGINWIDTH="0">

<!-- Patient Header Begin -->
<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<td bgcolor="#006666" align=left>
		<img src="images/patient_top_banner3.gif">
	</td>
	<td bgcolor=#006666>
		<div class="pageheaderpatient">
			<bean:message key="page.title.patient.registration"/>
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

<!-- Padding for Content Start -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<TR><TD>

<!-- Content -->
<html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/register.do" focus="userBean.username">
<TABLE cellSpacing="0" cellPadding=10 border="1">
<TR>
  <TD>
    <TABLE cellSpacing="1" cellPadding="1" align="center" border="0">
    <TR>
      <TD colspan="5"><b><bean:message key="message.all.fields.required"/></b><br></TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Email"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="userBean.username"
          size="20"
          maxlength="45"
          redisplay="false"
          tabindex="1"/>
      </TD>
      <TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
      <TD class="label"><bean:message key="Address"/></TD>
      <TD></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="userBean.username">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Password"/> <br><font size=1pt>(8-10 chars)</font></TD>
      <TD>
        <html:password
          name="registrationBean"
          property="userBean.password"
          size="20"
          maxlength="10"
          redisplay="false"
          tabindex="2"/>
      </TD>
      <TD></TD>
      <TD class="label"><bean:message key="Street"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.address.streetName1"
          size="20"
          maxlength="60"
          tabindex="10"/>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="userBean.password">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.address.streetName1">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="FirstName"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.firstName"
          size="20"
          maxlength="60"
          tabindex="3"/>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.address.streetName2"
          size="20"
          maxlength="60"
          tabindex="11"/>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.firstName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="MiddleName"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.middleName"
          size="20"
          maxlength="60"
          tabindex="4"/>
      </TD>
      <TD></TD>
      <TD><b><bean:message key="City"/></b></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.address.city"
          size="20"
          maxlength="60"
          tabindex="12"/>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.middleName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.address.city">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD><b><bean:message key="LastName"/></b></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.lastName"
          size="20"
          maxlength="60"
          tabindex="5"/>
      </TD>
      <TD></TD>
      <TD class="label"><bean:message key="State"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.address.state"
          size="2"
          maxlength="2"
          tabindex="13"/>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.lastName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.address.state">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Gender"/></TD>
      <TD>
        <html:select name="registrationBean" property="patientBean.gender" tabindex="6">
          <html:option value=""><bean:message key="ChooseGender"/></html:option>
          <html:option value="dashes">-------------</html:option>
          <html:option value="Male" key="Male"/>
          <html:option value="Female" key="Female"/>
        </html:select>
      </TD>
      <TD></TD>
      <TD class="label"><bean:message key="Zip"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.address.zipCode"
          size="20"
          maxlength="10"
          tabindex="14"/>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.gender">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.address.zipCode">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="DOB"/><br><font size=1pt>(mm/dd/yyyy)</font></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.dob"
          size="10"
          maxlength="10"
          tabindex="7"/>
      </TD>
      <TD></TD>
      <TD class="label"><bean:message key="Country"/></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.address.country"
          size="20"
          maxlength="60"
          tabindex="15"/>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.dob">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.address.country">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="SSN"/><br><font size=1pt>(xxxxxxxxx)</font></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.ssn"
          size="9"
          maxlength="9"
          tabindex="8"/>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.ssn">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Phone"/><br><font size=1pt>(xxx-xxx-xxxx)</font></TD>
      <TD>
        <html:text
          name="registrationBean"
          property="patientBean.phone"
          size="12"
          maxlength="15"
          tabindex="9"/>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="patientBean.phone">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD>
        <BR>
        <html:submit property="action" tabindex="16" styleClass="profileButton">
          <bean:message key="button.Register"/>
        </html:submit>
        <html:submit property="action" tabindex="17">
          <bean:message key="button.Cancel"/>
        </html:submit>
      </TD>
      <TD></TD>
      <TD></TD>
      <TD></TD>
    </TR>
    </TABLE>
  </TD>
</TR>
</TABLE>
</html:form>
  <!-- Content END -->
</TD></TR>
</TABLE>
</BODY>
</HTML>
</html:html>
