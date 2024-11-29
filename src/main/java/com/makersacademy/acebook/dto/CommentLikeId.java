package com.makersacademy.acebook.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CommentLikeId {
    @Column(name = "user_id", insertable=false, updatable=false)
    private String userId;
    @Column(name = "comment_id", insertable=false, updatable=false)
    private Long commentId;

    public CommentLikeId(String userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }

    public CommentLikeId() {}

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }
}