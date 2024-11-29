package com.makersacademy.acebook.model;

import com.makersacademy.acebook.dto.CommentLikeId;
import jakarta.persistence.*;

@Entity
@Table(name="COMMENT_LIKES")
@IdClass(CommentLikeId.class)
public class CommentLike {
    @Id
    @JoinColumn(name = "user_id")
    private String userId;
    @Id
    @JoinColumn(name = "comment_id")
    private Long commentId;

    public CommentLike(String userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }

    public CommentLike() {}

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }
}
