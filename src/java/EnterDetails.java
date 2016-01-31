import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EnterDetails extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        //EnterDetails?days=&class=&lectures=&lecturespersub=&starttime=10%3A00
        //&duration=&teachers=&teacher_name1=&subjects=&subject_teacher=X
        
        int days= Integer.parseInt(request.getParameter("days"));
        int classes= Integer.parseInt(request.getParameter("class"));
        int lectures= Integer.parseInt(request.getParameter("lectures"));
        int lecturesPerSub = Integer.parseInt(request.getParameter("lecturespersub"));
        int startingTime= Integer.parseInt(request.getParameter("starttime"));
        int duration= Integer.parseInt(request.getParameter("duration"));
        int noSubjects= Integer.parseInt(request.getParameter("subjects"));
        int noTeachers = Integer.parseInt(request.getParameter("teachers"));
        
        HttpSession session=request.getSession();
         
        //In login page, remember to write its setAttribute
        String uname = (String) session.getAttribute("userid");
            
            //Iska koi alternative??
        session.setAttribute("days",days);
        session.setAttribute("classes",classes);
        session.setAttribute("lectures",lectures);
        session.setAttribute("lecturesPerSub",lecturesPerSub);
        session.setAttribute("startingTime",startingTime);
        session.setAttribute("duration",duration);
            
        session.setAttribute("noSubjects",noSubjects);
        session.setAttribute("noTeachers",noTeachers);
            
        response.sendRedirect("enterstrings.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
