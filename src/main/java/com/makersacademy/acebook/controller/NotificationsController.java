package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class NotificationsController {

        @Autowired
        NotificationRepository repository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/notifications")
    public String index(Model model) {
//        List<Notification> notifications = repository.findAllWithData(currentUser);

//        model.addAttribute("notifications", notifications);

        return "notifications/index";
    }
}
