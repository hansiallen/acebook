package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FriendsController {
    @Autowired
    FriendRepository repository;

    @GetMapping("/friends")
    public String index(Model model) {
        return "friends/index";
    }

    @GetMapping("/friends-test")
    @ResponseBody
    public List<Friend> test(Model model) {
        String userA = "auth0|6748e51bcc1b98c050fe67e2";
        String userB = "auth0|674634d86a29392e14307373";
        List<Friend> commonFriends = repository.findCommonFriends(userA, userB);
        return commonFriends;
    }
}