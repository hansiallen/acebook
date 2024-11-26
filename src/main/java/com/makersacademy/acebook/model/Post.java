package com.makersacademy.acebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_id;
    private String content;
    private boolean friends_only;
    private LocalDateTime date_time;

    public Post(String user_id, String content, Boolean friends_only, LocalDateTime date_time) {
        this.user_id = user_id;
        this.content = content;
        this.friends_only = friends_only;
        this.date_time = date_time;
    }

}
