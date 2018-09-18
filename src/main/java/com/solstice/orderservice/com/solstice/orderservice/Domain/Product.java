package com.solstice.orderservice.com.solstice.orderservice.Domain;

public class Product {
    private long productId;
    private String productName;
    private double price;

    public Product(){}
    public Product(long productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price=price;
    }
//
    public long getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
