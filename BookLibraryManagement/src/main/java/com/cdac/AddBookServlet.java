package com.cdac;
import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddBookServlet  extends HttpServlet{
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	int status = 0;
	String title = req.getParameter("title");
	String author = req.getParameter("author");
	String genre = req.getParameter("genre");
	String year = req.getParameter("year");
	int yearPublished = Integer.parseInt(year);
	
	Book b = new Book(title,author,genre,yearPublished);
	
	BookDAO bd = new BookDAO();
	  
	
	   try {
		   status = bd.writeBook(b);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   if(status>0) {
		   res.sendRedirect("success.jsp");
	   }else {
		   res.sendRedirect("error.html");
	   }
}
}











