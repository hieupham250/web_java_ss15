package com.data.controller;

import com.data.model.Cart;
import com.data.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String listCarts(Model model) {
        List<Cart> carts = cartService.findAll();
        model.addAttribute("carts", carts);
        return "listCart";
    }

    @GetMapping("/user/{id}")
    public String listCartByUser(@PathVariable("id") int userId, Model model) {
        List<Cart> carts = cartService.findByUserId(userId);
        model.addAttribute("carts", carts);
        return "list_by_user";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("cart", new Cart());
        return "addCart";
    }

    @PostMapping("/add")
    public String saveCart(@ModelAttribute("cart") Cart cart) {
        cartService.create(cart);
        return "redirect:/cart";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Cart cart = cartService.findAll().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (cart == null) {
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);
        return "editCart";
    }

    @PostMapping("/update")
    public String updateCart(@ModelAttribute("cart") Cart cart) {
        cartService.update(cart);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable("id") int id) {
        cartService.delete(id);
        return "redirect:/cart";
    }
}
