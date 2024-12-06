package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.DirectMessage;
import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.DirectMessageRepository;
import com.makersacademy.acebook.repository.NotificationRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class DirectMessagesController {

    @Autowired
    private DirectMessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;


    private Long getSenderUserId() {
        String getCurrentUser=SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAuth0Id(getCurrentUser);
        return user.getId();
    }
    @GetMapping("/directMessages")
    public String getConversationsList(Model model) {
        Long currentUserId = getSenderUserId();
        List<DirectMessage> sentMessages = messageRepository.findByReceiverIdOrSenderId(currentUserId, currentUserId);
        List<Long> receiverIds = new ArrayList<>();

        for (DirectMessage message: sentMessages) {
            if (!receiverIds.contains(message.getReceiverId()) && !message.getReceiverId().equals(currentUserId)) {
                receiverIds.add(message.getReceiverId());
            }

            if (!receiverIds.contains(message.getSenderId()) && !message.getSenderId().equals(currentUserId)) {
                receiverIds.add(message.getSenderId());
            }

        }
        if (receiverIds.isEmpty()) {
            model.addAttribute("message", "No conversations found.");
            return "direct_messages/conversations";
        }
//        need to get th users corresponding nickname and add to the model
        Iterable<User> conversedUsers = userRepository.findAllById(receiverIds);
        model.addAttribute("conversedUsers", conversedUsers);
        return "direct_messages/conversations";
    }


    @GetMapping("/directMessages/{userId}")
    public String getConversation(@PathVariable Long userId, Model model) {
        Long currentUserId = getSenderUserId();
        List<DirectMessage> conversation = messageRepository.findBySenderIdAndReceiverId(currentUserId, userId);
        conversation.addAll(messageRepository.findByReceiverIdAndSenderId(currentUserId, userId));
        conversation.sort(Comparator.comparing(DirectMessage::getDateTime));
        Optional<User> otherUser = userRepository.findById(userId);
        otherUser.ifPresent(user -> model.addAttribute("user", user));
        System.out.println(conversation);
        model.addAttribute("conversation", conversation);
        model.addAttribute("directMessage", new DirectMessage());
        model.addAttribute("senderId", currentUserId);
        model.addAttribute("receiverId", userId);
        return "direct_messages/index";
    }
    @PostMapping("/sendMessage")
    @ResponseBody
    public RedirectView sendMessage(@RequestParam Long receiverId, @RequestParam String content, @RequestParam(required = false) Long replyToId) {
        DirectMessage replyTo = replyToId != null ? messageRepository.findById(replyToId).orElse(null) : null;
        DirectMessage message = new DirectMessage(content, getSenderUserId(), receiverId, LocalDateTime.now(), replyTo);
        message = messageRepository.save(message);

        //sends notification

        String getCurrentUser=SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAuth0Id(getCurrentUser);
        Optional<User> receiver= userRepository.findById(message.getReceiverId());
        if (receiver.isPresent()){
            String ownerId = receiver.get().getAuth0Id();
            Notification newComment = new Notification(ownerId, user.getNickname() +" has sent you a message: "+message.getContent(),LocalDateTime.now(),"/directMessages/" + getSenderUserId()+ "#bottom");
            notificationRepository.save(newComment);
        }
        return new RedirectView("/directMessages/" + receiverId+ "#bottom");
    }

}
