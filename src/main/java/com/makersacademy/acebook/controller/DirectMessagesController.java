package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.DirectMessage;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.DirectMessageRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class DirectMessagesController {

    @Autowired
    private DirectMessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;


    private Long getSenderUserId() {
        String getCurrentUser=SecurityContextHolder.getContext().getAuthentication().getName();;
        User user = userRepository.findByAuth0Id(getCurrentUser);
        return user.getId();
    }


    @GetMapping("/conversations/{userId}")
    public String getConversation(@PathVariable Long userId, Model model) {
        Long currentUserId = getSenderUserId();
        List<DirectMessage> conversation = messageRepository.findBySenderIdAndReceiverId(currentUserId, userId);
        conversation.addAll(messageRepository.findByReceiverIdAndSenderId(currentUserId, userId));
        model.addAttribute("conversation", conversation);
        model.addAttribute("directMessage", new DirectMessage());
        model.addAttribute("senderId", currentUserId);
        model.addAttribute("receiverId", userId);
        return "direct_messages/index";
    }
    @PostMapping("/sendMessage")
    @ResponseBody
    public DirectMessage sendMessage(@RequestParam Long receiverId, @RequestParam String content, @RequestParam(required = false) Long replyToId) {
        DirectMessage replyTo = replyToId != null ? messageRepository.findById(replyToId).orElse(null) : null;
        DirectMessage message = new DirectMessage(content, getSenderUserId(), receiverId, LocalDateTime.now(), replyTo);// Hardcode senderId for now
        return messageRepository.save(message);
    }
}
