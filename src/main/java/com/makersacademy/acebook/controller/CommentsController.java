package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class CommentsController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

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
        PostWithData postWithData = new PostWithData(
                post.getId(),
                post.getUserId(),
                post.getParentId(),
                post.getContent(),
                false,
                post.getDateTime(),
                nickname,
                false);
        postWithData.setTimeAgo(postWithData.timeSince(LocalDateTime.now()));
        return ResponseEntity.ok(postWithData);
    }
}
