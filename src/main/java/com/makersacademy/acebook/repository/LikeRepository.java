package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Long> {
    List<Like> findByUserId(String userId);
    Optional<Like> findByUserIdAndPostId(String userId, Long postId);
    List<Like> findByPostIdAndEmoji(Long postId, String emoji);
}
