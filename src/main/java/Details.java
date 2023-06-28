import Connection.Conn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Details extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con=Conn.getCon();
        String query = "insert into user_input values(?,?,?,?,?,?,?,?,?,?,?)";
        HttpSession session=request.getSession();
        int i,j,k;
        
        String uname = (String) session.getAttribute("userid");
        
        int ses_days= (int) session.getAttribute("days");
        int ses_classes= (int) session.getAttribute("classes");
        int ses_lectures= (int) session.getAttribute("lectures");
        int ses_lecturesPerSub = (int) session.getAttribute("lecturesPerSub");
        int ses_startingTime= (int) session.getAttribute("startingTime");
        int ses_duration= (int) session.getAttribute("duration");
        int ses_noSubjects= (int) session.getAttribute("noSubjects");
        int ses_noTeachers = (int) session.getAttribute("noTeachers");
        String ses_teacher[]=(String[]) session.getAttribute("teachers");
        
        
        //ab teen files hogayi sirf input ke liye
        //and database se userid b check karwa ke input karwana hai
        //kyui wo primary key hai..to usko kaha kaha call karna
        //and kese use karna ye yaad rakhna
        
        //Details?days=5&class=3&lectures=5&lecturespersub=6&starttime=10&duration=1&
        //teachers=4&subjects=4&teacher0=A&teacher1=B&teacher2=C&teacher3=D&subject11=&
        //subject_teacher11=A&subject12=&subject_teacher12=A&subject13=&subject_teacher13=A&
        //subject14=&subject_teacher14=A&subject21=&subject_teacher21=A&subject22=&
        //subject_teacher22=A&subject23=&subject_teacher23=A&subject24=&subject_teacher24=A&
        //subject31=&subject_teacher31=A&subject32=&subject_teacher32=A&subject33=&
        //subject_teacher33=A&subject34=&subject_teacher34=A
       
        //Details?subject_teacher11=s&subject_teacher12=k&subject_teacher13=t
        //&subject_teacher21=s&subject_teacher22=k&subject_teacher23=t&subject_teacher31=s
        //&subject_teacher32=k&subject_teacher33=t
        
        String teacher=new String();
        String subject=new String();
        for(i=0;i<ses_noTeachers;i++)
        {    
            teacher=teacher.concat(ses_teacher[i]);
            teacher=teacher.concat(",");
        }
        //out.print(teacher);

        for(k=0;k<ses_classes;k++)
        {
            for(j=0;j<ses_noSubjects;j++)
                {
                    subject=subject.concat((String) request.getParameter("subject"+(k+1)+(j+1)));
                    subject=subject.concat(".");
                    subject=subject.concat((String) request.getParameter("subject_teacher"+(k+1)+(j+1)));
                    subject=subject.concat(",");
                    // . separates subject from its corresponding teacher
                    // , separates 2 subjects of each class
                    // : seaparates 2 different classes
                }
            subject=subject.concat(":");
        }
        //out.print(subject);
        if(ses_noSubjects==ses_noSubjects && ses_noTeachers==ses_noTeachers && uname!=null)
        {
            PreparedStatement ps;
            try 
            {
                ps = con.prepareStatement(query);
                ps.setString(1,uname);
                ps.setInt(2,ses_days);
                ps.setInt(3,ses_classes);
                ps.setInt(4,ses_noSubjects);
                ps.setInt(5,ses_lectures);
                ps.setInt(6,ses_noTeachers);
                ps.setInt(7,ses_lecturesPerSub);
                ps.setInt(8,ses_startingTime);
                ps.setInt(9,ses_duration);
                ps.setString(10,teacher);
                ps.setString(11,subject);
                ps.executeUpdate();
                con.close();

                response.sendRedirect("userchoices.jsp");
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(FullDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    public String getServletInfo() 
    {
        return "Short description";
    }
}
