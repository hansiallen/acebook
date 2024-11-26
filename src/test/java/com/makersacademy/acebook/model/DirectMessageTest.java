package com.makersacademy.acebook.model;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDateTime;


public class DirectMessageTest {

    private DirectMessage directMessage = new DirectMessage("hi how are you", "1", "2", LocalDateTime.now());
    @Test
    public void sendDirectMessage() {
        assertThat(directMessage.getContent(), containsString("hi how are you"));
    }

}
