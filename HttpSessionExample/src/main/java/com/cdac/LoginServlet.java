// LoginServlet.java
package com.cdac;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uname = req.getParameter("uname");
        String pass = req.getParameter("pass");

        // Simple validation
        if(uname.equals("admin") && pass.equals("1234")) {

            HttpSession session = req.getSession();  // create new session
            session.setAttribute("username", uname);

            // *** FIX: Redirect directly to the registration form ***
            resp.sendRedirect("register.html");
        } else {
            resp.getWriter().println("Invalid Login... <a href='login.html'>Try Again</a>");
        }
    }
}