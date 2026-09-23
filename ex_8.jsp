<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Registration Details</title>

</head>

<body>

<h1>User Registration Details</h1>

<%

String YourName=request.getParameter("yourname");

String UserName=request.getParameter("username");

String Password=request.getParameter("password");

String Creditcard=request.getParameter("credit cord no");

String PhoneNumber=request.getParameter("phone no");

String Debitcard=request.getParameter("debit cord no");

String Email=request.getParameter("email");

%>

<p><strong>Your Name:</strong><%=YourName%></p>

<p><strong>User Name:</strong><%=UserName%></p>

<p><strong>Email:</strong><%=Email%></p>

<p><strong>Credit card Number:</strong><%=Creditcard%></p>

<p><strong>Phone Number:</strong><%=PhoneNumber%></p>

<p><strong>debit card Number:</strong><%=Debitcard%></p>

<p><strong>Password:</strong><%=Password%></p>


</body>

</html>