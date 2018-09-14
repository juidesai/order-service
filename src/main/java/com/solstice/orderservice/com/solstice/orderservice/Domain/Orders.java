package com.solstice.orderservice.com.solstice.orderservice.Domain;

//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Orders")
@JsonIgnoreProperties
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderNumber")
    private long orderNumber;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Transient
    @JsonIgnore
    private double grandTotal;

    @Column(name = "account_order_id")
    private long account_order_id;

    @Column(name = "address_order_id")
    private long address_order_id;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderLineItems> orderLineItemsList;



    public Orders(){}

    public Orders(long orderNumber, LocalDateTime orderDate){
        this.orderNumber=orderNumber;
        this.orderDate=orderDate;
    }

    public Orders(long orderNumber, LocalDateTime orderDate, long address_order_id, List<OrderLineItems> orderLineItemsList, long account_order_id) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.account_order_id=account_order_id;
        this.orderLineItemsList = orderLineItemsList;
        this.address_order_id=address_order_id;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }


    public double getGrandTotal() {
        double totalPrice=0;
        for(OrderLineItems orderLineItems:orderLineItemsList){
            totalPrice+=orderLineItems.getTotalPrice();
        }
        this.grandTotal = totalPrice;
        return this.grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public long getAccount_order_id() {
        return account_order_id;
    }

    public void setAccount_order_id(long account_order_id) {
        this.account_order_id = account_order_id;
    }

    public long getAddress_order_id() {
        return address_order_id;
    }

    public void setAddress_order_id(long address_order_id) {
        this.address_order_id = address_order_id;
    }

    public List<OrderLineItems> getOrderLineItemsList() {
        return orderLineItemsList;
    }

    public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
        this.orderLineItemsList = orderLineItemsList;
    }
}
