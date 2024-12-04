package com.makersacademy.acebook.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.makersacademy.acebook.model.FriendRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FriendRequestRepo extends CrudRepository<FriendRequest, Long> {
    //To remove a friend request
    @Query("DELETE FROM FriendRequest f WHERE (f.requested_user = :requested_user AND f.requesting_user = :requesting_user) OR (f.requesting_user = : requested_user AND f.requested_user = :requesting_user)")
    void deleteFriendship(@Param("requesting_user") String requesting_user, @Param("requested_user") String requested_user);

    //To check for mutual friends
    @Query("SELECT f FROM FriendRequest f WHERE (f.requesting_user = :requested_user " +
            "AND f.requested_user IN (SELECT f2.requested_user FROM FriendRequest f2 WHERE f2.requesting_user = :requesting_user)) " +
            "OR (f.requested_user = :requesting_user AND f.requesting_user IN (SELECT f2.requesting_user FROM FriendRequest f2 WHERE f2.requested_user = :requested_user))")
    List<FriendRequestRepo> findCommonFriends(@Param("requesting_user") String requesting_user, @Param("requested_user") String requested_user );
}
