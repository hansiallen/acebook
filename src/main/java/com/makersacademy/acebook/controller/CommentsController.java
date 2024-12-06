package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.NotificationRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import jakarta.persistence.EntityListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Controller
public class CommentsController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity<PostWithData> create(@ModelAttribute Post post) {
        post.setDateTime(LocalDateTime.now());
        postRepository.save(post);
        User user = userRepository.findUserByAuth0Id(post.getUserId()).orElse(null);
        String nickname = (user != null) ? user.getNickname() : "Anonymous user";

        //sends notification
        Optional<Post> originalPost= postRepository.findById(post.getParentId());

        if (originalPost.isPresent()){
            String ownerId = originalPost.get().getUserId();
            Notification newComment = new Notification(ownerId, nickname +" has commented on your post: "+post.getContent(),LocalDateTime.now(),"/posts");
            notificationRepository.save(newComment);
        }

        PostWithData postWithData = new PostWithData(
                post.getId(),
                post.getUserId(),
                post.getParentId(),
                post.getContent(),
                false,
                post.getDateTime(),
                nickname,
                null,
                0L,
                0L,
                0L);
        postWithData.setTimeAgo(postWithData.timeSince(LocalDateTime.now()));

        return ResponseEntity.ok(postWithData);
    }
}
