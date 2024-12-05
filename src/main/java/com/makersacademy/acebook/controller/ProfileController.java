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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepositoryWired;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    ProfileRepository profileRepository;

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
        // Set isYourProfile to true
        model.addAttribute("isYourProfile", false);

        // Stuff for rendering user's posts
        String currentUser = getCurrentUser();
        String auth0_id = userRepository.findById(user_id).get().getAuth0Id();
        Iterable<PostWithData> posts = postRepositoryWired.findAllWithData(auth0_id);
        posts.forEach(post -> getRepliesAndCommentCount(post, post, currentUser));
        model.addAttribute("posts", posts);
        CommentsHandler commentsHandler = new CommentsHandler(postRepositoryWired, currentUser);
        model.addAttribute("post", new Post());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("likesHandler", new LikesHandler(userRepository, currentUser));
        model.addAttribute("commentsHandler", commentsHandler);

        // Stuff for profile info
        String userNickname = userRepository.findById(user_id).get().getNickname();
        model.addAttribute("nickname", userNickname);
        Profile profile = profileRepository.findById(user_id).get();
        model.addAttribute("profile", profile);
        return "profile/index";
    }

    @GetMapping("/profile/me")
    public String myProfile(Model model) {
        // Set isYourProfile to true
        model.addAttribute("isYourProfile", true);

        // Get current user's user_id
        Long user_id = userRepository.findUserByAuth0Id(getCurrentUser()).get().getId();

        // Stuff for rendering user's posts
        String currentUser = getCurrentUser();
        String auth0_id = userRepository.findById(user_id).get().getAuth0Id();
        Iterable<PostWithData> posts = postRepositoryWired.findAllWithData(auth0_id);
        posts.forEach(post -> getRepliesAndCommentCount(post, post, currentUser));
        model.addAttribute("posts", posts);
        CommentsHandler commentsHandler = new CommentsHandler(postRepositoryWired, currentUser);
        model.addAttribute("post", new Post());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("likesHandler", new LikesHandler(userRepository, currentUser));
        model.addAttribute("commentsHandler", commentsHandler);

        // Stuff for profile info
        String userNickname = userRepository.findById(user_id).get().getNickname();
        model.addAttribute("nickname", userNickname);
        Profile profile = profileRepository.findById(user_id).get();
        model.addAttribute("profile", profile);
        return "profile/index";
    }
}
