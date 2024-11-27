package com.makersacademy.acebook.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.makersacademy.acebook.model.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FriendsRepository extends CrudRepository<Friend, Long> {

    //To remove a friend
    @Query("DELETE FROM Friend f WHERE (f.id.userA = :userA AND f.id.userB = :userB) OR (f.id.userA = :userB AND f.id.userB = :userA)")
    void deleteFriendship(@Param("userA") String userA, @Param("userB") String userB);

    //To check for mutual friends
    @Query("SELECT f FROM Friend f WHERE (f.id.userA = :userA " +
            "AND f.id.userB IN (SELECT f2.id.userB FROM Friend f2 WHERE f2.id.userA = :userB)) " +
            "OR (f.id.userB = :userA AND f.id.userA IN (SELECT f2.id.userA FROM Friend f2 WHERE f2.id.userB = :userB))")
    List<Friend> findCommonFriends(@Param("userA") String userA, @Param("userB") String userB );
        }
