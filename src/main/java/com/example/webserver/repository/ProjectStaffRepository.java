package com.example.webserver.repository;

import com.example.webserver.model.Project;
import com.example.webserver.model.ProjectStaff;

import com.example.webserver.model.Topic;
import com.example.webserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProjectStaffRepository extends JpaRepository<ProjectStaff,Long> {


    Iterable<ProjectStaff> findAllByUserId(User userId);

    ArrayList<ProjectStaff> findAllByProjectId(Project project);


    void deleteByProjectId(Project entity);
    void deleteByUserId(User entity);
}
