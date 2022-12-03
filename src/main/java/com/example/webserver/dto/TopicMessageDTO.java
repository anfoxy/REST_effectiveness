package com.example.webserver.dto;

import com.example.webserver.model.Project;
import com.example.webserver.model.Topic;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.model.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class TopicMessageDTO {

    private Long id;
    private User userId;
    private String text;
    private String doc;

    private Date time;
    public TopicMessageDTO(Long id, User userId, String text, String doc, Date time) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.doc = doc;
        this.time = time;
    }

    public TopicMessageDTO(TopicMessage c) {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.text = c.getText();
        this.doc = c.getDoc();
        this.time = c.getTime();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicMessage convertToEntity() {
        TopicMessage c = new TopicMessage();
        c.setId(id);
        c.setDoc(doc);
        c.setText(text);
        c.setTime(time);
        c.setUserId(userId);
        return c;
    }
}
