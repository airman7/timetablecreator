import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import Connection.Con;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterUser extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        //RegisterUser?name=&institute=&email=&username=&password=&password=
        PrintWriter out = response.getWriter();
        Connection con=Con.getCon();
        String query = "insert into users values(?,?,?,?,?)";

        String uname=request.getParameter("name");
        String uid= request.getParameter("username");
        String password= request.getParameter("password");
        String institute= request.getParameter("institute");
        String email = request.getParameter("email");
        if(uid!=null)
        {
        try 
        {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1,uname);
                    ps.setString(2,uid);
                    ps.setString(3,password);
                    ps.setString(4,institute);
                    ps.setString(5,email);
                    ps.executeUpdate();
                    con.close();
                    
                    out.println("<html>");
                        out.println("<head>");
                                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"><script type=\"text/javascript\" src=\"myscripts.js\"></script><title> Congratulations!!</title>");
                        out.println("</head>");
                        out.println("<body bgcolor=\"lightblue\" >");
                            out.println("<p style=\"font-family:calibri font-size:30\">");
                         out.println("<nav><img class=\"logo\" src=\"t9.png\" align=\"left\" size=\"20px\"><a href=\"index.jsp\" class=\"navbar\" >TIMETABLE</a></nav>	");
                            out.println("<br><br><br>");
                            out.println("<center><h1>Congratulations!!!<br><center><br><br>");
                            out.println("You have successfully registered yourself.<br>Now get started!!</h1><br><br>");
                            
                                     HttpSession ses=request.getSession();
                    ses.setAttribute("user",uname);
                    ses.setAttribute("userid",uid);
                    ses.setAttribute("institute",institute);
                    out.println("<a href=\"enterdetails.jsp\" class=\"button\">CONTINUE</a></pre>");
                   
                        out.println("</body>");
                    
                    //response.sendRedirect("index.html");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getServletInfo() {
        return "Short description";
    }
}
