package com.example.webserver.builder;

import com.example.webserver.model.Project;
import com.example.webserver.model.ProjectStaff;
import com.example.webserver.model.User;
import lombok.Setter;


@Setter
public class ProjectStaffBuilder {

    private Long id;

    private User userId;

    private Project projectId;

    public ProjectStaffBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ProjectStaffBuilder setUserId(User userId) {
        this.userId = userId;
        return this;
    }
    public ProjectStaffBuilder setProjectId(Project projectId) {
        this.projectId = projectId;
        return this;
    }

    public ProjectStaff build() {
        return new ProjectStaff(id, userId,projectId);
    }
}
