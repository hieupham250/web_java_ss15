package com.data.service;

import com.data.model.Product;

import java.util.List;


public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    boolean create(Product product);
}
