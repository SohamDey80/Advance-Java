<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
    <h2>Edit Student</h2>
    <form action="${pageContext.request.contextPath}/updateStudent" method="post">
        <input type="hidden" name="oldId" value="${student.id}"/>
        
        ID: <input type="text" name="id" value="${student.id}"/><br><br>
        
        Name: <input type="text" name="name" value="${student.name}"/><br><br>
        Email: <input type="email" name="email" value="${student.email}"/><br><br>
        Course: <input type="text" name="course" value="${student.course}"/><br><br>
        
        <input type="submit" value="Update Record"/>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/viewStudents">Cancel</a>
</body>
</html>