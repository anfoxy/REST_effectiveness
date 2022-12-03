package com.example.webserver.builder;

import com.example.webserver.model.Message;
import com.example.webserver.model.Project;
import lombok.Setter;

@Setter
public class ProjectBuilder {


    private Long id;
    private String projectName;
    private String description;

    public ProjectBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public ProjectBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Project build() {
        return new Project(id, projectName, description);
    }
}
