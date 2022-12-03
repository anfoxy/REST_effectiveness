package com.example.webserver.repository;

import com.example.webserver.model.Chat;
import com.example.webserver.model.Topic;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TopicMessageRepository extends JpaRepository<TopicMessage,Long> {

    ArrayList<TopicMessage> findAllByTopicId(Topic topic);
    Iterable<TopicMessage> findAllByUserId(User user);

    void deleteByTopicId(Topic topic);
}
