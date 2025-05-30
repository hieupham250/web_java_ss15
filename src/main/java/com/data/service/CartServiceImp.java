package com.data.service;

import com.data.model.Cart;
import com.data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> findByUserId(int id) {
        return cartRepository.findByUserId(id);
    }

    @Override
    public boolean create(Cart cart) {
        return cartRepository.create(cart);
    }

    @Override
    public boolean update(Cart cart) {
        return cartRepository.update(cart);
    }

    @Override
    public boolean delete(int id) {
        return cartRepository.delete(id);
    }
}
