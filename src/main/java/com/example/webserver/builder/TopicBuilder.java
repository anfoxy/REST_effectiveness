package com.example.webserver.builder;

import com.example.webserver.model.Message;
import com.example.webserver.model.Project;
import com.example.webserver.model.Topic;
import lombok.Setter;


@Setter
public class TopicBuilder {

    private Long id;
    private Project projectId;
    private String nameTopic;
    private String category;

    public TopicBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TopicBuilder setProjectId(Project projectId) {
        this.projectId = projectId;
        return this;
    }

    public TopicBuilder setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
        return this;
    }

    public TopicBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public Topic build() {
        return new Topic(id, projectId, nameTopic,category);
    }
}
