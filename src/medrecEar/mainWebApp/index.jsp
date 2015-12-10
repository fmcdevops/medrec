<%@ page session="false" %>

<%--
 % $Id: index.jsp,v 1.10 2001/10/27 04:25:44 ro89390 Exp $
 % Copyright 2004 BEA Systems, Inc. All rights reserved.
--%>

<html>
<head>
<title>Avitek Medical Records Application</title>
</head>
<body>

<table width="100%" cellpadding="5" cellspacing="0" border="0">
<tr>
<td bgcolor="red">
<font color="#FFFFFF"><b>Avitek[tm] Medical Records</b></font>
</td>
<td bgcolor="red">
<div align="right"><a
href="http://edocs.bea.com/wls/docs81/medrec_tutorials/index.html"><font
color="#FFFFFF"><b>Medical Records Tutorials</b></font></a></div>
</td>
</tr>
</table>

<h1>Avitek&#153; Medical Records</h1>

<p><a href="start.jsp" target="_blank"><b>Start using MedRec!</a></b></p>

<p>Avitek Medical Records (or MedRec) is a WebLogic Server sample application suite that concisely demonstrates all aspects of the J2EE platform. MedRec is designed as an educational tool for all levels of J2EE developers.  It showcases the use of each J2EE component, and illustrates best practice design patterns for component interaction and client development.  MedRec also illustrates best practices for developing and deploying applications with WebLogic Server.
</p>

<p>
MedRec consists of four separate J2EE applications that correspond to each user type:
</p>

<ul>
<li>
Patient&nbsp;—&nbsp;The Patient application allows Patients to log in, edit their profile information, or request that their profile be added to the system. Patients can also view prior medical records of visits with their physician.
</li>

<li>
Controller&nbsp;—&nbsp;The controller application provides all of the controller and business logic used by the MedRec application suite.
</li>

<li>
Admin&nbsp;—&nbsp;The admin application allows an administrator to approve or deny new patient profile requests.
</li>

<li>
Physician&nbsp;—&nbsp;The Physician application allows physicians and nurses to log in, search and access patient profiles, create and review patient medical records, and prescribe medicine to patients.
</li>
</ul>

<p>
To begin using MedRec, access the <a href="start.jsp" target="_blank"><b>start page</a></b>.  From there, you can begin by registering as a new patient, or you can login using one of the previously-defined roles listed below.
</p>

<table border="1" cellspacing="0" cellpadding="0"
 <tr>
  <td width="103" valign="top" bgcolor=gray><b>&nbsp;Role</b></td>
  <td width="168" valign="top" bgcolor=gray><b>&nbsp;Username</b></td>
  <td width="96" valign="top" bgcolor=gray><b>&nbsp;Password</b></td>
 </tr>
 <tr>
  <td width="103" valign="top">&nbsp;Admin</td>
  <td width="168" valign="top">&nbsp;admin@avitek.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
 <tr>
  <td width="103" valign="top">&nbsp;Patient</td>
  <td width="168" valign="top">&nbsp;charlie@heisman.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
 <tr>
  <td width="103" valign="top">&nbsp;&nbsp;</td>
  <td width="168" valign="top">&nbsp;fred@pga.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
 <tr>
  <td width="103" valign="top">&nbsp;</td>
  <td width="168" valign="top">&nbsp;larry@celtics.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
 <tr>
  <td width="103" valign="top">&nbsp;</td>
  <td width="168" valign="top">&nbsp;page@vidablue.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
 <tr>
  <td width="103" valign="top">&nbsp;</td>
  <td width="168" valign="top">&nbsp;volley@ball.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
  <tr>
  <td width="103" valign="top">&nbsp;Physician</td>
  <td width="168" valign="top">&nbsp;mary@md.com</td>
  <td width="96" valign="top">&nbsp;weblogic</td>
 </tr>
</table>
<br>
<b>Heterogenous Clients</b>
<p>Several clients are provided to demonstrate Web Services interoperability features.  The Physician application, a Web Application client, communicates with Medical Records completely via Web Services.  In addition, two standalone clients are provided: a Java Swing client and .NET C# client.<p>
<p>To run the Java Swing client, follow these steps:</p>
<ol>
  <li>Open a CMD shell.</li>
  <li>Set up your MedRec environment with the <font face="Courier New" size="-1">setMedRecEnv.cmd</font> script, located in <font face="Courier New" size="-1">SAMPLES_HOME\domains\medrec</font>.</li>
  <li>Change to the <font face="Courier New" size="-1">SAMPLES_HOME\server\medrec\src\clients</font> directory.</li>
  <li>Run the client by typing "<font face="Courier New" size="-1"><b>ant run</b></font>".</li>
</ol>
<p>To run the .NET C# client, follow these steps:</p>
<ol>
  <li>Make sure you have the Microsoft .NET Framework Redistributable.  If not, download it from <a href="http://msdn.microsoft.com/netframework/downloads/howtoget.asp">http://msdn.microsoft.com/netframework/downloads/howtoget.asp</a>.</li>
  <li>In Windows Explorer, browse to the <font face="Courier New" size="-1">SAMPLES_HOME\server\medrec\src\clients\CSharpClient\bin\Release</font> directory.</li>
  <li>Run the client by double-clicking on <font face="Courier New" size="-1"><b>CSharpClient.exe</b></font>.</li>
</ol>

<p>To use either of the standalone clients, enter a valid Social Security Number (SSN) in the Patient ID search field and click submit to retrieve the Patient's profile.</p>

<b>Database</b>
<p>Data for the MedRec applications is stored in a PointBase database installed with WebLogic Server.  You can view the PointBase data directly by running the <font face="Courier New" size="-1">startPointBaseConsole.cmd</font> or <font face="Courier New" size="-1">startPointBaseConsole.sh</font> scripts located in the <font face="Courier New" size="-1">WL_HOME/common/eval/pointbase/tools</font> subdirectory of the WebLogic Server installation directory.  The login for the demo database is medrec/medrec.
</p>

<p>
For more information about MedRec, see the WebLogic Server <b><a
href="http://edocs.bea.com/wls/docs81/medrec_tutorials/index.html">Tutorials</a></b> page.
</p>

<p>
For more information about the MedRec application itself, see the <b>
<b><a href="javadoc/index.html" target="_blank">MedRec Javadocs</a></b>.
</p>

<p><hr noshade="noshade" />Copyright &copy; 2004 BEA Systems,
Inc. All Rights Reserved.</p>

</body>
</html>

