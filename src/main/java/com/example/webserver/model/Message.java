package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "message", schema = "public")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipientId;
    @Column(name = "text",nullable = false)
    private String text;
    @Column
    private String doc;
    @Column
    private Date time;



    public Message() {}

    public Message(Long id, User userId,User recipientId, String text, String doc, Date time) {
        this.id = id;
        this.userId = userId;
        this.recipientId = recipientId;
        this.text = text;
        this.doc = doc;
        this.time = time;
    }
    public Message( User userId,User recipientId, String text, String doc, Date time) {
        this.userId = userId;
        this.recipientId = recipientId;
        this.text = text;
        this.doc = doc;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userId=" + userId +
                ", recipientId=" + recipientId +
                ", text='" + text + '\'' +
                ", doc='" + doc + '\'' +
                ", time=" + time +
                '}';
    }
}
