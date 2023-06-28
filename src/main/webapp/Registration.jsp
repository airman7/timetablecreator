<%-- 
    Document   : Registration
    Created on : Sep 11, 2015, 1:51:22 AM
    Author     : mayank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="myscripts.js"></script><title>Registration</title></head>
	<body bgcolor="lightblue">
<nav>
			<img class="logo" src="t9.png" align="left" size="20px">

			<a href="index.jsp" class="navbar" >TIMETABLE</a>
			
                    		
                </nav>	
		<section   class="one"><br>
		<h1>Provide the following Details and quickly signup for free!!</h1>
                <center><pre><form  onsubmit="return checkForm(this);" method="get" action="RegisterUser"><fieldset class"one-edge-shadow">
Name			<input  required type='text' name='name'maxlength="50" size="30" placeholder="enter your name"><br>
Institute Name		<input  required type='text' name='institute' maxlength="50" size="30" placeholder="enter your Imstitute's name"> <br>
Email Address		<input  required type="email" name='email' maxlength="50" size="30"  required placeholder="username@example.com"><br>
Username		<input type='text' name='username' maxlength="50" size="30" placeholder="chose a username"> <br>
Password		<input  required type='password' name='password' maxlength="50" size="30" placeholder="min. 6 characters"><br>
Confirm Password	<input  required type='password' name='password2' maxlength="50" size="30" placeholder="re-enter the password">
                    <input value="Submit" type="submit" class="button">		<input class="button" value="Reset!" type="reset">

</center></pre>
</fieldset></form></section>
</body>
</html>

