import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM students " +
                     "WHERE email = ? AND password = ?";

        PrintWriter out = response.getWriter();

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();

                session.setAttribute("studentId", rs.getInt("id"));
                session.setAttribute("studentName", rs.getString("name"));
                session.setAttribute("studentEmail", rs.getString("email"));

                response.sendRedirect("events.html");

            } else {

                out.println("<html>");
                out.println("<head><title>Login Failed</title></head>");
                out.println("<body>");
                out.println("<h2>Invalid Email or Password</h2>");
                out.println("<a href='login.html'>Try Again</a>");
                out.println("</body>");
                out.println("</html>");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            out.println("<h2>Database Error</h2>");
            out.println("<p>" + e.getMessage() + "</p>");

            e.printStackTrace();
        }
    }
}