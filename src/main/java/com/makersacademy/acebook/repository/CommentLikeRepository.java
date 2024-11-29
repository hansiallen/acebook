package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.CommentLike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLike, Long> {
    List<CommentLike> findByUserId(String userId);
    Optional<CommentLike> findByUserIdAndCommentId(String userId, Long commentId);
}