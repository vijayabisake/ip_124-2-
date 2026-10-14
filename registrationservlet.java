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

@WebServlet("/MyRegistrationsServlet")
public class MyRegistrationsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        PrintWriter out = response.getWriter();

        if (session == null ||
            session.getAttribute("studentId") == null) {

            out.println("<h2>Please login first.</h2>");
            out.println("<a href='login.html'>Login</a>");
            return;
        }

        int studentId =
                (Integer) session.getAttribute("studentId");

        String studentName =
                (String) session.getAttribute("studentName");

        String sql =
                "SELECT r.id, e.event_name, e.event_date, " +
                "e.venue, e.description, r.registration_date " +
                "FROM registrations r " +
                "JOIN events e ON r.event_id = e.id " +
                "WHERE r.student_id = ?";

        out.println("<html>");
        out.println("<head>");
        out.println("<title>My Registered Events</title>");

        out.println("<style>");

        out.println("body {");
        out.println("font-family: Arial;");
        out.println("background-color: #f2f2f2;");
        out.println("margin: 0;");
        out.println("}");

        out.println(".header {");
        out.println("background-color: #222;");
        out.println("color: white;");
        out.println("text-align: center;");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".container {");
        out.println("width: 85%;");
        out.println("margin: 30px auto;");
        out.println("}");

        out.println(".event {");
        out.println("background-color: white;");
        out.println("padding: 20px;");
        out.println("margin-bottom: 20px;");
        out.println("border-radius: 8px;");
        out.println("box-shadow: 0 2px 6px #aaa;");
        out.println("}");

        out.println("button {");
        out.println("background-color: #c62828;");
        out.println("color: white;");
        out.println("border: none;");
        out.println("padding: 10px 20px;");
        out.println("border-radius: 5px;");
        out.println("}");

        out.println("a {");
        out.println("display: inline-block;");
        out.println("margin: 10px;");
        out.println("color: #222;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='header'>");
        out.println("<h1>My Registered Events</h1>");
        out.println("<p>Welcome, " + studentName + "</p>");
        out.println("</div>");

        out.println("<div class='container'>");

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                out.println("<div class='event'>");

                out.println("<h2>"
                        + rs.getString("event_name")
                        + "</h2>");

                out.println("<p><b>Date:</b> "
                        + rs.getDate("event_date")
                        + "</p>");

                out.println("<p><b>Venue:</b> "
                        + rs.getString("venue")
                        + "</p>");

                out.println("<p><b>Description:</b> "
                        + rs.getString("description")
                        + "</p>");

                out.println("<p><b>Registered On:</b> "
                        + rs.getTimestamp("registration_date")
                        + "</p>");

                out.println("<form action='EventServlet' method='post'>");

                out.println("<input type='hidden' " +
                        "name='action' value='cancel'>");

                out.println("<input type='hidden' " +
                        "name='registrationId' value='"
                        + rs.getInt("id") + "'>");

                out.println("<button type='submit'>");
                out.println("Cancel Registration");
                out.println("</button>");

                out.println("</form>");

                out.println("</div>");
            }

            if (!found) {

                out.println("<div class='event'>");

                out.println("<h2>No Registered Events</h2>");

                out.println("<p>You have not registered for any events yet.</p>");

                out.println("</div>");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            out.println("<h2>Error</h2>");
            out.println("<p>" + e.getMessage() + "</p>");

            e.printStackTrace();
        }

        out.println("<a href='events.html'>View Events</a>");

        out.println("<a href='index.html'>Home</a>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}