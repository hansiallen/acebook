package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.User;
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
        return new RedirectView("/posts");
    }
}
