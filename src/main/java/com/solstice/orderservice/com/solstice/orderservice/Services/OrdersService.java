package com.solstice.orderservice.com.solstice.orderservice.Services;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.orderservice.com.solstice.orderservice.Domain.*;
import com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices.AccountOrderProxy;
import com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices.ProductOrderProxyClient;
import com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices.ShipmentOrderProxyClient;
import com.solstice.orderservice.com.solstice.orderservice.Repositories.OrderLineItemsRepository;
import com.solstice.orderservice.com.solstice.orderservice.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderLineItemsRepository orderLineItemsRepository;

    @Autowired
    private AccountOrderProxy accountOrderProxy;

    @Autowired
    private ShipmentOrderProxyClient shipmentOrderProxyClient;

    @Autowired
    private ProductOrderProxyClient productOrderProxyClient;

    private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);
    public OrdersService(OrdersRepository ordersRepository) { this.ordersRepository = ordersRepository;
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void addNewOrders(Orders orders) {
        ordersRepository.save(orders);
    }

    public List<OrderLineItems> getOrderLineItemsByOrderId(long orderId){
        Orders orders=ordersRepository.getOne(orderId);
        return orders.getOrderLineItemsList();
    }

    @HystrixCommand(fallbackMethod = "getSummaryDataByOrderIdFallback")
    public SummaryData getSummaryDataByOrderId(long accountId){
        SummaryData summaryData=new SummaryData();
        long orderNumber=ordersRepository.getOrderNumberByAccountId(accountId);
        summaryData.setOrderNumber(orderNumber);
        long addressId=shipmentOrderProxyClient.getShippingAddressIdByAccountId(accountId);
        Address address=accountOrderProxy.findAddressById(addressId);
        summaryData.setAddress(address);
        List<OrderLineItems> orderLineItemsList=orderLineItemsRepository.findAllOrderLineItemById(orderNumber);
        for(OrderLineItems orderLineItems : orderLineItemsList){
            long productId1=orderLineItemsRepository.findProductIdByOrderNumber(orderNumber);
            summaryData.setProduct(productOrderProxyClient.findProductById(productId1));
            summaryData.setQuantity(orderLineItems.getQuantity());
            summaryData.setPrice(orderLineItems.getPrice());
            summaryData.setTotalPrice(orderLineItems.getTotalPrice());
            summaryData.setShipment(shipmentOrderProxyClient.getShipmentByAccountId(accountId));
        }
        return summaryData;
    }

    @SuppressWarnings("unused")
    public SummaryData getSummaryDataByOrderIdFallback(long accountId){
        SummaryData summaryData=new SummaryData();
        logger.error("other service is down");
        return summaryData;
    }
}
