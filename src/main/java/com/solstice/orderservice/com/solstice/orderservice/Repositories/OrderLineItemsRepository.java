package com.solstice.orderservice.com.solstice.orderservice.Repositories;

import com.solstice.orderservice.com.solstice.orderservice.Domain.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems,Long> {
    @Query(value = "select * " +
            "from order_line_items o " +
            "where o.order_items_id = ?1 and o.order_items_id = ?2", nativeQuery = true)
    OrderLineItems findByOrderAndOrderLineItemId(@Param("account_addres_id") long orderLineId, @Param("address_id") long orderId);

    @Query(value = "select * " +
            "from order_line_items o " +
            "where o.order_line_id = ?1 and o.product_id = ?2", nativeQuery = true)
    OrderLineItems findProductById(@Param("order_items_id") long orderLineId, @Param("product_id") long productId);

    @Query(value = "select * " +
            "from order_line_items o " +
            "where o.order_line_id = ?1 ", nativeQuery = true)
    List<OrderLineItems> findAllOrderLineItemById(@Param("order_line_id") long orderId);

    @Query(value = "select product_id " +
            "from order_line_items o " +
            "where o.order_line_id = ?1 ", nativeQuery = true)
    Long findProductIdByOrderNumber(@Param("order_line_id") long orderId);


}
