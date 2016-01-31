<%-- 
    Document   : userchoices
    Created on : Sep 16, 2015, 12:57:56 AM
    Author     : mayank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String user= (String) session.getAttribute("user");
String institute= (String) session.getAttribute("institute");
%>
<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><title>Homepage</title></head>
	<body bgcolor="lightblue" >
		<p style="font-family:calibri font-size:30">
                <nav>
			<img class="logo" src="t9.png" align="left" size="20px">
			<a href="index.jsp" class="navbar" >TIMETABLE</a>
                        <a href="Logout" class="navbar" class="button" class="right">Logout</a></li>
                </nav>
		<br><br><br> 	
		
 		<section class="one"><center>
        		<span  style="font-size: 60px"></span>
                        <h1>Hello <%=user%> from <%=institute%></h1>
                        <br>
                        <h2 align="center">What would you want to do?</h2>
                        <pre>
<a href="enterdetails.jsp" class="button">Change your Details</a>       <a href="showdetails.jsp" class="button">View Your Details</a>
<a href="viewtimetable.jsp" class="button">View your TimeTable</a>       <a href="changeuserdetails.jsp" class="button">Change Password</a>
              </pre><br><br><hr><br> <br>
   		</section>
</body>
</html>