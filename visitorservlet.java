import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


@WebServlet("/VisitorServlet")

public class VisitorServlet extends HttpServlet {


static int count = 0;


protected void doGet(HttpServletRequest request,

HttpServletResponse response)

throws ServletException, IOException {


response.setContentType("text/html");


PrintWriter out = response.getWriter();


HttpSession session = request.getSession();

// Get visitor count from session

Integer sessionCount = (Integer) session.getAttribute("count");

// First visit

if (sessionCount == null || sessionCount == 0) {

count++;

sessionCount = count;

session.setAttribute("count", sessionCount);

}

String name = request.getParameter("name");

String course = request.getParameter("course");

out.println("<html>");

out.println("<body>");

out.println("<h2>Visitor Details</h2>");

out.println("<p>Name: " + name + "</p>");

out.println("<p>Course: " + course + "</p>");

out.println("<p>Unique Visitor Count: " + sessionCount + "</p>");

out.println("</body>");

out.println("</html>");

}

}