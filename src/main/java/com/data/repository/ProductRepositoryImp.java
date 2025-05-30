package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement cs = null;
        List<Product> products = new ArrayList<Product>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call find_all_product()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Connection conn = null;
        CallableStatement cs = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call find_product_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return product;
    }

    @Override
    public boolean create(Product product) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call create_product(?, ?, ?)}");
            cs.setString(1, product.getName());
            cs.setString(2, product.getDescription());
            cs.setDouble(3, product.getPrice());
            result = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }
}
