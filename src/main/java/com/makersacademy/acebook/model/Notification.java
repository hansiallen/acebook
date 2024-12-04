package com.makersacademy.acebook.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String message;
    private LocalDateTime dateTime;
    private  String link;

    public Notification() {
    }

    public Notification(String userId, String message, LocalDateTime dateTime , String link) {
        this.userId = userId;
        this.message = message;
        this.dateTime = dateTime;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String timeSince() {
        Duration duration = Duration.between(dateTime, LocalDateTime.now());
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
