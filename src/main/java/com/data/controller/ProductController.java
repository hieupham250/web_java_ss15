package com.data.controller;

import com.data.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private List<Product> productList = new ArrayList<>();

    public ProductController() {
        productList.add(new Product(1, "iPhone 15","iphone vip", 999));
        productList.add(new Product(2, "Samsung S24","samsung vip", 899));
        productList.add(new Product(3, "Xiaomi 13","xiaomi vip", 699));
    }

    @GetMapping("search")
    public String showSearchForm(Model model) {
        model.addAttribute("keyword", "");
        model.addAttribute("results", null);
        return "search";
    }

    @PostMapping("search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> results = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            int finalKeywordAsId = -1;
            results = productList.stream()
                    .filter(p -> p.getName().toLowerCase().contains(lowerKeyword) || p.getId() == finalKeywordAsId)
                    .collect(Collectors.toList());
        } else {
            model.addAttribute("error", "Vui lòng nhập tên hoặc mã sản phẩm.");
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("results", results);
        return "search";
    }

}
