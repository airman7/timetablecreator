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

public class ChangePassword extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession ses=request.getSession();
        String user=(String)ses.getAttribute("user");
                        
        //ChangePassword?pass=123456&password=qwerty&password2=qwerty
        
        Connection con=Conn.getCon();
        String query = "update users set password=? where password=? and name=?";

        String oldpassword= request.getParameter("pass");
        String password= request.getParameter("password");
        try 
        {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,password);
            ps.setString(2,oldpassword);
            ps.setString(3,user);
            ps.executeUpdate();
            con.close();
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"><script type=\"text/javascript\" src=\"myscripts.js\"></script><title> Congratulations!!</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"lightblue\" >");
            out.println("<p style=\"font-family:calibri font-size:30\">");
            out.println("<nav><img class=\"logo\" src=\"t9.png\" align=\"left\" size=\"20px\"><a href=\"index.jsp\" class=\"navbar\" >TIMETABLE</a></nav>	");
            out.println("<br><br><br>");
            out.println("<center><h1>Congratulations!!!<br><center><br><br>");
            out.println("You have successfully changed your password!!</h1><br><br>");

            out.println("<a href=\"userchoices.jsp\" class=\"button\">CONTINUE</a></pre>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(SQLException e)
        {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, e);
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
