package com.data.service;

import com.data.model.Order;
import com.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean create(Order order) {
        return orderRepository.create(order);
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public Order findById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public int findLastInsertId() {
        return 0;
    }
}
