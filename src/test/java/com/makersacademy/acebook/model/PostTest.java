package com.makersacademy.acebook.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class PostTest {
	@Test
	public void postHasContent() {
		Post post = new Post("user_1", null, "hello", true, LocalDateTime.now());
		Assert.assertEquals("hello", post.getContent());
	}
}
