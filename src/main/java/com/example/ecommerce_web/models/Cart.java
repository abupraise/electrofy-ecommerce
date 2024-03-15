package com.example.ecommerce_web.models;

public class Cart extends Product {
    private int quantity;
    public Cart(){
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
