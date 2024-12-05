package com.makersacademy.acebook.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendId {
    @Column(name = "user_a", insertable=false, updatable=false)
    private String userA;
    @Column(name = "user_b", insertable=false, updatable=false)
    private String userB;

    public FriendId(String userA, String userB) {
        this.userA = userA;
        this.userB = userB;
    }

    public FriendId() {}

    public String getUserA() { return userA; }
    public void setUserA(String userA) { this.userA = userA; }
    public String getUserB() { return userB; }
    public void setUserB(String userB) { this.userB = userB; }
}