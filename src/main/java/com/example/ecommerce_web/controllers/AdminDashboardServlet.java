package com.example.ecommerce_web.controllers;


import com.example.ecommerce_web.dao.ProductDao;
import com.example.ecommerce_web.models.Product;
import com.example.ecommerce_web.util.ConnectionUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            ProductDao productDao = new ProductDao(connection);
            List<Product> products = productDao.getAllProduct();
            request.setAttribute("Products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminDashboard.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirecting to an error page
        }
    }
}

