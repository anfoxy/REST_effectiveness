package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "project_staff", schema = "public")
@Getter
@Setter
public class ProjectStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;

    public ProjectStaff(Long id, User userId, Project projectId) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
    }
    public ProjectStaff( User userId, Project projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }
    public ProjectStaff() {}

    @Override
    public String toString() {
        return "ProjectStaff{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
