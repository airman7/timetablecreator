<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Connection.Con"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
        Connection con=Con.getCon();
        String query;
        PreparedStatement pres;
        query="select numberOfClasses from user_input where userid=?"; 
        int classes=0;
        int i;
        try 
        {
            pres = con.prepareStatement(query);
            pres.setString(1,(String) session.getAttribute("userid"));
            ResultSet res = pres.executeQuery();
            boolean b=res.next();
            if(b)
            {
                    classes= res.getInt("numberOfClasses");
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
<html>
    <head><link rel="stylesheet" type="text/css" href="mystyle.css">
        <script type="text/javascript" src="myscripts.js"></script>
        <title>Timetable</title>
    </head>	
    <body bgcolor="lightblue" >
        <p style="font-family:calibri font-size:30">

        <nav> 
            <img class="logo" src="t9.png" align="left" size="20px">
            <a href="index.jsp" class="navbar" >TIMETABLE</a>
        </nav>	
        <center>
            <br><br><br> <br>
            <h1 align="center">TimeTable</h1>
            <p align="center">Following time table has been generated:</p>
<%
for(i=0;i<classes;i++)
{
%>
<a href="TimeTable?class=<%=i+1%>" class="button">Timetable for class <%=(i+1)%></a>
<%
}
%>
            <br><br><br>
            <pre>
                Want to Change the Details You have entered?	<a href="enterdetails.jsp"><button>CHANGE</button></a>
            </pre>
            <pre>
                Go to user home:	<a href="userchoices.jsp"><button>Home</button></a>
            </pre>
        </center>
    <body>
<html>