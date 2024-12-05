package com.makersacademy.acebook.model;

import com.makersacademy.acebook.dto.FriendRequestId;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FRIEND_REQUESTS")
@IdClass(FriendRequestId.class)
public class FriendRequest {
    @Id
    @JoinColumn(name = "requesting_user")
    private String requestingUser;
    @Id
    @JoinColumn(name = "requested_user")
    private String requestedUser;

    public FriendRequest(String requestingUser, String requestedUser) {
        this.requestingUser = requestingUser;
        this.requestedUser = requestedUser;
    }

    public FriendRequest() {}

    public String getRequestingUser() {return this.requestingUser;}
    public String getRequestedUser() {return this.requestedUser;}
    public void setRequestingUser(String requestingUser) {this.requestingUser = requestingUser;}
    public void setRequestedUser(String requestedUser) {this.requestedUser = requestedUser;}
}
