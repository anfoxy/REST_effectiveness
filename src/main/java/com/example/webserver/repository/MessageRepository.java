package com.example.webserver.repository;

import com.example.webserver.model.Message;
import com.example.webserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message,Long> {


    Set<Message> findAllByUserIdInAndRecipientIdIn(Iterable  userId,Iterable  recipient);
    void deleteByRecipientIdAndUserId(User recipient,User userId);
}
