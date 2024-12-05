package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Profile;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.ProfileRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@RestController
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/users/after-login")
    public RedirectView afterLogin() {
        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String auth0Id = (String) principal.getAttributes().get("sub");
        String nickname = (String) principal.getAttributes().get("nickname");
        userRepository
                .findUserByAuth0Id(auth0Id)
                .map(existingUser -> {
                    existingUser.setNickname(nickname);
                    existingUser.setLastLogin(LocalDateTime.now());
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> userRepository.save(new User(auth0Id, nickname, LocalDateTime.now())));

        // Get current user's user_id
        Long user_id = userRepository.findUserByAuth0Id(getCurrentUser()).get().getId();

        if (profileRepository.findById(user_id).stream().count() < 1) {
            Profile profile = new Profile(user_id);
            profileRepository.save(profile);
            return new RedirectView("/profile/edit");
        }

        return new RedirectView("/posts");
    }
}
