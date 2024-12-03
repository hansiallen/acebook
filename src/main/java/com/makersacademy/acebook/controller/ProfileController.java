package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.CommentsHandler;
import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/profile/{user_id}")
    public String index(Model model, @PathVariable Long user_id) {
        String auth0_id = userRepository.findById(user_id).get().getAuth0Id();
        Iterable<PostWithData> posts = postRepository.findAllWithData(auth0_id);
        model.addAttribute("posts", posts);
        String currentUser = getCurrentUser();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("likesHandler", new LikesHandler(userRepository, currentUser));
        model.addAttribute("commentsHandler", new CommentsHandler(commentRepository, currentUser));
        return "profile/index";
    }
}
