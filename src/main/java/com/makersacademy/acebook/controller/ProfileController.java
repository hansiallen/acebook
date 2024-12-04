package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.CommentsHandler;
import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Post;
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

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/profile/{user_id}")
    public String index(Model model, @PathVariable Long user_id) {
        String auth0_id = userRepository.findById(user_id).get().getAuth0Id();
        Iterable<PostWithData> posts = postRepository.findAllWithData(auth0_id);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        String currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("likesHandler", new LikesHandler(userRepository, currentUser));
        return "profile/index";
    }

    @GetMapping("/profile/me")
    public String myProfile(Model model) {
        return "";
    }
}
