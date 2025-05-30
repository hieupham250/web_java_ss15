package com.data.service;

import com.data.model.Order;

import java.util.List;

public interface OrderService {
    boolean create(Order order);
    List<Order> findOrdersByUserId(int userId);
    Order findById(int orderId);
    int findLastInsertId();
}
