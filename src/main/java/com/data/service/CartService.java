package com.data.service;

import com.data.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
    List<Cart> findByUserId(int id);
    boolean create(Cart cart);
    boolean update(Cart cart);
    boolean delete(int id);
}
