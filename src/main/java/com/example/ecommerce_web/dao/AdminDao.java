package com.example.ecommerce_web.dao;


import com.example.ecommerce_web.models.Admin;
import com.example.ecommerce_web.models.User;
import com.example.ecommerce_web.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private Connection connection;

    public AdminDao(Connection connection) {
        this.connection = ConnectionUtil.getConnection();
    }

    public boolean emailExists(String email) {
        boolean exists = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT COUNT(*) FROM admin WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }
    public void addAdmin(Admin admin) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into admin(fullName,email,password,adminToken) values (?,?,?,?)");
            preparedStatement.setString(1, admin.getFullName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setString(4, admin.getAdminToken());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}