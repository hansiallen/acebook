package com.makersacademy.acebook.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "auth0_id")
    private String auth0Id;
    private boolean enabled;
    private String nickname;
    private LocalDateTime lastLogin;

    public User(String auth0Id, String nickname, LocalDateTime lastLogin) {
        this.auth0Id = auth0Id;
        this.enabled = TRUE;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
    }

    public User(String auth0Id, boolean enabled, String nickname, LocalDateTime lastLogin) {
        this.auth0Id = auth0Id;
        this.enabled = enabled;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
    }

    // everything beneath this line: I know. But lombok isn't handling these setters correctly.

    public User() {
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
 
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
