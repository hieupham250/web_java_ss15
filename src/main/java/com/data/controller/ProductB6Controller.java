package com.data.controller;

import com.data.model.Product;
import com.data.model.Review;
import com.data.service.ProductService;
import com.data.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductB6Controller {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping()
    public String showProductList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "listProduct";
    }

    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        List<Review> reviews = reviewService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews.stream().filter(r -> r.getIdProduct() == id));
        model.addAttribute("reviewForm", new Review());
        return "productDetail";
    }

    @PostMapping("/review/{id}")
    public String addReview(@PathVariable int id, @ModelAttribute("reviewForm") Review review) {
        review.setIdProduct(id);
        review.setIdUser(1);
        reviewService.create(review);
        return "redirect:/product/detail/" + id;
    }
}
