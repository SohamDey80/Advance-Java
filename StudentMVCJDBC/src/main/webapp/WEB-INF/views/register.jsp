<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<h2>Student Registration</h2>

<form action="registerStudent" method="post">
    Name: <input type="text" name="name"/><br><br>
    Email: <input type="email" name="email"/><br><br>
    Course: <input type="text" name="course"/><br><br>
    <input type="submit" value="Register"/>
</form>

<br>
<a href="viewStudents">View All Students</a>
