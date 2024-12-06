package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.LikesControllerResponse;
import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LikesController {
    @Autowired
    LikeRepository repository;
    @Autowired
    UserRepository userRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/likes/{postId}")
    @ResponseBody
    public String create(@PathVariable Long postId) {
        String currentUser = getCurrentUser();
        repository.save(new Like(currentUser, postId, "\uD83D\uDC4D"));
        LikesHandler likesHandler = new LikesHandler(userRepository, currentUser);
        return likesHandler.getLikedString(postId, "\uD83D\uDC4D", false);
    }

    @PostMapping("/likes/laughing/{postId}")
    @ResponseBody
    public String createLaughing(@PathVariable Long postId) {
        String currentUser = getCurrentUser();
        repository.save(new Like(currentUser, postId, "\uD83D\uDE02"));
        LikesHandler likesHandler = new LikesHandler(userRepository, currentUser);
        return likesHandler.getLikedString(postId, "\uD83D\uDE02", false);
    }

    @PostMapping("/likes/shocked/{postId}")
    @ResponseBody
    public String createShocked(@PathVariable Long postId) {
        String currentUser = getCurrentUser();
        repository.save(new Like(currentUser, postId, "\uD83D\uDE32"));
        LikesHandler likesHandler = new LikesHandler(userRepository, currentUser);
        return likesHandler.getLikedString(postId, "\uD83D\uDE32", false);
    }

    @DeleteMapping("/likes/{postId}")
    @ResponseBody
    public LikesControllerResponse delete(@PathVariable Long postId) {
        String currentUser = getCurrentUser();
        Like like = repository.findByUserIdAndPostId(currentUser, postId).orElse(null);
        String emoji = like.getEmoji();
        repository.delete(like);

        LikesHandler likesHandler = new LikesHandler(userRepository, currentUser);
        String likedString = likesHandler.getLikedString(postId, null, false);

        return new LikesControllerResponse(likedString, emoji);
    }
}
