package com.makersacademy.acebook.dto;

import com.makersacademy.acebook.repository.PostRepository;

import java.util.List;

public class CommentsHandler {
    private PostRepository postRepository;
    private String currentUser;

    public CommentsHandler(PostRepository postRepository, String currentUser) {
        this.postRepository = postRepository;
        this.currentUser = currentUser;
    }

    public PostRepository getPostRepository() { return postRepository; }
    public String getCurrentUser() { return currentUser; }

    public void setPostRepository(PostRepository commentRepository) { this.postRepository = commentRepository; }
    public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }

    public List<PostWithData> getCommentsWithData(Long postId) {
        return postRepository.findAllCommentsWithData(postId, currentUser);
    }

    public int count(Long parentId) { return postRepository.countByParentId(parentId); }
}