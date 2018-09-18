package com.solstice.orderservice.com.solstice.orderservice.Domain;

import javax.persistence.*;

@Entity
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderItemsId;
    @Column(name = "productId")
    private Long productId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "total_price")
    private double totalPrice;



    @ManyToOne
    @JoinColumn(name = "order_line_id")
    private Orders orders;

    public OrderLineItems(){}
    public OrderLineItems(long orderItemsId, int quantity, Long productId, double price, double totalPrice ) {
        this.orderItemsId = orderItemsId;
        this.productId=productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = this.getTotalPrice();

    }

    public long getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(long orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return price*quantity;
    }

    public void setTotalPrice(OrderLineItems orderLineItems) {
        this.totalPrice = orderLineItems.getTotalPrice();
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
