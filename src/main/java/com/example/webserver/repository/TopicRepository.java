package com.example.webserver.repository;

import com.example.webserver.model.Project;
import com.example.webserver.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    Iterable<Topic> findAllByProjectId(Project projectId);
}
