<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="myscripts.js"></script><title>Entry</title></head>
    <body bgcolor="lightblue">
<%
int classes= (int) session.getAttribute("classes");
int noSubjects= (int) session.getAttribute("noSubjects");
int noTeachers = (int) session.getAttribute("noTeachers");
int i;
String teacher[]=(String[]) session.getAttribute("teachers");
%>
        <nav>
                <img class="logo" src="t9.png" align="left" size="20px">

                <a href="index.jsp" class="navbar" >TIMETABLE</a>
       </nav>
        <section class="one">
            <center>
                <h1>Enter the details of subjects</h1>
            </center>
            <pre><center>
                <form method="get" action="Details">
                    <fieldset>
<%
    int k,j;
    for(k=0;k<classes;k++)
    { 
%>
    <h4>Subjects for class <%=(k+1)%></h4>                        
<%
        for(j=0;j<noSubjects;j++)
        {
%>
<input type="text" maxlength="50" size="30" name="subject<%=(k+1)%><%=(j+1)%>" placeholder=<%="Subject" +(j+1)%>  > <select name="subject_teacher<%=(k+1)%><%=(j+1)%>">
<%
            for(i=0;i<noTeachers;i++)
            {
%>
    <option value=<%=teacher[i]%>> <%=teacher[i]%> </option>
<%          
            }
%>
</select><br>
<%
        }
    }
%>
<input class="button" type='submit' value='SUBMIT'><input class="button" type="reset" value="Reset!"><br>   
                    </fieldset>
                </form>
            </center></pre>
        </section>
    </body>
</html>