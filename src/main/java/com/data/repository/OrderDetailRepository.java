package com.data.repository;

import com.data.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {
    boolean createOrderDetail(OrderDetail detail);
    List<OrderDetail> findByOrderId(int orderId);
}
