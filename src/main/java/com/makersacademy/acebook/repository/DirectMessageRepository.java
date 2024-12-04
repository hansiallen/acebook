package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.DirectMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectMessageRepository extends CrudRepository<DirectMessage, Long> {
    List<DirectMessage> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
    List<DirectMessage> findByReceiverIdAndSenderId(Long receiverId, Long senderId);

}
