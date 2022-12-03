package com.example.webserver.controller;

import com.example.webserver.builder.UserBuilder;
import com.example.webserver.model.User;
import com.example.webserver.repository.MessageRepository;
import com.example.webserver.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void after() throws Exception{
        messageRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    void loginOK() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").setUserFN("aaa@mail.ru").setUserLN("kazhaeva").build();
        userRepository.save(user1);

        mockMvc.perform(post("/rest/login").content(objectMapper.writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void loginNO() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").setUserFN("aaa@mail.ru").setUserLN("kazhaeva").build();
        user1.setMatchingPassword("1234");
        userRepository.save(user1);
        User user2 = userBuilder.setUsername("Nastya").setCode("1").setPassword("124").setUserFN("aaa@mail.ru").setUserLN("kazhaeva").build();

        mockMvc.perform(post("/rest/login").content(objectMapper.writeValueAsString(user2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("NO"));
    }

    @Test
    void register() throws Exception{
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").setUserFN("aaa@mail.ru").setUserLN("kazhaeva").build();
        user1.setMatchingPassword("1234");

        mockMvc.perform(post("/rest/register").content(objectMapper.writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
    @Test
    void registerNOPassword() throws Exception{
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").setUserFN("aaa@mail.ru").setUserLN("kazhaeva").build();
        user1.setMatchingPassword("123");

        mockMvc.perform(post("/rest/register").content(objectMapper.writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("password doesn't match"));
    }
    @Test
    void registerNO() throws Exception{
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").setUserFN("aaa@mail.ru").setUserLN("kazhaeva").build();
        user1.setMatchingPassword("1234");
        userRepository.save(user1);

        mockMvc.perform(post("/rest/register").content(objectMapper.writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("NO"));
    }

    @Test
    void getAllUser() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        mockMvc.perform(get("/rest/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("Nastya"))
                .andExpect(jsonPath("$[0].password").value("1234"))
                .andExpect(jsonPath("$[0].code").value("1"))
                .andExpect(jsonPath("$[1].userName").value("Anna"))
                .andExpect(jsonPath("$[1].password").value("Qwerty"))
                .andExpect(jsonPath("$[1].code").value("2"));

    }

    @Test
    void getUserById() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        mockMvc.perform(get("/rest/user/{id}", user1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Nastya"))
                .andExpect(jsonPath("$.password").value("1234"))
                .andExpect(jsonPath("$.code").value("1"));
    }

    @Test
    void createUser() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();

        mockMvc.perform(post("/rest/user")
                        .content(objectMapper.writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Nastya"))
                .andExpect(jsonPath("$.password").value("1234"))
                .andExpect(jsonPath("$.code").value("1"));
    }

    @Test
    void deleteUser() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        mockMvc.perform(delete("/rest/user/{id}", user1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User with id:"+user1.getId()+" deleted"));
    }

    @Test
    void patchUser() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);
        user1.setUsername("test");
        mockMvc.perform(patch("/rest/user/{id}", user1.getId())
                        .content(objectMapper.writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("test"))
                .andExpect(jsonPath("$.password").value("1234"))
                .andExpect(jsonPath("$.code").value("1"));
    }

    @Test
    void putUser() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);
        User user2 = userBuilder.setUsername("Nastya1").build();
        mockMvc.perform(put("/rest/user/{id}", user1.getId())
                        .content(objectMapper.writeValueAsString(user2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Nastya1"))
                .andExpect(jsonPath("$.password").value("1234"))
                .andExpect(jsonPath("$.code").value("1"));
    }
}