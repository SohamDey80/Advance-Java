// RegisterServlet.java
package com.cdac;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet
{

	public void doPost(HttpServletRequest request , HttpServletResponse response  ) throws IOException, ServletException
	{
		          response.setContentType("text/html");
		          // PrintWriter out=response.getWriter(); // Not needed if redirecting/forwarding
		          
		          HttpSession session = request.getSession(false);
		          
		          if(session == null || session.getAttribute("username") == null){
		        	  response.sendRedirect("login.html");
		              return;
		          }

		          String uname =request.getParameter("name");
		          String course =request.getParameter("course");
		          String email =request.getParameter("email");
		          
		          
		          session.setAttribute("sname", uname);
		          session.setAttribute("scourse", course);
		          session.setAttribute("semail", email);
		          
		          // *** DEBUG STEP: CHECK CONSOLE OUTPUT ***
                  System.out.println("--- RegisterServlet Debug ---");
                  System.out.println("Session ID: " + session.getId());
                  System.out.println("Stored Name: " + session.getAttribute("sname"));
                  System.out.println("Stored Course: " + session.getAttribute("scourse"));
                  System.out.println("-----------------------------");
                  // If these print statements show 'null', the problem is reading parameters from register.html.
		          
		          // Use redirect to cleanly navigate to the view page
		          response.sendRedirect("view.jsp");
		          
	}
}