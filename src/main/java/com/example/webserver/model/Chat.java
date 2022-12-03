package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "chat", schema = "public")
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User userId1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User userId2;


    public Chat() {}

    public Chat(Long id, User userId1, User userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;

    }

    public Chat(User userId1, User userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;

    }
    @Override
    public String toString() {
        return "ChatBuilder{" +
                "id=" + id +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                '}';
    }
}
