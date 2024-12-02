package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.DirectMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectMessageRepository extends CrudRepository<DirectMessage, Long> {
    List<DirectMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);
    List<DirectMessage> findByReceiverIdAndSenderId(String receiverId, String senderId);

}
