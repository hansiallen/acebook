package com.makersacademy.acebook.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;

public class PostWithData {
    private Long id;
    private String userId;
    private Long parentId;
    private String content;
    private Boolean friendsOnly;
    private LocalDateTime dateTime;
    private String nickname;
    private boolean liked;
    private Long likesThumbsUp;
    private List<PostWithData> replies;
    private int commentCount;
    private String timeAgo;

    public PostWithData(Long id, String userId, Long parentId, String content, Boolean friendsOnly, LocalDateTime dateTime, String nickname, boolean liked, Long likesThumbsUp) {
        this.id = id;
        this.userId = userId;
        this.parentId = parentId;
        this.content = content;
        this.friendsOnly = friendsOnly;
        this.dateTime = dateTime;
        this.nickname = nickname;
        this.liked = liked;
        this.likesThumbsUp = likesThumbsUp;
        this.commentCount = 0;
    }

    public PostWithData() {};

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public Long getParentId() { return parentId; }
    public String getContent() { return content; }
    public Boolean getFriendsOnly() { return friendsOnly; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getNickname() { return nickname; }
    public boolean getLiked() { return liked; }
    public Long getLikesThumbsUp() { return likesThumbsUp; }
    public List<PostWithData> getReplies() { return replies; }
    public int getCommentCount() { return commentCount; }
    public String getTimeAgo() { return timeAgo; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    public void setContent(String content) { this.content = content; }
    public void setFriendsOnly(boolean friendsOnly) { this.friendsOnly = friendsOnly; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setLiked(boolean liked) { this.liked = liked; }
    public void setLikesThumbsUp(Long likesThumbsUp) { this.likesThumbsUp = likesThumbsUp; }
    public void setReplies(List<PostWithData> replies) { this.replies = replies; }
    public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }
    public void setCommentCount(int commentCount) { this.commentCount = commentCount; }

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
