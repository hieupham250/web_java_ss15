package com.data.repository;

import com.data.model.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> findAll();
    List<Cart> findByUserId(int id);
    boolean create(Cart cart);
    boolean update(Cart cart);
    boolean delete(int id);

}
