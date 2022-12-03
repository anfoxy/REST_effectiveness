package com.example.webserver.dto;

import com.example.webserver.model.Message;
import com.example.webserver.model.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class MessageDTO {

    private Long id;

    private User userId;

    private String text;

    private String doc;

    private Date time;


    public MessageDTO(Long id, User userId, String text, String doc, Date time) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.doc = doc;
        this.time = time;
    }

    public MessageDTO(Message c) {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.text = c.getText();
        this.doc = c.getDoc();
        this.time = c.getTime();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message convertToEntity() {
        Message c = new Message();
        c.setId(id);
        c.setUserId(userId);
        c.setText(text);
        c.setDoc(doc);
        c.setTime(time);
        return c;
    }
}
