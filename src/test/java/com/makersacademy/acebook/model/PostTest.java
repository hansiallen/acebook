package com.makersacademy.acebook.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostTest {
	private Post post = new Post("user_1", "hello", true, LocalDateTime.now());
	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}
}
