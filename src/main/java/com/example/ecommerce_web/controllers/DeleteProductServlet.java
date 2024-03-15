package com.example.ecommerce_web.controllers;


import com.example.ecommerce_web.dao.ProductDao;
import com.example.ecommerce_web.util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection con = ConnectionUtil.getConnection()) {
            ProductDao productDao = new ProductDao(con);
            boolean result = productDao.deleteProduct(id);
            if (result) {
                response.sendRedirect("adminDashboard.jsp?status=deleted");
            } else {
                response.sendRedirect("adminDashboard.jsp?status=fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?status=error");
        }
    }
}

