package com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices;

import com.solstice.orderservice.com.solstice.orderservice.Domain.Account;
import com.solstice.orderservice.com.solstice.orderservice.Domain.Address;
import com.solstice.orderservice.com.solstice.orderservice.Services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "account-service")
public interface AccountOrderProxyClient {

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    Account findById(@PathVariable("accountId") long accountId);

    @RequestMapping(value = "/addresses/{addressId}")
    Address findAddressById(@PathVariable("addressId") long addressId);

}
