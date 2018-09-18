package com.solstice.orderservice.com.solstice.orderservice.Repositories;

import com.solstice.orderservice.com.solstice.orderservice.Domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    //Query 1
    @Query(value = "select * " +
            "from orders o " +
            "where o.account_order_id = ?1 " +
            "order by o.order_date ", nativeQuery = true)
    List<Orders> getAllOrderByAccountId(@Param("account_order_id") long accountId);

    //Query 2
    @Query(value = "select order_number " +
            "from orders o " +
            "where o.account_order_id = ?1 " , nativeQuery = true)
    int getOrderNumberByAccountId(@Param("account_order_id") long accountId);

}
