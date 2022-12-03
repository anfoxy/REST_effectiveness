package com.example.webserver.builder;

import com.example.webserver.model.Chat;
import com.example.webserver.model.User;

public class ChatBuilder {

    private Long id;
    private User userId1;
    private User userId2;

    public ChatBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ChatBuilder setUserId1(User userId1) {
        this.userId1 = userId1;
        return this;
    }

    public ChatBuilder setUserId2(User userId2) {
        this.userId2 = userId2;
        return this;
    }

    public Chat build() {
        return new Chat(id, userId1, userId2);
    }
}
