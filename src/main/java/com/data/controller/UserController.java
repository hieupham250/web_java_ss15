package com.data.controller;

import com.data.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @GetMapping("register")
    public String showForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("register")
    public String submitForm(@Valid @ModelAttribute("user") UserDTO user,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        model.addAttribute("user", user);
        return "result";
    }
}
