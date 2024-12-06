package com.makersacademy.acebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DIRECT_MESSAGES")
public class DirectMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "reply_to_id", referencedColumnName = "id")
    private DirectMessage replyTo;

    public DirectMessage(String content, Long senderId, Long receiverId, LocalDateTime dateTime, DirectMessage replyTo) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateTime = dateTime;
        this.replyTo = replyTo;
    }
    public DirectMessage(String content, Long senderId, Long receiverId, LocalDateTime dateTime) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateTime = dateTime;
    }

    public DirectMessage() {}

    public Long getId() {return this.id;};
    public void setId(Long id) { this.id = id; }
    public String getContent() {return this.content;}
    public void setContent(String content) { this.content = content; }
    public Long getSenderId() {return this.senderId;}
    public void setSenderId(Long senderId) { this.senderId = senderId; }
    public Long getReceiverId() {return this.receiverId;}
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
    public LocalDateTime getDateTime() {return this.dateTime;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public DirectMessage getReplyTo() {return replyTo; }
    public void setReplyTo(DirectMessage replyTo) {this.replyTo = replyTo; }
}
