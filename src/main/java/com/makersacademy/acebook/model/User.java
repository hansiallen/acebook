package com.makersacademy.acebook.model;

import jakarta.persistence.*;

import javassist.LoaderClassPath;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String userId;
    private boolean enabled;
    private String nickname;
    private LocalDateTime lastLogin;

    public User(String userId, String nickname, LocalDateTime lastLogin) {
        this.userId = userId;
        this.enabled = TRUE;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
    }

    public User(String userId, boolean enabled, String nickname, LocalDateTime lastLogin) {
        this.userId = userId;
        this.enabled = enabled;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
    }

    public User() {
        // Default constructor required by Hibernate
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
  
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
