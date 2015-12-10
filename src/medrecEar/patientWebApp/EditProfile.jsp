<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.bea.medrec.beans.AddressBean,
                 com.bea.medrec.utils.MedRecWebAppUtils"%>

<jsp:useBean id="patientBean" class="com.bea.medrec.beans.PatientBean" scope="session"/>
<%
  AddressBean theAddressBean = patientBean.getAddress();
%>

<html:html locale="true">
<HEAD>
  <html:base/>
  <TITLE><bean:message key="title.MedRec"/></TITLE>
  <link rel="stylesheet" type="text/css" HREF="stylesheet.css">
</HEAD>
<BODY bgcolor="white" TOPMARGIN="0" LEFTMARGIN="0" RIGHTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">

<!-- START Header -->
<jsp:include page="Header.jsp" flush="true"/>
<!-- END Header -->

<!-- START Padding for Content -->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="10">
<TR>
  <TD>

<!-- Content -->
<a href="medicalrecord.do"><span class="pagetitle-patient"><bean:message key="link.home"/></span></a> &gt;
<span class="pagetitle-patient"><bean:message key="page.title.patient.profile.edit"/></span>
<br><br>

<html:form method="<%=MedRecWebAppUtils.getHttpMethod()%>" action="/editprofile.do" focus="firstName">
  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="3">
    <TR>
      <TD class="label"><bean:message key="FirstName"/></TD>
      <TD><html:text
            name="patientBean"
            property="firstName"
            size="20"
            maxlength="60"
            value="<%=patientBean.getFirstName()%>"
            tabindex="1"
          />
      </TD>
      <TD class="label"><bean:message key="Address"/>:</TD>
      <TD></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="firstName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="LastName"/></TD>
      <TD><html:text
            name="patientBean"
            property="lastName"
            size="20"
            maxlength="60"
            value="<%=patientBean.getLastName()%>"
            tabindex="2"
          />
      </TD>
      <TD class="label"><bean:message key="Street"/></TD>
      <TD><html:text
            name="patientBean"
            property="address.streetName1"
            size="20"
            maxlength="60"
            value="<%=patientBean.getAddress().getStreetName1()%>"
            tabindex="9"
          />
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="lastName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="address.streetName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="MiddleName"/></TD>
      <TD><html:text
            name="patientBean"
            property="middleName"
            size="20"
            maxlength="60"
            value="<%=patientBean.getMiddleName()%>"
            tabindex="3"
          />
      </TD>
      <TD class="label"></TD>
      <TD><html:text
            name="patientBean"
            property="address.streetName2"
            size="20"
            maxlength="60"
            value="<%=theAddressBean.getStreetName2()%>"
            tabindex="10"
          />
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="middleName">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Gender"/></TD>
      <TD><html:select property="gender"
            value="<%=patientBean.getGender()%>"
            tabindex="4">
            <html:option value="1"><bean:message key="ChooseGender"/></html:option>
            <html:option value="2">-------------</html:option>
            <html:option value="Male"><bean:message key="Male"/></html:option>
            <html:option value="Female"><bean:message key="Female"/></html:option>
          </html:select>
      </TD>
      <TD class="label"><bean:message key="City"/></TD>
      <TD><html:text
            name="patientBean"
            property="address.city"
            size="20"
            maxlength="60"
            value="<%=theAddressBean.getCity()%>"
            tabindex="11"
          />
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="gender">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="address.city">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="DOB"/><br><font size=1pt>(mm/dd/yyyy)</font></TD>
      <TD><html:text
            name="patientBean"
            property="dob"
            size="10"
            maxlength="10"
            value="<%=patientBean.getDob()%>"
            tabindex="5"
          />
      </TD>
      <TD class="label"><bean:message key="State"/></TD>
      <TD><html:text
            name="patientBean"
            property="address.state"
            size="20"
            maxlength="40"
            value="<%=theAddressBean.getState()%>"
            tabindex="12"
          />
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="dob">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="address.state">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="SSN"/><br><font size=1pt>(xxxxxxxxx)</font></TD>
      <TD><html:text
            name="patientBean"
            property="ssn"
            size="9"
            maxlength="9"
            value="<%=patientBean.getSsn()%>"
            tabindex="6"
          />
      </TD>
      <TD class="label"><bean:message key="Zip"/></TD>
      <TD><html:text
            name="patientBean"
            property="address.zipCode"
            size="20"
            maxlength="10"
            value="<%=theAddressBean.getZipCode()%>"
            tabindex="13"
          />
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="ssn">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="address.zipCode">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Phone"/><br><font size=1pt>(xxx-xxx-xxxx)</font></TD>
      <TD><html:text
            name="patientBean"
            property="phone"
            size="15"
            maxlength="15"
            value="<%=patientBean.getPhone()%>"
            tabindex="7"
          />
      </TD>
      <TD class="label"><bean:message key="Country"/></TD>
      <TD><html:text
            name="patientBean"
            property="address.country"
            size="20"
            maxlength="40"
            value="<%=theAddressBean.getCountry()%>"
            tabindex="14"
          />
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="phone">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="address.country">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD class="label"><bean:message key="Email"/></TD>
      <TD><html:text
              name="patientBean"
              property="email"
              size="20"
              maxlength="40"
              value="<%=patientBean.getEmail()%>"
              readonly="true"
              styleClass="row1"
              tabindex="8"

           />
        </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD align="left">
        <html:messages id="error" property="email">
          <bean:write name="error" filter="false"/>
        </html:messages>
      </TD>
    </TR>
    <TR>
      <TD></TD>
      <TD>
        <br>
        <html:submit property="action">
          <bean:message key="button.Save"/>
        </html:submit>
        <html:submit property="action">
          <bean:message key="button.Cancel"/>
        </html:submit>
      </TD>
    </TR>
  </TABLE>
</html:form>

<!-- Content END -->
<!-- End Padding for Content -->
  </tr>
</table>
</BODY>
</html:html>
