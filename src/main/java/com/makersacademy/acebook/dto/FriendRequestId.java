package com.makersacademy.acebook.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendRequestId implements Serializable {
    @Column(insertable=false, updatable=false)
    private String requesting_user;
    @Column(insertable=false, updatable=false)
    private String requested_user;

    public FriendRequestId(String requesting_user, String requested_user) {
        this.requesting_user = requesting_user;
        this.requested_user = requested_user;
    }

    public FriendRequestId() {}

    public String getRequestingUser() {
        return requesting_user;
    }
    public void setRequestingUser(String requesting_user) {
        this.requesting_user = requesting_user;
    }
    public String getRequestedUser() {
        return requested_user;
    }
    public void setRequestedUser(String requested_user) {
        this.requested_user = requested_user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendRequestId friendRequestId = (FriendRequestId) o;
        return Objects.equals(requesting_user, friendRequestId.requesting_user) &&
                Objects.equals(requested_user, friendRequestId.requested_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requesting_user, requested_user);
    }
}
