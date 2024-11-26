package com.makersacademy.acebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectMessagesController {
    @GetMapping("/conversations")
    public String index(Model model) {
        return "direct_messages/index";
    }
}
