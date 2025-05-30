package com.data.service;

import com.data.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    boolean create(Review review);
}
