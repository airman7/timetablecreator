<%-- 
    Document   : enterstrings
    Created on : Sep 14, 2015, 1:19:10 AM
    Author     : mayank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    int days= (int) session.getAttribute("days");
        int classes= (int) session.getAttribute("classes");
        int lectures= (int) session.getAttribute("lectures");
        int lecturesPerSub = (int) session.getAttribute("lecturesPerSub");
        int startingTime= (int) session.getAttribute("startingTime");
        int duration= (int) session.getAttribute("duration");
        int noSubjects= (int) session.getAttribute("noSubjects");
        int noTeachers = (int) session.getAttribute("noTeachers");
    %>


<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="myscripts.js"></script><title>Entry</title></head>

	<body bgcolor="lightblue" ><nav>
			<img class="logo" src="t9.png" align="left" size="20px">

			<a href="index.jsp" class="navbar" >TIMETABLE</a>
			
                    		
                </nav><section class="one">
	<center><h1> Enter the details and submit to generate the timetable you want!!! </h1><br>
            <pre><center><form method="get" action="FullDetails"><fieldset>
Number of Days in a week		<input readonly  value=<%=days%>>

Number of classes			<input readonly  value=<%= classes %>>

Number of lectures in a day 		<input readonly value=<%= lectures %>>

Number of lectures per subject in a week<input readonly value=<%= lecturesPerSub %>>

Starting Time 				<input readonly value=<%= startingTime %> >

Duration of each lecture 		<input readonly value=<%= duration %>>

Number of teachers 			<input readonly value=<%= noTeachers %>>

Number of Subjects per class		<input readonly value=<%= noSubjects %>>
				
Teacher name                        <%  int i;  for(i=0;i<noTeachers;i++){%>
<input required type="text" name="teacher<%= i %>" placeholder="Enter the name"><br>
<%
    }
%>				<br><br>
<input type='submit' class="button" value='SUBMIT'>	<input class="button" type="reset" value="Reset!"><br>
</center></pre></fieldset>
</form></section>
</body>
</html>