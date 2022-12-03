package com.example.webserver.controller;

import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.model.Project;
import com.example.webserver.respons.DeleteResponse;
import com.example.webserver.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping("/project")
    public List<Project> getAllProject() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping("/project")
    public Project createProject(@RequestBody Project project){
        return  projectService.save(project);
    }

    @DeleteMapping("/project/{id}")
    @Transactional
    public ResponseEntity<DeleteResponse> deleteProject(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        projectService.delete(id);
        return ResponseEntity.ok(new DeleteResponse("Project with id:"+id+" deleted"));
    }

    @DeleteMapping("/project")
    public ResponseEntity<DeleteResponse> deleteProjectAll() throws ResourceNotFoundException {
        projectService.deleteAll();
        return ResponseEntity.ok(new DeleteResponse("Project deleted"));
    }

    @PatchMapping(value = "/project/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable Long id, @RequestBody Project project) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectService.updateProject(project,id));
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<Project> putProject(@PathVariable Long id,@RequestBody Project req) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectService.putMet(id,req));
    }

}














