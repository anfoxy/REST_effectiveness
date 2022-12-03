package com.example.webserver.dto;

import com.example.webserver.model.Chat;
import com.example.webserver.model.User;
import lombok.Getter;

@Getter
public class ChatDTO {

    private Long id;

    private User userId1;

    private User userId2;

    public ChatDTO(Long id, User userId1, User userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatDTO(Chat c) {
        this.id = c.getId();
        this.userId1 = c.getUserId1();
        this.userId2 = c.getUserId2();

    }

    public Chat convertToEntity() {
        Chat c = new Chat();
        c.setId(id);
        c.setUserId1(userId1);
        c.setUserId2(userId2);
        return c;
    }
}
