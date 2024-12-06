package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.dto.PostWithData;
import com.makersacademy.acebook.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT new com.makersacademy.acebook.dto.PostWithData(" +
            "p.id, p.userId, p.parentId, p.content, p.friendsOnly, p.dateTime, u.nickname, " +
            "CASE WHEN l.userId IS NOT NULL THEN true ELSE false END, " +
            "(SELECT COUNT(ll) FROM Like ll WHERE ll.postId = p.id AND ll.emoji = '\uD83D\uDC4D'))" +
            "FROM Post p " +
            "JOIN User u ON p.userId = u.auth0Id " +
            "LEFT JOIN Like l ON p.id = l.postId AND l.userId = :userId " +
            "WHERE p.parentId IS NULL " +
            "ORDER BY p.dateTime DESC")
    public List<PostWithData> findAllWithData(@Param("userId") String currentUser);
    @Query("SELECT new com.makersacademy.acebook.dto.PostWithData(" +
            "p.id, p.userId, p.parentId, p.content, p.friendsOnly, p.dateTime, u.nickname, " +
            "CASE WHEN l.userId IS NOT NULL THEN true ELSE false END) " +
            "FROM Post p " +
            "JOIN User u ON p.userId = u.auth0Id " +
            "LEFT JOIN Like l ON p.id = l.postId AND l.userId = :userId " +
            "WHERE p.parentId = :postId " +
            "ORDER BY p.dateTime ASC")
    public List<PostWithData> findAllCommentsWithData(@Param("postId") Long postId, @Param("userId") String currentUser);
    public List<Post> findByParentId(Long parentId);
    int countByParentId(Long parentId);
    @Query(value = "WITH RECURSIVE comments AS (" +
            "SELECT id FROM posts WHERE parent_id = :parentId " +
            "UNION ALL " +
            "SELECT p.id " +
            "FROM posts p " +
            "JOIN comments d ON p.parent_id = d.id) " +
            "SELECT COUNT(*) FROM comments", nativeQuery = true)
    int countAllComments(@Param("parentId") Long parentId);
}
