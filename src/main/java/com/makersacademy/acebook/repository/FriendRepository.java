package com.makersacademy.acebook.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.makersacademy.acebook.model.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FriendRepository extends CrudRepository<Friend, Long> {
    //To check for mutual friends
    @Query("SELECT f FROM Friend f WHERE (f.user_a = :user_a " +
            "AND f.user_b IN (SELECT f2.user_b FROM Friend f2 WHERE f2.user_a = :user_b)) " +
            "OR (f.user_b = :user_a AND f.user_a IN (SELECT f2.user_a FROM Friend f2 WHERE f2.user_b = :user_b))")
    List<Friend> findCommonFriends(@Param("user_a") String user_a, @Param("user_b") String user_b );
        }
