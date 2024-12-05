package com.makersacademy.acebook.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.makersacademy.acebook.model.FriendRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {
    List<FriendRequest> findByRequestedUser(String requestedUser);
    Optional<FriendRequest> findByRequestingUserAndRequestedUser(String requestingUser, String requestedUser);
}
