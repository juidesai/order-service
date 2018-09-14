package com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices;

import com.solstice.orderservice.com.solstice.orderservice.Domain.Shipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shipment-service")
public interface ShipmentOrderProxyClient {
    @GetMapping(value = "/shipments/accountId/{accountId}/addressId")
    long getShippingAddressIdByAccountId(@PathVariable(value = "accountId") long accountId);

    @GetMapping(value = "/shipmentsDetails/{accountId}")
    Shipment getShipmentByAccountId(@PathVariable(value = "accountId") long accountId);
}
