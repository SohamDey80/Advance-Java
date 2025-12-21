package com.cdac;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{

	public void doPost(HttpServletRequest request , HttpServletResponse response  ) throws IOException
	{
		          response.setContentType("text/html");
		          PrintWriter out=response.getWriter();
		          
		          String uname =request.getParameter("username");
		          String pwd =request.getParameter("password");
		          
		          if(uname.equals("admin") && pwd.equals("admin123"))
		          {
//		        	  out.println("<h1> Login success ! </h1>"  );
//		        	  out.println("<p> Welcome ," + uname + "</p>");
		        	  
		        	  response.sendRedirect("success.html");
		        	  
		          }else
		          {
		        	 // out.println("<h1> Invalid user name or password  ! login again  </h1>"  );
		        	  response.sendRedirect("error.html");
		          }
		          
		          
		
		
	} 

}

