package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "topic", schema = "public")
@Getter
@Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;

    @Column(name = "topic_name",nullable = false)
    private String nameTopic;

    @Column
    private String category;

    public Topic(Long id, Project projectId, String nameTopic, String category) {
        this.id = id;
        this.projectId = projectId;
        this.nameTopic = nameTopic;
        this.category = category;
    }

    public Topic(Project projectId, String nameTopic, String category) {
        this.projectId = projectId;
        this.nameTopic = nameTopic;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", nameTopic='" + nameTopic + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public Topic() {}

}
