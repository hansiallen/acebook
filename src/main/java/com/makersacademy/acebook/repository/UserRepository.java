package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findUserByAuth0Id(String auth0Id);
    @Query("SELECT u FROM User u JOIN Like l ON u.auth0Id = l.userId WHERE l.postId = :postId")
    List<User> findUsersWhoLikedPost(@Param("postId") Long postId);
}
