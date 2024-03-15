package com.example.ecommerce_web.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ecommerceDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Abujesus1$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String sql = "SELECT * FROM user WHERE email=? AND password=?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, email);
                    statement.setString(2, password);
                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            // User authenticated
                            String userEmail = result.getString("email");

                            // Set session attribute
                            request.getSession().setAttribute("user", userEmail); // Using email as session attribute for simplicity

                            // Redirect to index.jsp directly after successful login
                            response.sendRedirect("login-success.jsp");
                        } else {
                            // Invalid credentials
                            response.sendRedirect("login-error.jsp"); // Redirect to a custom error page
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                response.sendRedirect("database-error.jsp"); // Redirect to a generic database error page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("class-not-found-error.jsp"); // Redirect to a class not found error page
        }
    }
}

