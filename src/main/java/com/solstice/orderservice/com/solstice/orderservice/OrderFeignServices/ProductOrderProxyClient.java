package com.solstice.orderservice.com.solstice.orderservice.OrderFeignServices;

import com.solstice.orderservice.com.solstice.orderservice.Domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service")
public interface ProductOrderProxyClient {
    @RequestMapping(value = "/products/{productId}")
    Product findProductById(@PathVariable(value = "productId") long productId);

    @RequestMapping(value = "/productPrice/{productId}")
    double findProductPricefromProductId(@PathVariable(value = "productId") long productId);
}
