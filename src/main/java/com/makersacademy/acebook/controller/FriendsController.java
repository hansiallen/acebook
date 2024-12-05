package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.FriendWithData;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FriendsController {
    @Autowired
    FriendRepository repository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private List<FriendWithData> getFriends() {
        List<User> friends = repository.findFriends(getCurrentUser());
        List<FriendWithData> friendsWithData = new ArrayList<>();

        for (User friend : friends) {
            List<User> friendsOfFriend = repository.findFriends(friend.getAuth0Id());
            List<User> mutualFriends = friends.stream()
                    .filter(friendsOfFriend::contains)
                    .collect(Collectors.toList());

            friendsWithData.add(new FriendWithData(friend.getId(), friend.getAuth0Id(), friend.getNickname(), friend.getLastLogin(), mutualFriends));
        }

        return friendsWithData;
    }

    @GetMapping("/friends")
    public String index(Model model) {
        model.addAttribute("friends", getFriends());
        return "friends/index";
    }
}