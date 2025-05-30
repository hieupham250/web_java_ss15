package com.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("messages", "Chào mừng bạn đến với bình nguyên vô tận!!!!!!!!!!");
        return "hello";
    }
}
