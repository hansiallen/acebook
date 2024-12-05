package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.FriendRequest;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FriendRequestsController {
    @Autowired
    FriendRequestRepository repository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @DeleteMapping("/friend-requests/{requestingUserId}")
    public RedirectView delete(@PathVariable String requestingUserId) {
        FriendRequest request = repository.findByRequestingUserAndRequestedUser(requestingUserId, getCurrentUser()).orElse(null);
        if (request != null) {
            repository.delete(request);
        }
        return new RedirectView("/friends");
    }
}
