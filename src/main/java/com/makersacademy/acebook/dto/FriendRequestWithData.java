package com.makersacademy.acebook.dto;

import com.makersacademy.acebook.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class FriendRequestWithData {
    private Long id;
    private String auth0Id;
    private String nickname;
    private LocalDateTime lastLogin;
    private List<User> mutualFriends;
}
