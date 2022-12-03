package com.example.webserver.repository;

import com.example.webserver.model.Chat;

import com.example.webserver.model.Project;
import com.example.webserver.model.Topic;
import com.example.webserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    Iterable<Chat> findAllByUserId1(User user);
    ArrayList<Chat> findAllByUserId1AndUserId2(User user1, User user2);
    void deleteByUserId1AndUserId2(User entity1,User entity2);
    Chat findByUserId1(User entity1);
}
