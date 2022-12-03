package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "topic_message", schema = "public")
@Getter
@Setter
public class TopicMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topicId;
    @Column
    private String text;
    @Column
    private String doc;
    @Column
    private Date time;

    public TopicMessage() {}

    public TopicMessage(Long id, User userId,Topic topicId, String text, String doc, Date time) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.doc = doc;
        this.time = time;
        this.topicId = topicId;
    }
    public TopicMessage(User userId,Topic topicId, String text, String doc, Date time) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.doc = doc;
        this.time = time;
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "TopicMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", doc='" + doc + '\'' +
                ", time=" + time +
                '}';
    }
}
