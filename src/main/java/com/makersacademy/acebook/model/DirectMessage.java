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
    private String senderId;
    private String receiverId;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "reply_to_id", referencedColumnName = "id")
    private DirectMessage replyTo;

    public DirectMessage(String content, String senderId, String receiverId, LocalDateTime dateTime, DirectMessage replyTo) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateTime = dateTime;
        this.replyTo = replyTo;
    }

    public DirectMessage() {}

    public Long getId() {return this.id;};
    public void setId(Long id) { this.id = id; }
    public String getContent() {return this.content;}
    public void setContent(String content) { this.content = content; }
    public String getSenderId() {return this.senderId;}
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public String getReceiverId() {return this.receiverId;}
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }
    public LocalDateTime getDateTime() {return this.dateTime;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public DirectMessage getReplyTo() {return replyTo; }
    public void setReplyTo(DirectMessage replyTo) {this.replyTo = replyTo; }
}
