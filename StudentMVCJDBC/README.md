# create 'com.cdac' package inside "src/main/java"

## Student.java
```
package com.cdac;

public class Student {

	private int id;
	private String name;
	private String email;
	private String course;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}

```

## StudentDAO.java
```
package com.cdac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentDAO {

    @Autowired
    private DataSource dataSource;

    public void registerStudent(Student student) {
        String sql = "INSERT INTO student(name, email, course) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Student> viewAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM student");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setCourse(rs.getString("course"));
                list.add(s);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // --- NEW METHODS ---

    public Student getStudentById(int id) {
        Student s = null;
        String sql = "SELECT * FROM student WHERE id = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setCourse(rs.getString("course"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }

    public void updateStudent(Student student, int oldId) {
        // We update the 'id' column along with other fields
        String sql = "UPDATE student SET id=?, name=?, email=?, course=? WHERE id=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, student.getId());    // The New ID
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            ps.setInt(5, oldId);              // The Old ID to find the row
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}

```
## StudentController.java
```
package com.cdac;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/registerStudent")
    public String register(@ModelAttribute Student student) {
        studentDAO.registerStudent(student);
        return "redirect:/viewStudents";
    }

    @GetMapping("/viewStudents")
    public String viewStudents(Model model) {
        List<Student> students = studentDAO.viewAllStudents();
        model.addAttribute("students", students);
        return "viewStudents";
    }

    // --- NEW HANDLERS ---

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") int id, Model model) { // Added ("id")
        Student student = studentDAO.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/updateStudent")
    public String update(@ModelAttribute Student student, @RequestParam("oldId") int oldId) {
        studentDAO.updateStudent(student, oldId);
        return "redirect:/viewStudents";
    }

    @GetMapping("/deleteStudent/{id}")
    public String delete(@PathVariable("id") int id) { // Added ("id")
        studentDAO.deleteStudent(id);
        return "redirect:/viewStudents";
    }
}

```
# src/main/resources

## applicationContext.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx">
<context:component-scan base-package="com.cdac"/>
<!--  DataSource  -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://localhost:3306/studentdb"/>
<property name="username" value="root"/>
<property name="password" value="Sohamdey#123"/>
</bean>
<!--  Transaction Manager  -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
<property name="dataSource" ref="dataSource"/>
</bean>
<tx:annotation-driven/>
</beans>
```

# WEB-INF
## dispatcher-servlet.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=" http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
<mvc:annotation-driven/>
<context:component-scan base-package="com.cdac"/>
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/studentdb"/>
        <property name="username" value="root"/>
        <property name="password" value="Sohamdey#123"/>
    </bean>
</beans>

```

## web.xml
```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
<servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>

```

## views/ editStudent.jsp
```
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

```

## views/ register.jsp
```
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

```

## views/ viewStudents.jsp
```
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

```

# pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>cdac</groupId>
  <artifactId>StudentMVCJDBC</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>StudentMVCJDBC Maven Webapp</name>
  <url>http://maven.apache.org</url>
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>6.1.13</version> </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>6.1.13</version>
    </dependency>

    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <version>6.0.0</version>
        <scope>provided</scope>
    </dependency>

    <dependency>
        <groupId>jakarta.servlet.jsp.jstl</groupId>
        <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
        <version>3.0.0</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.web</groupId>
        <artifactId>jakarta.servlet.jsp.jstl</artifactId>
        <version>3.0.1</version>
    </dependency>

    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
</dependencies>
  <build>
    <finalName>StudentMVCJDBC</finalName>
  </build>
</project>
```
