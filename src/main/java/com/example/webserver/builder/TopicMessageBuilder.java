package com.example.webserver.builder;

import com.example.webserver.model.Topic;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.model.User;
import lombok.Setter;

import java.util.Date;


@Setter
public class TopicMessageBuilder {

    private Long id;
    private User userId;
    private Topic topicId;
    private String text;
    private String doc;
    private Date time;

    public TopicMessageBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TopicMessageBuilder setUserId(User userId) {
        this.userId = userId;
        return this;
    }
    public TopicMessageBuilder setTopicId(Topic topicId) {
        this.topicId = topicId;
        return this;
    }
    public TopicMessageBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public TopicMessageBuilder setDoc(String doc) {
        this.doc = doc;
        return this;
    }

    public TopicMessageBuilder setTime(Date time) {
        this.time = time;
        return this;
    }

    public TopicMessage build() {
        return new TopicMessage(id, userId,topicId, text,doc,time);
    }
}
