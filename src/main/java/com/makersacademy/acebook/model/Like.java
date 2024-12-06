package com.makersacademy.acebook.model;

import com.makersacademy.acebook.dto.LikeId;
import jakarta.persistence.*;

@Entity
@Table(name="LIKES")
@IdClass(LikeId.class)
public class Like {
    @Id
    @JoinColumn(name = "user_id")
    private String userId;
    @Id
    @JoinColumn(name = "post_id")
    private Long postId;
    private String emoji;

    public Like(String userId, Long postId, String emoji) {
        this.userId = userId;
        this.postId = postId;
        this.emoji = emoji;
    }

    public Like() {}

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
}
