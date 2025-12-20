package com.cdac;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewBooksServlet extends HttpServlet  {
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	BookDAO bd = new BookDAO();
	
	List<Book> list = null;
	
	try {
		list = bd.getAllBooks();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	req.setAttribute("bookList", list);
	
	RequestDispatcher rd = req.getRequestDispatcher("viewBooks.jsp");
	rd.forward(req,res);
}
}
