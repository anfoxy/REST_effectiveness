package com.example.webserver.controller;

import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.model.Project;
import com.example.webserver.model.ProjectStaff;
import com.example.webserver.respons.DeleteResponse;
import com.example.webserver.service.ProjectStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ProjectStaffController {

    @Autowired
    ProjectStaffService projectStaffService;


    @GetMapping("/projectStaff")
    public List<ProjectStaff> getAllProjectStaff() {
        return projectStaffService.findAll();
    }

    @GetMapping("/projectStaff/{id}")
    public ResponseEntity<ProjectStaff> getProjectStaffById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectStaffService.findById(id));
    }

    @PostMapping("/projectStaff")
    public ProjectStaff createProjectStaff(@RequestBody ProjectStaff project){
        return  projectStaffService.save(project);
    }

    @DeleteMapping("/projectStaff/{id}")
    public ResponseEntity<DeleteResponse> deleteProjectStaff(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        projectStaffService.delete(id);
        return ResponseEntity.ok(new DeleteResponse("ProjectStaff with id:"+id+" deleted"));
    }

    @DeleteMapping("/projectStaff")
    public ResponseEntity<DeleteResponse> deleteProjectStaffAll() throws ResourceNotFoundException {
        projectStaffService.deleteAll();
        return ResponseEntity.ok(new DeleteResponse("ProjectStaff deleted"));
    }
    @PatchMapping(value = "/projectStaff/{id}")
    public ResponseEntity<ProjectStaff> patchProjectStaff(@PathVariable Long id, @RequestBody ProjectStaff project) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectStaffService.updateQuestion(project,id));
    }

    @PutMapping("/projectStaff/{id}")
    public ResponseEntity<ProjectStaff> putProjectStaff(@PathVariable Long id,@RequestBody ProjectStaff req) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectStaffService.putMet(id,req));
    }

}














