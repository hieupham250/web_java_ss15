package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Order;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImp implements OrderRepository {
    @Override
    public boolean create(Order order) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call create_order(?, ?, ?, ?)}");
            cs.setInt(1, order.getIdUser());
            cs.setString(2, order.getRecipientName());
            cs.setString(3, order.getAddress());
            cs.setString(4, order.getPhoneNumber());
            result = cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call get_orders_by_user_id(?)}");
            cs.setInt(1, userId);
            rs = cs.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("id_user"),
                        rs.getString("recipient_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getTimestamp("order_date").toLocalDateTime()
                );
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return orders;
    }

    @Override
    public Order findById(int orderId) {
        Connection conn = null;
        CallableStatement cs = null;
        Order order = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call get_order_by_id(?)}");
            cs.setInt(1, orderId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getString("recipientName"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getTimestamp("orderDate").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return order;
    }

    @Override
    public int findLastInsertId() {
        return 0;
    }
}
