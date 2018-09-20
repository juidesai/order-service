package com.solstice.orderservice.com.solstice.orderservice.Services;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.orderservice.com.solstice.orderservice.Domain.*;
import com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices.AccountOrderProxyClient;
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
    private AccountOrderProxyClient accountOrderProxyClient;

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

    @HystrixCommand(fallbackMethod = "addNewOrderFallback")
    public Orders addNewOrder(Orders orders){
        if((accountOrderProxyClient.findById(orders.getAccount_order_id())!=null) && (accountOrderProxyClient.findAddressById(orders.getAddress_order_id())!=null)){
            return ordersRepository.save(orders);
        }else {
            logger.error("invalid AccountId or AddressId");
            return new Orders();
        }
    }

    public List<OrderLineItems> getOrderLineItemsByOrderId(long orderId){
        Orders orders=ordersRepository.getOne(orderId);
        return orders.getOrderLineItemsList();
    }

    @HystrixCommand(fallbackMethod = "getSummaryDataByAccountIdFallback")
    public SummaryData getSummaryDataByAccountId(long accountId) {
        SummaryData summaryData=new SummaryData();
        long orderNumber=ordersRepository.getOrderNumberByAccountId(accountId); //getting orderNumber from order repository
        summaryData.setOrderNumber(orderNumber);

        long addressId=shipmentOrderProxyClient.getShippingAddressIdByAccountId(accountId);//getting shipping address from shiiping client
        Address address= accountOrderProxyClient.findAddressById(addressId);
        summaryData.setAddress(address);

        List<OrderLineItems> orderLineItemsList=orderLineItemsRepository.findAllOrderLineItemById(orderNumber);
        for(OrderLineItems orderLineItems : orderLineItemsList){
            long productId1=orderLineItemsRepository.findProductIdByOrderNumber(orderNumber);//getting productId from orderline repo
            //using that product id getting product object from product client
            summaryData.setProduct(productOrderProxyClient.findProductById(productId1));
            summaryData.setQuantity(orderLineItems.getQuantity());
            summaryData.setPrice(orderLineItems.getPrice());
            summaryData.setTotalPrice(orderLineItems.getTotalPrice());
            summaryData.setShipment(shipmentOrderProxyClient.getShipmentByAccountId(accountId));
        }
        return summaryData;
    }

    @SuppressWarnings("unused")
    public SummaryData getSummaryDataByAccountIdFallback(long accountId){
        SummaryData summaryData=null;
        logger.error("other service is down");
        return summaryData;
    }

    public Orders addNewOrderFallback(Orders orders){
        logger.error("account-service is down");
        return new Orders();
    }
}
