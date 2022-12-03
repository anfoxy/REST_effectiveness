package com.example.webserver.dto;

import com.example.webserver.model.Project;
import com.example.webserver.model.ProjectStaff;
import com.example.webserver.model.User;
import lombok.Getter;

@Getter
public class ProjectStaffDTO {

    private Long id;

    private User userId;
    private Project projectId;


    public ProjectStaffDTO(Long id, User userId, Project projectId) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
    }

    public ProjectStaffDTO(ProjectStaff c) {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.projectId = c.getProjectId();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public ProjectStaff convertToEntity() {
        ProjectStaff c = new ProjectStaff();
        c.setId(id);
        c.setUserId(userId);
        c.setProjectId(projectId);
        return c;
    }
}
