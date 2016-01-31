import Connection.Con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TimeTable extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con=Con.getCon();
        String query;
        PreparedStatement pres;
        HttpSession session=request.getSession();
        
        //TimeTable?class=1
        int showClass=Integer.parseInt(request.getParameter("class"));

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
        
        int i,j,k;
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
        
        Algo a=new Algo(days,classes,lectures,lecturesPerSub,startingTime,duration,noSubjects,noTeachers,teachers,subjects);
        
        out.println("<html>");
        out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"><script type=\"text/javascript\"></script></head>");
        out.println("<body>");
        i=showClass-1;
        int d;
        //int tempTime=startingTime;
        out.println("<h1>Timetable for class "+showClass+"</a>");
        out.println("<center><pre><table class=\"table1\" ><tr class=\"headrow\"><td>Time</td>");
        for(d=1;d<=days;d++)
            out.print("<td>Day "+d+"</td>");
        out.print("</tr>");

        int x;
        for(k=0;k<lectures;k++)
        {
            out.println("<tr>");
            for(j=-1;j<days;j++)
            {
                out.println("<td class=\"td1\">");
                if(j==-1)
                {
                    out.print(startingTime);
                    startingTime=startingTime+duration;
                    if(startingTime>12)
                        startingTime=startingTime-12;
                }
                else   
                    out.print("\t"+a.table[i][j][k]); 
                out.println("</td>");
            }
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td class=\"td2\">");
            for(j=0;j<days;j++)
            {
                out.println("<td class=\"td2\">");

                out.print("\t");
                for(x=0;x<noSubjects;x++)
                {   
                    if(a.subs[i][0][x].equals(a.table[i][j][k]))
                    {
                        out.print("\t"+a.subs[i][1][x]);
                        break;
                    }       
                }
                out.println("</td>");
            }
            out.println("</tr>");
        }
        //startingTime=tempTime;
        out.println("</table>");
        out.println("<button class=\"button\" type=\"button\" onclick=\"JavaScript:window.print()\">Print</button>");
        out.println("</pre></center>");
        out.println("<body>");
        out.println("<html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}