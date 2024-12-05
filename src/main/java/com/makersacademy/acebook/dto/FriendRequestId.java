package com.makersacademy.acebook.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendRequestId {
    @Column(name = "requesting_user", insertable=false, updatable=false)
    private String requestingUser;
    @Column(name = "requested_user", insertable=false, updatable=false)
    private String requestedUser;

    public FriendRequestId(String requestingUser, String requestedUser) {
        this.requestingUser = requestingUser;
        this.requestedUser = requestedUser;
    }

    public FriendRequestId() {}

    public String getRequestingUser() {
        return requestingUser;
    }
    public void setRequestingUser(String requestingUser) {
        this.requestingUser = requestingUser;
    }
    public String getRequestedUser() {
        return requestedUser;
    }
    public void setRequestedUser(String requestedUser) {
        this.requestedUser = requestedUser;
    }
}
