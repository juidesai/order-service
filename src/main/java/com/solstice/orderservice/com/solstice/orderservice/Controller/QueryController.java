package com.solstice.orderservice.com.solstice.orderservice.Controller;

import com.solstice.orderservice.com.solstice.orderservice.Domain.Account;
import com.solstice.orderservice.com.solstice.orderservice.Domain.OrderLineItems;
import com.solstice.orderservice.com.solstice.orderservice.Domain.Orders;
import com.solstice.orderservice.com.solstice.orderservice.Domain.SummaryData;
import com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices.AccountOrderProxy;
import com.solstice.orderservice.com.solstice.orderservice.Repositories.OrderLineItemsRepository;
import com.solstice.orderservice.com.solstice.orderservice.Repositories.OrdersRepository;
import com.solstice.orderservice.com.solstice.orderservice.Services.OrderLineItemsService;
import com.solstice.orderservice.com.solstice.orderservice.Services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueryController {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderLineItemsRepository orderLineItemsRepository;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderLineItemsService orderLineItemsService;

    @Autowired
    private AccountOrderProxy accountOrderProxy;

    @GetMapping(value = "/orders/accountId/{accountId}")
    public List<Orders> getAllOrderByAccountId(@PathVariable(value = "accountId") long accountId)
    {
        return ordersRepository.getAllOrderByAccountId(accountId);
    }

    @GetMapping(value = "/orders/{orderId}/orderLineItems")
    public List<OrderLineItems> getOrderLineItemsByOrderId(@PathVariable(value = "orderId") long orderId){
                return orderLineItemsService.getAllOrderLineItemsById(orderId);
    }

    @PostMapping(value = "/orders/{orderId}/orderLineItems")
    public OrderLineItems addNewOrderLineItemByOrderId(@RequestBody OrderLineItems orderLineItems, @PathVariable(value = "orderId") long orderId){
        return orderLineItemsService.addNewOrderLineByOrderId(orderLineItems,orderId);
    }

    @PutMapping(value = "/orders/{orderId}/orderLineItems/{orderLineId}")
    public OrderLineItems updateOrderLineItemByorderId(@RequestBody OrderLineItems orderLineItems, @PathVariable(value = "orderId") long orderId, @PathVariable(value = "orderLineId")long orderLineId){
        return orderLineItemsService.uodateOrderLineItemByOrderId(orderLineItems,orderId,orderLineId);
    }

    @DeleteMapping(value = "/orders/{orderId}/orderLineItems/{orderLineId}")
    public void deleteOrderLineItemById( @PathVariable(value = "orderId") long orderId, @PathVariable(value = "orderLineId")long orderLineId){
         orderLineItemsService.deleteOrderLineItemById(orderId,orderLineId);
    }

    @GetMapping(value = "/feign/{accountId}")
    public Account findById(@PathVariable(value = "accountId") long accountId){
        Account account = accountOrderProxy.findById(accountId);
        return accountOrderProxy.findById(accountId);
    }

    //product
    @GetMapping(value = "/orderlineItem/{orderLineItemId}/product/{productId}")
    public OrderLineItems findProductById(@PathVariable(value = "orderLineItemId") long orderLineItemId, @PathVariable(value = "productId") long productId){
        return orderLineItemsRepository.findProductById(orderLineItemId,productId);
    }

//    @GetMapping(value = "/orders/{orderId}/orderLineItems")
//    public List<OrderLineItems> findOrderline(long orderId){
//        return orderLineItemsService.getAllOrderLineItemsById(orderId);
//    }

    @GetMapping(value = "/orders/{accountId}")
    public SummaryData findSummaryByAccountId(@PathVariable(value = "accountId") long accountId){
        return ordersService.getSummaryDataByOrderId(accountId);
    }
    @GetMapping("/test/{id}")
    public long test(@PathVariable(value = "id") long id){
        return orderLineItemsRepository.findProductIdByOrderNumber(id);
    }
}
