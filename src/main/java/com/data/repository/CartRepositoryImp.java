package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Cart;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImp implements CartRepository {
    @Override
    public List<Cart> findAll() {
        Connection conn = null;
        CallableStatement cs = null;
        List<Cart> carts = new ArrayList<Cart>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call find_all_cart()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getInt("id_product"),
                        rs.getInt("quantity")
                );
                carts.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return carts;
    }

    @Override
    public List<Cart> findByUserId(int id) {
        Connection conn = null;
        CallableStatement cs = null;
        List<Cart> carts = new ArrayList<Cart>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call find_cart_by_user_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getInt("id_product"),
                        rs.getInt("quantity")
                );
                carts.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return carts;
    }

    @Override
    public boolean create(Cart cart) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call create_cart(?, ?, ?)}");
            cs.setInt(1, cart.getIdUser());
            cs.setInt(2, cart.getIdProduct());
            cs.setInt(3, cart.getQuantity());
            result = cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }

    @Override
    public boolean update(Cart cart) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call update_cart(?, ?, ?, ?)}");
            cs.setInt(1, cart.getId());
            cs.setInt(2, cart.getIdUser());
            cs.setInt(3, cart.getIdProduct());
            cs.setInt(4, cart.getQuantity());
            result = cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call delete_cart(?)}");
            cs.setInt(1, id);
            result = cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }
}
