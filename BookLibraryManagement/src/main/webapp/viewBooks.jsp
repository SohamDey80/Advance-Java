<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.cdac.Book" %>
    <h2>All Registered Books</h2>
    
    <table border="1" cellpadding="10">
       <tr>
         <th>ID</th><th>Title</th><th>Author</th><th>Genre</th><th>Year</th>
       </tr>
       
  <%
  		List<Book> list = (List<Book>) request.getAttribute("bookList");
  		for (Book b : list) {
  %>
    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getTitle() %></td>
        <td><%= b.getAuthor() %></td>
        <td><%= b.getGenre() %></td>
        <td><%= b.getYear_published() %></td>
    </tr>
  <% 
  		}
   %>
    </table>
    
    <br>
    <a href="addBook.html">Register Another Book</a>