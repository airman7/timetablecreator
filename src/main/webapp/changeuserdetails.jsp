<%-- 
    Document   : changeuserdetails
    Created on : Oct 3, 2015, 4:25:37 PM
    Author     : mayank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="myscripts.js"></script><title>Registr<link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="myscripts.js"></script><title>ation</title></head>
	<body bgcolor="lightblue">
<nav>
			<img class="logo" src="t9.png" align="left" size="20px">

			<a href="index.jsp" class="navbar" >TIMETABLE</a>
			
                    		
                </nav>	
		<section   class="one"><br>
		<h1>Fill this form to change your password</h1>
                <center><pre><form  onsubmit="return checkForm(this);" method="get" action="ChangePassword"><fieldset class"one-edge-shadow">
Old Password            <input  required type='password' name='pass' maxlength="50" size="30"><br>
New Password		<input  required type='password' name='password' maxlength="50" size="30" placeholder="min. 6 characters"><br>
Confirm Password	<input  required type='password' name='password2' maxlength="50" size="30" placeholder="re-enter the password">
                    <input value="Submit" type="submit" class="button">		<input class="button" value="Reset!" type="reset">
</center></pre>
</fieldset></form></section>
</body>
</html>
