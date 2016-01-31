import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FullDetails extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        //FullDetails?days=5&class=5&lectures=5&lecturespersub=5&
        //starttime=5&duration=1&teachers=4&subjects=4&teacher0=A&
        //teacher1=B&teacher2=C&teacher3=D     
        
        HttpSession session=request.getSession();
        int noTeachers = (int) session.getAttribute("noTeachers");
        int i;
        String teacherName[]= new String[noTeachers];
        for(i=0;i<noTeachers;i++)
        {
            teacherName[i] = request.getParameter("teacher"+i);        
        }  
        session.setAttribute("teachers",teacherName);
        response.sendRedirect("entersub.jsp");
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
