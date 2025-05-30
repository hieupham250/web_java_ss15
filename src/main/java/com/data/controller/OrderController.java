package com.data.controller;

import com.data.model.Cart;
import com.data.model.Order;
import com.data.model.OrderDetail;
import com.data.service.CartService;
import com.data.service.OrderDetailService;
import com.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public String listOrders(@RequestParam("userId") int userId, Model model) {
        List<Order> orders = orderService.findOrdersByUserId(userId);
        System.out.println("orders: " + orders.size());
        model.addAttribute("orders", orders);
        model.addAttribute("userId", userId);
        return "orderlist";
    }
    @GetMapping("/detail")
    public String orderDetail(@RequestParam("orderId") int orderId, Model model) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);

        Order order = orderService.findById(orderId);

        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("order", order);

        return "orderdetail";
    }
    @GetMapping("/checkout")
    public String showCheckoutPage(@RequestParam("userId") int userId, Model model) {
        List<Cart> cartItems = cartService.findByUserId(userId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("order", new Order());
        model.addAttribute("userId", userId);
        return "checkout";
    }

    @PostMapping("/place")
    public String placeOrder(@ModelAttribute("order") Order order,
                             @RequestParam("userId") int userId,
                             Model model) {
        order.setIdUser(userId);
        boolean saved = orderService.create(order);
        if (saved) {
            List<Cart> cartItems = cartService.findByUserId(userId);

            int newOrderId = orderService.findLastInsertId();

            for (Cart cart : cartItems) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderId(newOrderId);
                detail.setProductId(cart.getIdProduct());
                detail.setQuantity(cart.getQuantity());

                orderDetailService.createOrderDetail(detail);
            }

            for (Cart cart : cartItems) {
                cartService.delete(cart.getId());
            }

            return "redirect:/order/success";
        } else {
            model.addAttribute("error", "Không thể đặt hàng.");
            return "checkout";
        }
    }

    @GetMapping("/success")
    public String orderSuccess() {
        return "order_success";
    }
}
