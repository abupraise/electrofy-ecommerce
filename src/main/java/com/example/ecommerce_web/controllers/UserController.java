package com.example.ecommerce_web.controllers;

import com.example.ecommerce_web.dao.UserDao;
import com.example.ecommerce_web.models.User;
import com.example.ecommerce_web.util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user", value = "/signup")
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao(ConnectionUtil.getConnection());

        if (userDao.emailExists(email)) {
            request.setAttribute("signupError", "This email is already in use. Please Log in.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPassword(password);

            userDao.addUser(newUser);
            response.sendRedirect("signup-success.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
