import java.io.IOException;
import java.io.PrintWriter;

import entity.User;
import hibernate.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;  
import org.hibernate.Transaction;

public class RegisterUser extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        //RegisterUser?name=&institute=&email=&username=&password=&password=
        PrintWriter out = response.getWriter();
        // Connection con=Conn.getCon();
        // String query = "insert into users values(?,?,?,?,?)";

        String uname=request.getParameter("name");
        String uid= request.getParameter("username");
        String password= request.getParameter("password");
        String institute= request.getParameter("institute");
        String email = request.getParameter("email");
        if(uid!=null)
        {
            Transaction tx = null;
            //Get the session object.
            Session session = HibernateUtils.getSessionFactory().openSession();

            try {
                //Start hibernate session.
                tx = session.beginTransaction();
    
                //Insert a new student record in the database.
                User e1=new User();
                e1.setName(uname); 
                e1.setUserid(uid);
                e1.setPassword(password);
                e1.setInstitute(institute);
                e1.setEmail(email);

                session.save(e1);
    
                //Commit hibernate transaction if no exception occurs.
                tx.commit();
            } catch (HibernateException e) {
                if(tx!=null){
                    //Roll back if any exception occurs. 
                    tx.rollback();
                }
                e.printStackTrace(); 
            } finally {
                //Close hibernate session.
                session.close(); 
            }
            
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
