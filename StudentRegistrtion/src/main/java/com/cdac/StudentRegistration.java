package com.cdac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class StudentRegistration
 */
@WebServlet("/StudentRegistration") 
public class StudentRegistration extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Read values from the form
		String sid = request.getParameter("studentId");
		String name = request.getParameter("studentName");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String course = request.getParameter("course");
		
		//printing
		out.println("<h1>Student Registration Details</h1>");
		out.println("<p><b>Student ID:</b> " + sid + "</p>");
		out.println("<p><b>Name:</b>" + name + "</p>");
		out.println("<p><b>Email:</b>" + email + "</p>");
		out.println("<p><b>Gender:</b>" + gender + "</p>");
		out.println("<p><b>Course:</b> " + course + "</p>");
	}
}
