package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Review;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImp implements ReviewRepository {
    @Override
    public List<Review> findAll() {
        Connection conn = null;
        CallableStatement cs = null;
        List<Review> reviews = new ArrayList<Review>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call find_all_review()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getInt("idUser"),
                        rs.getInt("rating"),
                        rs.getString("comment")
                );
                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return reviews;
    }

    @Override
    public boolean create(Review review) {
        Connection conn = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call create_review(?, ?, ?, ?)}");
            cs.setInt(1, review.getIdProduct());
            cs.setInt(2, review.getIdUser());
            cs.setInt(3, review.getRating());
            cs.setString(4, review.getComment());
            result = cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return result;
    }
}
