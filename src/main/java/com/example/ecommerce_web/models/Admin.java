package com.example.ecommerce_web.models;

public class Admin {
    private int adminID;
    private String fullName;
    private String email;
    private String password;
    private String adminToken;

    public Admin(String fullName, String email, String password, String adminToken) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.adminToken = adminToken;
    }

    public Admin() {
    }

    public Admin(int adminID, String fullName, String email, String password, String adminToken) {
        this.adminID = adminID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.adminToken = adminToken;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID=" + adminID +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adminToken='" + adminToken + '\'' +
                '}';
    }
}

