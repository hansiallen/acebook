package com.makersacademy.acebook.dto;

import com.makersacademy.acebook.repository.CommentRepository;

import java.util.List;

public class CommentsHandler {
    private CommentRepository commentRepository;
    private String currentUser;

    public CommentsHandler(CommentRepository commentRepository, String currentUser) {
        this.commentRepository = commentRepository;
        this.currentUser = currentUser;
    }

    public CommentRepository getCommentRepository() { return commentRepository; }
    public String getCurrentUser() { return currentUser; }

    public void setCommentRepository(CommentRepository commentRepository) { this.commentRepository = commentRepository; }
    public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }

    public List<CommentWithData> getCommentsWithData(Long postId) {
        return commentRepository.findAllCommentsWithData(postId, currentUser);
    }

    public int count(Long postId) { return commentRepository.countByPostId(postId); }
}
