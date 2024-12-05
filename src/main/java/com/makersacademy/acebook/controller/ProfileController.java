package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dto.CommentsHandler;
import com.makersacademy.acebook.dto.LikesHandler;
import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepositoryWired;

    @Autowired
    LikeRepository likeRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private void getRepliesAndCommentCount(PostWithData post, PostWithData ancestor, String currentUser) {
        List<PostWithData> replies = postRepositoryWired.findAllCommentsWithData(post.getId(), currentUser);
        ancestor.setCommentCount(ancestor.getCommentCount() + replies.size());
        post.setReplies(replies);
        for (PostWithData reply : replies) {
            getRepliesAndCommentCount(reply, ancestor, currentUser);
        }
    }

    @GetMapping("/profile/{user_id}")
    public String index(Model model, @PathVariable Long user_id) {
        String currentUser = getCurrentUser();
        String auth0_id = userRepository.findById(user_id).get().getAuth0Id();
        Iterable<PostWithData> posts = postRepositoryWired.findAllWithData(auth0_id);
        posts.forEach(post -> getRepliesAndCommentCount(post, post, currentUser));
        model.addAttribute("posts", posts);
        CommentsHandler commentsHandler = new CommentsHandler(postRepositoryWired, currentUser);
        System.out.println(commentsHandler.getCommentsWithData(1L));
        for (PostWithData post :posts) {
            System.out.println(post.getId());
        }
        model.addAttribute("post", new Post());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("likesHandler", new LikesHandler(userRepository, currentUser));
        model.addAttribute("commentsHandler", commentsHandler);
        return "profile/index";
    }

    @GetMapping("/profile/me")
    public String myProfile(Model model) {
        return "";
    }
}
