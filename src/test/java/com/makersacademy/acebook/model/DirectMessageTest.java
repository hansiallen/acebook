package com.makersacademy.acebook.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class DirectMessageTest {
    @Test
    public void sendDirectMessage() {
        DirectMessage directMessage = new DirectMessage("hi how are you", 1L, 2L, LocalDateTime.now(), null);
        Assertions.assertEquals("hi how are you", directMessage.getContent());
        Assertions.assertEquals(1L, directMessage.getSenderId());
        Assertions.assertEquals(2L, directMessage.getReceiverId());
        Assertions.assertNull(directMessage.getReplyTo());
    }
    @Test
    public void sendReplyDirectMessage() {
        DirectMessage originalMessage = new DirectMessage("original message", 1L, 2L, LocalDateTime.now(), null);
        DirectMessage replyMessage = new DirectMessage("reply to message", 2L, 1L, LocalDateTime.now(), originalMessage);

        Assertions.assertEquals("reply to message", replyMessage.getContent());
        Assertions.assertEquals(2L, replyMessage.getSenderId());
        Assertions.assertEquals(1L, replyMessage.getReceiverId());
        Assertions.assertEquals(originalMessage, replyMessage.getReplyTo());
    }
}
