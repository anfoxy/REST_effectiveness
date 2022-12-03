package com.example.webserver.dto;

import com.example.webserver.model.Message;
import com.example.webserver.model.Project;
import com.example.webserver.model.Topic;
import com.example.webserver.model.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class TopicDTO {

    private Long id;

    private Project projectId;

    private String nameTopic;

    private String category;


    public TopicDTO(Long id, Project projectId, String nameTopic, String category) {
        this.id = id;
        this.projectId = projectId;
        this.nameTopic = nameTopic;
        this.category = category;
    }

    public TopicDTO(Topic c) {
        this.id = c.getId();
        this.projectId = c.getProjectId();
        this.nameTopic = c.getNameTopic();
        this.category = c.getCategory();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic convertToEntity() {
        Topic c = new Topic();
        c.setId(id);
        c.setCategory(category);
        c.setNameTopic(nameTopic);
        c.setProjectId(projectId);
        return c;
    }
}
