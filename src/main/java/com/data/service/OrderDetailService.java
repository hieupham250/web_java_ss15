package com.data.service;

import com.data.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    boolean createOrderDetail(OrderDetail detail);
    List<OrderDetail> findByOrderId(int orderId);
}
