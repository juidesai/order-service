package com.solstice.orderservice.com.solstice.orderservice.Services;


import com.solstice.orderservice.com.solstice.orderservice.Domain.OrderLineItems;
import com.solstice.orderservice.com.solstice.orderservice.Domain.Orders;
import com.solstice.orderservice.com.solstice.orderservice.Domain.Product;
import com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices.ProductOrderProxyClient;
import com.solstice.orderservice.com.solstice.orderservice.Repositories.OrderLineItemsRepository;
import com.solstice.orderservice.com.solstice.orderservice.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineItemsService {
    @Autowired
    private OrderLineItemsRepository orderLineItemsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductOrderProxyClient productOrderProxyClient;

    public OrderLineItemsService(OrderLineItemsRepository orderLineItemsRepository) { this.orderLineItemsRepository = orderLineItemsRepository;
    }

    public List<OrderLineItems> getAllOrderLineItems() {
        return orderLineItemsRepository.findAll();
    }

    public void addNewOrderLineItem(OrderLineItems orderLineItems) {
        orderLineItemsRepository.save(orderLineItems);
    }

    public OrderLineItems addNewOrderLineByOrderId(OrderLineItems orderLineItems,long orderId){
        Orders orders=ordersRepository.getOne(orderId);
        //long productId=orderLineItems.getProductId();
        //orderLineItems.setPrice(productOrderProxyClient.findProductPricefromProductId(productId));
        orderLineItems.setOrders(orders);
        return orderLineItemsRepository.save(orderLineItems);
    }

    public OrderLineItems uodateOrderLineItemByOrderId(OrderLineItems orderLineItems, long orderLineItemId,long orderId){
        OrderLineItems orderLineItems1=orderLineItemsRepository.findByOrderAndOrderLineItemId(orderLineItemId,orderId);
        orderLineItems1.setProductId(orderLineItems.getProductId());
        orderLineItems1.setPrice(orderLineItems.getPrice());
        orderLineItems1.setQuantity(orderLineItems.getQuantity());
        return orderLineItemsRepository.save(orderLineItems1);
    }

    public void deleteOrderLineItemById(long orderId, long orderLineId){
        OrderLineItems orderLineItems=orderLineItemsRepository.findByOrderAndOrderLineItemId(orderLineId,orderId);
        orderLineItemsRepository.delete(orderLineItems);
        System.out.println("Deleted Successfully");
    }

    public List<OrderLineItems> getAllOrderLineItemsById(long orderId){
        return orderLineItemsRepository.findAllOrderLineItemById(orderId);
    }

}

