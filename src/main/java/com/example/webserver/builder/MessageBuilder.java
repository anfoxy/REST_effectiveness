package com.example.webserver.builder;

import com.example.webserver.model.Chat;
import com.example.webserver.model.Message;
import com.example.webserver.model.User;
import lombok.Setter;

import java.util.Date;

@Setter
public class MessageBuilder {

    private Long id;
    private User userId;
    private User recipientId;
    private String text;
    private String doc;
    private Date time;

    public MessageBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MessageBuilder setUserId(User userId) {
        this.userId = userId;
        return this;
    }
    public MessageBuilder setRecipientId(User recipientId) {
        this.recipientId = recipientId;
        return this;
    }
    public MessageBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public MessageBuilder setDoc(String doc) {
        this.doc = doc;
        return this;
    }

    public MessageBuilder setTime(Date time) {
        this.time = time;
        return this;
    }

    public Message build() {
        return new Message(id, userId,recipientId, text,doc,time);
    }
}
