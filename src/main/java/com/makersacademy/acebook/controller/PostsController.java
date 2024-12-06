package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.CommentsHandler;
import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Profile;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.ProfileRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    ProfileRepository profileRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private void getRepliesAndCommentCount(PostWithData post, PostWithData ancestor, String currentUser) {
        List<PostWithData> replies = repository.findAllCommentsWithData(post.getId(), currentUser);
        ancestor.setCommentCount(ancestor.getCommentCount() + replies.size());
        post.setReplies(replies);
        Optional<Profile> profile = profileRepository.findById(userRepository.findUserByAuth0Id(post.getUserId()).get().getId());
        System.out.println(profile.get().getProfilePictureUrl());
        post.setProfile(profile.get().getProfilePictureUrl());
        System.out.println(post.getProfile());
        for (PostWithData reply : replies) {
            getRepliesAndCommentCount(reply, ancestor, currentUser);
        }
    }

    @GetMapping("/posts")
    public String index(Model model) {
        String currentUser = getCurrentUser();
        List<PostWithData> posts = repository.findAllWithData(currentUser);
        posts.forEach(post -> getRepliesAndCommentCount(post, post, currentUser));
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("likesHandler", new LikesHandler(userRepository, currentUser));

        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.setDateTime(LocalDateTime.now());
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody String newContent) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            post.setContent(newContent);
            repository.save(post);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            repository.delete(post);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
