package com.data.repository;

import com.data.model.Order;

import java.util.List;

public interface OrderRepository {
    boolean create(Order order);
    List<Order> findOrdersByUserId(int userId);
    Order findById(int orderId);
    int findLastInsertId();
}
