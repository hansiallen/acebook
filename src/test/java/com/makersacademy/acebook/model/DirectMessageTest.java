package com.makersacademy.acebook.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class DirectMessageTest {
    @Test
    public void sendDirectMessage() {
        DirectMessage directMessage = new DirectMessage("hi how are you", "user1", "user2", LocalDateTime.now(), null);
        Assertions.assertEquals("hi how are you", directMessage.getContent());
        Assertions.assertEquals("user1", directMessage.getSenderId());
        Assertions.assertEquals("user2", directMessage.getReceiverId());
        Assertions.assertNull(directMessage.getReplyTo());
    }
    @Test
    public void sendReplyDirectMessage() {
        DirectMessage originalMessage = new DirectMessage("original message", "user1", "user2", LocalDateTime.now(), null);
        DirectMessage replyMessage = new DirectMessage("reply to message", "user2", "user1", LocalDateTime.now(), originalMessage);

        Assertions.assertEquals("reply to message", replyMessage.getContent());
        Assertions.assertEquals("user2", replyMessage.getSenderId());
        Assertions.assertEquals("user1", replyMessage.getReceiverId());
        Assertions.assertEquals(originalMessage, replyMessage.getReplyTo());
    }
}
