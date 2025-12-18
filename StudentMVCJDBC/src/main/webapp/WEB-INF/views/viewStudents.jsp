<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Registered Students</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Course</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td>${s.course}</td>
            <td>
                <a href="${pageContext.request.contextPath}/editStudent/${s.id}">Edit</a> | 
                <a href="${pageContext.request.contextPath}/deleteStudent/${s.id}" 
                   onclick="return confirm('Delete this record?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="register">Add New Student</a>