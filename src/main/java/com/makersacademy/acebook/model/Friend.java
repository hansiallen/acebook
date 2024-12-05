package com.makersacademy.acebook.model;

import com.makersacademy.acebook.dto.FriendId;
import jakarta.persistence.*;

@Entity
@Table(name = "FRIENDS")
@IdClass(FriendId.class)
public class Friend {
    @Id
    @JoinColumn(name = "user_a")
    private String userA;
    @JoinColumn(name = "user_b")
    private String userB;

    public Friend(String userA, String userB) {
        this.userA = userA;
        this.userB = userB;
    }

    public Friend() {}

    public String getUserA() {return this.userA;}
    public String getUserB() {return this.userB;}
    public void setUserA(String userA) {this.userA = userA;}
    public void setUserB(String userB) {this.userB = userB;}
}
