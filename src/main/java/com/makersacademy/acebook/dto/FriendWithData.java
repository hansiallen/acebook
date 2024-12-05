package com.makersacademy.acebook.dto;

import com.makersacademy.acebook.model.User;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public class FriendWithData {
    private Long id;
    private String auth0Id;
    private String nickname;
    private LocalDateTime lastLogin;
    private List<User> mutualFriends;

    public FriendWithData(Long id, String auth0Id, String nickname, LocalDateTime lastLogin, List<User> mutualFriends) {
        this.id = id;
        this.auth0Id = auth0Id;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
        this.mutualFriends = mutualFriends;
    }

    public FriendWithData() {}

    public Long getId() { return id; }
    public String getAuth0Id() { return auth0Id; }
    public String getNickname() { return nickname; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public List<User> getMutualFriends() { return mutualFriends; }

    public void setId(Long id) { this.id = id; }
    public void setAuth0Id(String auth0Id) { this.auth0Id = auth0Id; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    public void setMutualFriends(List<User> mutualFriends) { this.mutualFriends = mutualFriends; }
}