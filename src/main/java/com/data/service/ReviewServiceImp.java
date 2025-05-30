package com.data.service;

import com.data.model.Review;
import com.data.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean create(Review review) {
        return reviewRepository.create(review);
    }
}
