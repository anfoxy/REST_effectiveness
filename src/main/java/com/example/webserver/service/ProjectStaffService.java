package com.example.webserver.service;

import com.example.webserver.dto.ProjectDTO;
import com.example.webserver.dto.ProjectStaffDTO;
import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.mapper.CustomerMapper;
import com.example.webserver.model.Project;
import com.example.webserver.model.ProjectStaff;
import com.example.webserver.model.User;
import com.example.webserver.repository.ProjectStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStaffService {
    @Autowired
    ProjectStaffRepository projectStaffRepository;

    @Autowired
    CustomerMapper mapper;


    public ProjectStaff putMet(Long id, ProjectStaff req) throws ResourceNotFoundException {
        ProjectStaff message = findById(id);
        message.setUserId(req.getUserId());
        message.setProjectId(req.getProjectId());
        projectStaffRepository.save(message);
        return message;
    }

    public ProjectStaff updateQuestion(ProjectStaff d, Long id) throws ResourceNotFoundException {
        ProjectStaffDTO dto = new ProjectStaffDTO(d);
        dto.setId(id);
        ProjectStaff message = findById(id);
        mapper.updateProjectStaffFromDto(dto, message);
        projectStaffRepository.save(message);
        return message;
    }


    public void delete(Long id) throws ResourceNotFoundException {
        ProjectStaff project = findById(id);
        projectStaffRepository.delete(project);
    }

    public void deleteByProjectId(Project project) throws ResourceNotFoundException {
        projectStaffRepository.deleteByProjectId(project);
    }

    public void deleteByUserId(User user) throws ResourceNotFoundException {
        projectStaffRepository.deleteByUserId(user);
    }

    public void deleteAll() throws ResourceNotFoundException {
        projectStaffRepository.deleteAll();
    }

    public ProjectStaff save(ProjectStaff project){
        return projectStaffRepository.save(project);
    }
    public ProjectStaff findById(Long id) throws ResourceNotFoundException {
        return projectStaffRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ProjectStaff not found for id:" + id.toString() + ""));
    }
    public List<ProjectStaff> findAll() {
        return projectStaffRepository.findAll();
    }

}
