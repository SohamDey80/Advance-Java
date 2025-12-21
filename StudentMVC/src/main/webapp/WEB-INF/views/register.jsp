<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
</head>
<body>
    <h2>Student Registration Form</h2>

    <form action="registerStudent" method="post">
        ID: <input type="text" name="id"><br><br>
        Name: <input type="text" name="name"><br><br>
        Course: <input type="text" name="course"><br><br>
        Email: <input type="text" name="email"><br><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>

