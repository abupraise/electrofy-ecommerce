package com.example.ecommerce_web.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "adminServlet", value="/admin-servlet")
public class AdminLoginServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ecommerceDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Abujesus1$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String adminToken = request.getParameter("adminToken");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String sql = "SELECT * FROM admin WHERE email= ? AND adminToken=?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, email);
                    statement.setString(2, adminToken);
                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            // Admin authenticated
                            HttpSession session = request.getSession();
                            session.setAttribute("adminEmail", email); // Store admin email in session
                            response.sendRedirect("admin-login-success.jsp"); // Redirect to login success page
                        } else {
                            // Invalid credentials
                            response.sendRedirect("login-error.jsp");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("database-error.jsp"); // Redirect to a generic database error page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("class-not-found-error.jsp"); // Redirect to a class not found error page
        }

    }
}
