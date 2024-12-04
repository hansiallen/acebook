package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.NotificationRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
public class NotificationsController {

        @Autowired
        NotificationRepository repository;
        @Autowired
        UserRepository userRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/notifications")
    public String index(Model model) {
        Optional<User> userInDB = userRepository.findUserByAuth0Id(getCurrentUser());
        if (userInDB.isPresent()) {
            userInDB.get().setLastCheckedNotifications(LocalDateTime.now());
            userRepository.save(userInDB.get());
        }
        
        
        List<Notification> notifications = repository.findByUserId(getCurrentUser());
        model.addAttribute("notifications", notifications.reversed());

        return "notifications/index";
    }

}
