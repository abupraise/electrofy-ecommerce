package com.example.ecommerce_web.dao;

import com.example.ecommerce_web.models.Cart;
import com.example.ecommerce_web.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }
    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();
        try{
            query = "SELECT * FROM products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()){
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                products.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public List<Cart> getCartProducts(ArrayList<Cart> cartList){
        List<Cart> products = new ArrayList<>();
        try{
            if (cartList.size()>0){
                for (Cart items:cartList ){
                    query = "SELECT * FROM products WHERE id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1,items.getId());
                    rs = pst.executeQuery();
                    while (rs.next()){
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*items.getQuantity());
                        row.setQuantity(items.getQuantity());
                        products.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public double getTotalCartPrice(ArrayList<Cart> cartList){
        double sum = 0;
        try {
            if (cartList.size() > 0){
                for (Cart items : cartList) {
                    query = "SELECT price FROM products WHERE id=? ";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, items.getId());
                    rs = pst.executeQuery();
                    while (rs.next()){
                        sum += rs.getDouble("price")*items.getQuantity();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }
    // In your existing ProductDao class

    public boolean addProduct(Product product) {
        try {
            query = "INSERT INTO products (name, category, price, image) VALUES (?, ?, ?, ?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setString(2, product.getCategory());
            pst.setDouble(3, product.getPrice());
            pst.setString(4, product.getImage());
            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        try {
            query = "DELETE FROM products WHERE id = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

