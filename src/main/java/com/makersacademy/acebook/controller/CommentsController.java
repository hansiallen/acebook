package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.CommentWithData;
import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class CommentsController {
    @Autowired
    CommentRepository repository;
    @Autowired
    UserRepository userRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity<CommentWithData> create(@ModelAttribute Comment comment) {
        comment.setDateTime(LocalDateTime.now());
        repository.save(comment);
        User user = userRepository.findUserByAuth0Id(comment.getUserId()).orElse(null);
        String nickname = (user != null) ? user.getNickname() : "Anonymous user";
        CommentWithData commentWithData = new CommentWithData(
            comment.getId(),
            comment.getUserId(),
            comment.getPostId(),
            comment.getComments(),
            comment.getDateTime(),
            nickname,
            false);
        commentWithData.setTimeAgo(commentWithData.timeSince(LocalDateTime.now()));
        return ResponseEntity.ok(commentWithData);
    }
}
