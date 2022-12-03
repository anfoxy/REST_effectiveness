package com.example.webserver.dto;

import com.example.webserver.model.Message;
import com.example.webserver.model.Project;
import com.example.webserver.model.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProjectDTO {

    private Long id;

    private String projectName;

    private String description;

    public ProjectDTO(Long id, String projectName, String description) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
    }

    public ProjectDTO(Project c) {
        this.id = c.getId();
        this.projectName = c.getProjectName();
        this.description = c.getDescription();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project convertToEntity() {
        Project c = new Project();
        c.setId(id);
        c.setProjectName(projectName);
        c.setDescription(description);
        return c;
    }
}
