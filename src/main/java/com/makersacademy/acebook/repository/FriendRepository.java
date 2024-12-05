package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.dto.FriendWithData;
import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.makersacademy.acebook.model.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FriendRepository extends CrudRepository<Friend, Long> {
    @Query("SELECT u FROM User u " +
            "JOIN Friend f ON (u.auth0Id = f.userA OR u.auth0Id = f.userB) " +
            "WHERE (f.userA = :auth0Id OR f.userB = :auth0Id) AND u.auth0Id != :auth0Id")
    List<User> findFriends(@Param("auth0Id") String auth0Id);
}
