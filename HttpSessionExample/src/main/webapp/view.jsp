<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    session = request.getSession(false);

    // If not logged in, redirect to login
    if(session == null || session.getAttribute("username") == null){
        response.sendRedirect("login.html");
        return;
    }

    String name = (String) session.getAttribute("sname");
    String course = (String) session.getAttribute("scourse");
    String email = (String) session.getAttribute("semail");
    
    // Optional: If registration details are missing, send user back to register page
    if (name == null || course == null || email == null) {
        response.sendRedirect("register.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
<body>

<h2>Student Registration Details</h2>

<table border="1" cellpadding="8">
    <tr><th>Name</th><td><%= name %></td></tr>
    <tr><th>Course</th><td><%= course %></td></tr>
    <tr><th>Email</th><td><%= email %></td></tr>
</table>

<br><br>
<p><a href="register.html">Go Back to Registration Form</a></p>
<a href="logout">Logout</a>

</body>
</html>

