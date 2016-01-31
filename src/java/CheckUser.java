import Connection.Con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUser extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        Connection con=Con.getCon();
        String query;
        PreparedStatement pres;
        query="select * from users where userid=? and password=?"; 
        
        //CheckUser?username=&password=
        
        String uid= request.getParameter("username");
        String password= request.getParameter("password");
        try 
        {
            pres = con.prepareStatement(query);
            pres.setString(1,uid);
            pres.setString(2,password);
            ResultSet res = pres.executeQuery();
            boolean b=res.next();
            if(b)
            {
                    String name=res.getString("name");
                    String institute = res.getString("institute");
                    //storing the username in session object
                    HttpSession ses=request.getSession();
                    ses.setAttribute("user",name);
                    ses.setAttribute("userid",uid);
                    ses.setAttribute("institute",institute);
                    response.sendRedirect("userchoices.jsp");
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
