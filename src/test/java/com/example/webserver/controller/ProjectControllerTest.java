package com.example.webserver.controller;

import com.example.webserver.builder.MessageBuilder;
import com.example.webserver.builder.ProjectBuilder;
import com.example.webserver.builder.UserBuilder;
import com.example.webserver.model.Project;
import com.example.webserver.model.User;
import com.example.webserver.repository.MessageRepository;
import com.example.webserver.repository.ProjectRepository;
import com.example.webserver.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectBuilder projectBuilder;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void after() throws Exception{
        projectRepository.deleteAll();
    }
    @Test
    void getAllProject() throws Exception {
        Project project1 = projectBuilder.setProjectName("Test1").setDescription("text1").build();
        projectRepository.save(project1);

        Project project2 = projectBuilder.setProjectName("Test2").setDescription("text2").build();
        projectRepository.save(project2);

        mockMvc.perform(get("/rest/project"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectName").value("Test1"))
                .andExpect(jsonPath("$[0].description").value("text1"))

                .andExpect(jsonPath("$[1].projectName").value("Test2"))
                .andExpect(jsonPath("$[1].description").value("text2"));
    }

    @Test
    void getProjectById() throws Exception {
        Project project1 = projectBuilder.setProjectName("Test1").setDescription("text1").build();
        projectRepository.save(project1);

        mockMvc.perform(get("/rest/project/{id}", project1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Test1"))
                .andExpect(jsonPath("$.description").value("text1"));
    }

    @Test
    void createProject() throws Exception {
        Project project1 = projectBuilder.setProjectName("Test1").setDescription("text1").build();

        mockMvc.perform(post("/rest/project")
                        .content(objectMapper.writeValueAsString(project1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Test1"))
                .andExpect(jsonPath("$.description").value("text1"));
    }

    @Test
    void deleteProject() throws Exception {
        Project project1 = projectBuilder.setProjectName("Test1").setDescription("text1").build();
        projectRepository.save(project1);

        mockMvc.perform(delete("/rest/project/{id}", project1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Project with id:"+project1.getId()+" deleted"));
    }

    @Test
    void patchProject() throws Exception {
        Project project1 = projectBuilder.setProjectName("Test1").setDescription("text1").build();
        projectRepository.save(project1);
        project1.setProjectName("Test2");
        mockMvc.perform(patch("/rest/project/{id}", project1.getId())
                        .content(objectMapper.writeValueAsString(project1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Test2"))
                .andExpect(jsonPath("$.description").value("text1"));
    }

    @Test
    void putProject() throws Exception {
        Project project1 = projectBuilder.setProjectName("Test1").setDescription("text1").build();
        projectRepository.save(project1);
        Project project2 = projectBuilder.setProjectName("Test2").build();


        mockMvc.perform(put("/rest/project/{id}", project1.getId())
                        .content(objectMapper.writeValueAsString(project2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Test2"))
                .andExpect(jsonPath("$.description").value("text1"));
    }
}