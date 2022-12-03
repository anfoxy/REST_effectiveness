package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "project", schema = "public")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "project_name",nullable = false)
    private String projectName;
    @Column
    private String description;

    public Project(Long id, String projectName, String description) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
    }


    public Project(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    public Project() {}

    @Override
    public String toString() {
        return "ProjectBuilder{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
