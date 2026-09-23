import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;


@WebServlet("/SessionServlet")

public class SessionServlet extends HttpServlet {


protected void doPost(HttpServletRequest request,

HttpServletResponse response)

throws ServletException, IOException {


response.setContentType("text/html");


PrintWriter out = response.getWriter();


// Get form data

String name = request.getParameter("name");

String course = request.getParameter("course");


out.println("<html>");

out.println("<body>");


out.println("<h2>Session Servlet</h2>");

out.println("<p>Name: " + name + "</p>");

out.println("<p>Course: " + course + "</p>");


// URL Rewriting

String url = response.encodeURL(

"VisitorServlet?name=" + name + "&course=" + course

);


out.println("<a href='" + url + "'>");

out.println("Continue to Visitor Servlet");

out.println("</a>");


out.println("</body>");

out.println("</html>");

}

}