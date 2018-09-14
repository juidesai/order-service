package com.solstice.orderservice.com.solstice.orderservice.Domain;

import javax.persistence.Column;
import java.util.List;

public class SummaryData {
    private long orderNumber;
    private Address address;
    private Product product;
    private int quantity;
    private double price;
    private double totalPrice;
    private Shipment shipment;


    public SummaryData(){}

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SummaryData(long orderNumber, long shippingAddressId, double totalPrice, long orderLineItemId, long shipmentId) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;

    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalPrice() {
        return price*quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }



}
