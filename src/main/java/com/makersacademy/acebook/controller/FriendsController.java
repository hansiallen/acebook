package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.FriendWithData;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.FriendRequest;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.FriendRequestRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FriendsController {
    @Autowired
    FriendRepository repository;
    @Autowired
    FriendRequestRepository friendRequestRepository;
    @Autowired
    UserRepository userRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private List<User> getMutualFriends(User user) {
        List<User> friends = repository.findFriends(getCurrentUser());
        List<User> friendsOfFriends = repository.findFriends(user.getAuth0Id());
        return friends.stream()
                .filter(friendsOfFriends::contains)
                .collect(Collectors.toList());
    }

    private List<FriendWithData> getFriends() {
        List<User> friends = repository.findFriends(getCurrentUser());
        List<FriendWithData> friendsWithData = new ArrayList<>();

        for (User friend : friends) {
            friendsWithData.add(
                new FriendWithData(
                    friend.getId(),
                    friend.getAuth0Id(),
                    friend.getNickname(),
                    friend.getLastLogin(),
                    getMutualFriends(friend))
            );
        }

        return friendsWithData;
    }

    private List<FriendWithData> getFriendRequests() {
        List<FriendRequest> requests = friendRequestRepository.findByRequestedUser(getCurrentUser());
        List<FriendWithData> friendRequestsWithData = new ArrayList<>();

        for (FriendRequest friendRequest : requests) {
            User requestingUser = userRepository.findByAuth0Id(friendRequest.getRequestingUser());
            friendRequestsWithData.add(new FriendWithData(
                requestingUser.getId(),
                requestingUser.getAuth0Id(),
                requestingUser.getNickname(),
                requestingUser.getLastLogin(),
                getMutualFriends(requestingUser))
            );
        }

        return friendRequestsWithData;
    }

    @GetMapping("/friends")
    public String index(Model model) {
        model.addAttribute("friends", getFriends());
        model.addAttribute("friend", new Friend());
        model.addAttribute("friendRequests", getFriendRequests());
        return "friends/index";
    }

    @PostMapping("/friends")
    public RedirectView create(@ModelAttribute Friend friend) {
        repository.save(friend);
        Optional<FriendRequest> friendRequest = friendRequestRepository.findByRequestingUserAndRequestedUser(friend.getUserA(), friend.getUserB());
        friendRequest.ifPresent(request -> {
            friendRequestRepository.delete(request);
        });
        return new RedirectView("/friends");
    }
}