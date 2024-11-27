package com.makersacademy.acebook.dto;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendId implements Serializable {

    private String userA;
    private String userB;

    public FriendId() {}

    public FriendId(String userA, String userB) {
        this.userA = userA;
        this.userB = userB;
    }

    // Getters and setters
    public String getUserA() {
        return userA;
    }

    public void setUserA(String userA) {
        this.userA = userA;
    }

    public String getUserB() {
        return userB;
    }

    public void setUserB(String userB) {
        this.userB = userB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendId friendID = (FriendId) o;
        return userA.equals(friendID.userA) && userB.equals(friendID.userB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userA, userB);
    }
}