package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.OrderDetail;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailRepositoryImp implements OrderDetailRepository {
    @Override
    public boolean createOrderDetail(OrderDetail detail) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call create_order_detail(?, ?, ?, ?)}");
            cs.setInt(1, detail.getOrderId());
            cs.setInt(2, detail.getProductId());
            cs.setInt(3, detail.getQuantity());
            cs.setDouble(4, detail.getCurrentPrice());
            result = cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        Connection conn = null;
        CallableStatement cs = null;
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call get_order_details_by_order_id(?)}");
            cs.setInt(1, orderId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("orderId"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getDouble("currentPrice")
                );
                orderDetails.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return orderDetails;
    }
}
