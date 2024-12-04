package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.repository.NotificationRepository;
<<<<<<< HEAD
=======
import com.makersacademy.acebook.repository.UserRepository;
>>>>>>> 34c8dfbfaab6f91288fb3bd2cec1c82a78cc867a
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class NotificationsController {

        @Autowired
        NotificationRepository repository;
<<<<<<< HEAD
=======
        @Autowired
        UserRepository userRepository;
>>>>>>> 34c8dfbfaab6f91288fb3bd2cec1c82a78cc867a

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/notifications")
    public String index(Model model) {
        List<Notification> notifications = repository.findByUserId(getCurrentUser());
        model.addAttribute("notifications", notifications.reversed());

        return "notifications/index";
    }

}
