package com.makersacademy.acebook.dto;

public class LikesControllerResponse {
    private String likedString;
    private String emoji;

    public LikesControllerResponse(String likedString, String emoji) {
        this.likedString = likedString;
        this.emoji = emoji;
    }

    public LikesControllerResponse() {}

    public String getLikedString() { return likedString; }
    public String getEmoji() { return emoji; }
    public void setLikedString(String likedString) { this.likedString = likedString; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
}
