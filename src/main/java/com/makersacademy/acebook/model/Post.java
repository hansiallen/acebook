package com.makersacademy.acebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_id;
    private String content;
    private String friends_only;
    private LocalDateTime timestamp;


    public Post(String user_id, String content, String friends_only, LocalDateTime timestamp) {
        this.user_id = user_id;
        this.content = content;
        this.friends_only = friends_only;
        this.timestamp = timestamp;
    }

}
