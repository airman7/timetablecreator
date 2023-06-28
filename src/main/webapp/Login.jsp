<%-- 
    Document   : Login
    Created on : Sep 11, 2015, 1:50:16 AM
    Author     : mayank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><title>Login</title></head>
	<body bgcolor="lightblue">
                <nav>
			<img class="logo" src="t9.png" align="left" size="20px">
			<a href="index.jsp" class="navbar" >TIMETABLE</a>
			</nav>	
			
			
		<section class="one">
		<center><h1>LOGIN!</h1></center><br>
                <pre><center><form method="get" action="CheckUser"><fieldset background="log.xcf">
Username	<input required type='text' name='username'maxlength="50" size="30" placeholder="Username"><br>
	
Password	<input required type='password' name='password' maxlength="50" size="30" placeholder="Password"> <br>

         <input type='submit'  class="button" value='LOGIN'>		<input class="button" type="reset" value="Reset!"><br>

</center></pre></fieldset>
</section></form>
</body>
</html>

