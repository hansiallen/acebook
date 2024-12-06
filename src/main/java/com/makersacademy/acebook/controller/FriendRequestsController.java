package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.FriendRequest;
import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRequestRepository;
import com.makersacademy.acebook.repository.NotificationRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class FriendRequestsController {
    @Autowired
    FriendRequestRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;


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

    @PostMapping("/friend-requests")
    public RedirectView create(@ModelAttribute FriendRequest friendRequest) {
        repository.save(friendRequest);

        //sends notification

        String getCurrentUser=SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAuth0Id(getCurrentUser);
        Optional<User> receiver= userRepository.findUserByAuth0Id(friendRequest.getRequestedUser());
        if (receiver.isPresent()){
            String ownerId = receiver.get().getAuth0Id();
            Notification newFriendRequest = new Notification(ownerId, user.getNickname() +" sent you a friend request", LocalDateTime.now(),"/friends");
            notificationRepository.save(newFriendRequest);
        }

        return new RedirectView("/friends");
    }
}
