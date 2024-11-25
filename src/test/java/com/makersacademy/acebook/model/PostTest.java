package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PostTest {

	private Post post = new Post("user_1", "hello", "friends_only", LocalDateTime.of(2024, 12, 25, 10, 30));

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

}
