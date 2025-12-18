<%@ page import="java.util.*, com.cdac.Student"%>

<h2>All Registered Students</h2>
<link rel="stylesheet" href="css/style.css">

<table border="1" cellpadding="10">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Email</th>
		<th>mobile</th>
	</tr>

	<%
    List<Student> list = (List<Student>) request.getAttribute("studentList");
    for (Student s : list) {
%>
	<tr>
		<td><%= s.getId() %></td>
		<td><%= s.getName() %></td>
		<td><%= s.getEmail() %></td>
		<td><%= s.getMobile() %></td>
	</tr>
	<%
    }
%>
</table>

<br>
<a href="register.html">Register Another Student</a>