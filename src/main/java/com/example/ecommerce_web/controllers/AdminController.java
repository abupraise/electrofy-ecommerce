package com.example.ecommerce_web.controllers;


import com.example.ecommerce_web.dao.AdminDao;
import com.example.ecommerce_web.dao.UserDao;
import com.example.ecommerce_web.models.Admin;
import com.example.ecommerce_web.models.User;
import com.example.ecommerce_web.util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "admin", value = "/admin-signup")
public class AdminController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String adminToken = req.getParameter("adminToken");

        AdminDao adminDao = new AdminDao(ConnectionUtil.getConnection());

        if (adminDao.emailExists(email)) {
            req.setAttribute("signupError", "This email is already in use. Please Log in.");
            req.getRequestDispatcher("/adminLogin.jsp").forward(req, resp);
        } else {
            Admin newAdmin = new Admin(fullName, email, password, adminToken);
            adminDao.addAdmin(newAdmin);
            resp.sendRedirect("admin-login-success.jsp");
        }
    }
}

