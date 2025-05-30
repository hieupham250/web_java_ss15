package com.data.repository;

import com.data.model.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findAll();
    boolean create(Review review);
}
