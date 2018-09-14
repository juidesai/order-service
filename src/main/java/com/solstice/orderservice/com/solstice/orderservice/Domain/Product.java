package com.solstice.orderservice.com.solstice.orderservice.Domain;

public class Product {
    private long productId;
    private String productName;

    public Product(){}
    public Product(long productId, String productName) {
        this.productId = productId;
        this.productName = productName;

    }
//
    public long getProductId() {
        return productId;
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
