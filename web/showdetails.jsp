<%-- 
    Document   : showdetails
    Created on : Sep 16, 2015, 12:59:58 AM
    Author     : mayank
--%>

<%@page import="Connection.Con"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Connection con=Con.getCon();
        String query;
        PreparedStatement pres;
        query="select * from user_input where userid=?"; 
        int days=0;
        int classes=0;
        int lectures=0;
        int lecturesPerSub=0;
        int startingTime=0;
        int duration=0;
        int noSubjects=0;
        int noTeachers=0;
        String teachers=null;
        String subjects=null;
        
        int i;
        try 
        {
            pres = con.prepareStatement(query);
            pres.setString(1,(String) session.getAttribute("userid"));
            ResultSet res = pres.executeQuery();
            boolean b=res.next();
            if(b)
            {
                    days= res.getInt("days");
                    classes= res.getInt("numberOfClasses");
                    lectures= res.getInt("lecturesInDay");
                    lecturesPerSub = res.getInt("numberOfLecturesPerSubject");
                    startingTime= res.getInt("startingTime");
                    duration= res.getInt("duration");
                    noSubjects= res.getInt("numberOfSubjects");
                    noTeachers = res.getInt("numberOfTeachers");
                    teachers=res.getString("teacherNames");
                    subjects=res.getString("subjectsWithTeachers");
                    
                    teachers=teachers.substring(0,teachers.length()-1);
            }
            else 
            {
                response.sendRedirect("Login.jsp");
            }
           con.close();
        } 
        catch(Exception ex)
        {
            out.println(ex);
        }
    
    
%>
<!DOCTYPE html>
<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="myscripts.js"></script><title>View Details</title></head>
	<body bgcolor="lightblue" >
		<p style="font-family:calibri font-size:30">
		<%--
                <nav>
			<img src="images/logo" align="left">
			<a href="home.html"><h4 style="font-family:comic sans" align="left">TimeTable</h4></a>
			
                    		
		</nav>
                --%>

                <center>
                    <h1> Please view the details that You have entered. </h1><br>

                    <table>
                        <tr><td>Number of Days in a week      <td>      <%= days %>
                        <tr><td>Number of classes  <td>                 <%= classes %>
                        <tr><td>Number of lectures in a day  <td>       <%= lectures %>
                        <tr><td>Number of lectures per subject  <td>    <%= lecturesPerSub %>
                        <tr><td>Number of teachers<td>                  <%= noTeachers %>
                        <tr><td>Starting Time   <td>                    <%= startingTime %>
                        <tr><td>Duration of each lecture    <td>        <%= duration %>
                        <tr><td>Number of subjects <td>                 <%= noSubjects %>
                        <tr><td>Teachers<td>                            <%= teachers %>
                        <br></table>
                    <a href="enterdetails.jsp" class="button">Change</a>
                </center>
        </body>
</html>