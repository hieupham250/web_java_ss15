package com.data.service;

import com.data.model.OrderDetail;
import com.data.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public boolean createOrderDetail(OrderDetail detail) {
        return orderDetailRepository.createOrderDetail(detail);
    }

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
