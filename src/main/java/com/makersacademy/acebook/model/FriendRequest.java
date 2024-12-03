package com.makersacademy.acebook.model;

import com.makersacademy.acebook.dto.FriendRequestId;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FRIEND_REQUESTS")
public class FriendRequest {
    @EmbeddedId
    private FriendRequestId id;
    private String requesting_user;
    private String requested_user;

    public FriendRequest(String requesting_user, String requested_user) {
        this.requesting_user = requesting_user;
        this.requested_user = requested_user;
    }

    public FriendRequest() {}

    public FriendRequestId getId() {return id;}
    public void setId(FriendRequestId id) {this.id = id;}
    public String getRequesting_user() {return this.requesting_user;}
    public String getRequested_user() {return this.requested_user;}
    public void setRequesting_user(String requesting_user) {this.requesting_user = requesting_user;}
    public void setRequested_user(String requested_user) {this.requested_user = requested_user;}
}
