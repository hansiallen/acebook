package com.makersacademy.acebook.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class CommentWithData {
    private Long id;
    private String userId;
    private Long postId;
    private String content;
    private LocalDateTime dateTime;
    private String nickname;
    private boolean liked;
    private String timeAgo;

    public CommentWithData(Long id, String userId, Long postId, String content, LocalDateTime dateTime, String nickname, boolean liked) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.dateTime = dateTime;
        this.nickname = nickname;
        this.liked = liked;
    }

    public CommentWithData() {}

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public Long getPostId() { return postId; }
    public String getContent() { return content; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getNickname() { return nickname; }
    public boolean getLiked() { return liked; }
    public String getTimeAgo() { return timeAgo; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setPostId(Long postId) { this.postId = postId; }
    public void setContent(String content) { this.content = content; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setLiked(boolean liked) { this.liked = liked; }
    public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }

    public String timeSince(LocalDateTime currentTime) {
        Duration duration = Duration.between(dateTime, currentTime);
        long minutes = duration.toMinutes();

        if (minutes < 1) {
            return "just now";
        } else if (minutes < 2) {
            return "a minute ago";
        } else if (minutes < 60) {
            return minutes + " minutes ago";
        } else if (minutes < 120) {
            return "an hour ago";
        } else if (minutes < 1440) {
            return (minutes / 60) + " hours ago";
        } if (minutes < 2880) {
            return "yesterday";
        } else {
            return (minutes / 1440) + " days ago";
        }
    }
}
