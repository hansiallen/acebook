package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.DirectMessage;
import com.makersacademy.acebook.repository.DirectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DirectMessagesController {

    @Autowired
    private DirectMessageRepository messageRepository;

    @GetMapping("/conversations{userId}")
    public String getConversation(@PathVariable String userId, Model model) {
        String otherUserId = "otherUser"; // Replace this with actual logic or pass it from the view
        List<DirectMessage> conversation = messageRepository.findBySenderIdAndReceiverId(userId, otherUserId);
        conversation.addAll(messageRepository.findByReceiverIdAndSenderId(userId, otherUserId));
        model.addAttribute("conversation", conversation);
        return "direct_messages/index";
    }
    @PostMapping("/sendMessage")
    @ResponseBody
    public DirectMessage sendMessage(@RequestParam String receiverId, @RequestParam String content, @RequestParam(required = false) Long replyToId) {
        DirectMessage replyTo = replyToId != null ? messageRepository.findById(replyToId).orElse(null) : null;
        DirectMessage message = new DirectMessage(content, "senderUserId", receiverId, LocalDateTime.now(), replyTo);  // Hardcode senderId for now
        return messageRepository.save(message);
    }
}
