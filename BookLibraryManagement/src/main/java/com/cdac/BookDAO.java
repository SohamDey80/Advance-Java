package com.cdac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sohamdb","root","Sohamdey#123");      
		return con;
	}
	
	public int writeBook(Book b) throws ClassNotFoundException, SQLException {
		Connection con = BookDAO.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into books(title,author,genre,year_published) values (?,?,?,?)");
		
		ps.setString(1, b.getTitle());
		ps.setString(2, b.getAuthor());
		ps.setString(3, b.getGenre());
		ps.setInt(4, b.getYear_published());
		
		int status = ps.executeUpdate();
		return status;
		
	}
	

	public List<Book> getAllBooks() throws ClassNotFoundException, SQLException {
		List<Book> list = new ArrayList<>();
		
		   Connection con = BookDAO.getConnection();
			String q = "select * from books";
			PreparedStatement ps = con.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setGenre(rs.getString(4));
				b.setYear_published(rs.getInt(5));
				
				list.add(b);
			}
			
			
			return list;
			
	}

}








