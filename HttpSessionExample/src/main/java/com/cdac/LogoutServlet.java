package com.cdac;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        HttpSession session = req.getSession(false);

	        if(session != null){
	            session.invalidate();
	        }

//	        RequestDispatcher rd =req.getRequestDispatcher("view.jsp");
//	          rd.forward(req, resp);
	        
	        resp.sendRedirect("login.html");
	    }
	}
	

	
	
	
	
