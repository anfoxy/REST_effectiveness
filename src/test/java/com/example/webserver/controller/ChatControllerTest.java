package com.example.webserver.controller;

import com.example.webserver.builder.ChatBuilder;
import com.example.webserver.builder.UserBuilder;
import com.example.webserver.model.Chat;
import com.example.webserver.model.User;
import com.example.webserver.repository.ChatRepository;
import com.example.webserver.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatBuilder chatBuilder;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void after() throws Exception{
        chatRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    void getAllChat() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Chat chat1 = chatBuilder.setUserId1(user1).setUserId2(user2).build();
        chatRepository.save(chat1);
        Chat chat2 = chatBuilder.setUserId1(user2).setUserId2(user1).build();
        chatRepository.save(chat2);

        mockMvc.perform(get("/rest/chat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId1.password").value("1234"))
                .andExpect(jsonPath("$[0].userId1.code").value("1"))
                .andExpect(jsonPath("$[0].userId1.userName").value("Nastya"))
                .andExpect(jsonPath("$[1].userId2.password").value("1234"))
                .andExpect(jsonPath("$[1].userId2.code").value("1"))
                .andExpect(jsonPath("$[1].userId2.userName").value("Nastya"))
                .andExpect(jsonPath("$[1].userId1.password").value("Qwerty"))
                .andExpect(jsonPath("$[1].userId1.code").value("2"))
                .andExpect(jsonPath("$[1].userId1.userName").value("Anna"))
                .andExpect(jsonPath("$[0].userId2.password").value("Qwerty"))
                .andExpect(jsonPath("$[0].userId2.code").value("2"))
                .andExpect(jsonPath("$[0].userId2.userName").value("Anna"));

    }

    @Test
    void getChatById() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Chat chat1 = chatBuilder.setUserId1(user2).setUserId2(user1).build();
        chatRepository.save(chat1);

        mockMvc.perform(get("/rest/chat/{id}", chat1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId2.password").value("1234"))
                .andExpect(jsonPath("$.userId2.code").value("1"))
                .andExpect(jsonPath("$.userId2.userName").value("Nastya"))
                .andExpect(jsonPath("$.userId1.password").value("Qwerty"))
                .andExpect(jsonPath("$.userId1.code").value("2"))
                .andExpect(jsonPath("$.userId1.userName").value("Anna"));
    }

    @Test
    void createChat() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Chat chat1 = chatBuilder.setUserId1(user1).setUserId2(user2).build();


        mockMvc.perform(post("/rest/chat")
                        .content(objectMapper.writeValueAsString(chat1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId1.password").value("1234"))
                .andExpect(jsonPath("$.userId1.code").value("1"))
                .andExpect(jsonPath("$.userId1.userName").value("Nastya"))
                .andExpect(jsonPath("$.userId2.password").value("Qwerty"))
                .andExpect(jsonPath("$.userId2.code").value("2"))
                .andExpect(jsonPath("$.userId2.userName").value("Anna"));
    }

    @Test
    void deleteChat() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Chat chat1 = chatBuilder.setUserId1(user1).setUserId2(user2).build();
        chatRepository.save(chat1);

        mockMvc.perform(delete("/rest/chat/{id}", chat1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Chat with id:"+chat1.getId()+" deleted"));
    }

    @Test
    void patchChat() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Chat chat1 = chatBuilder.setUserId1(user1).setUserId2(user2).build();
        chatRepository.save(chat1);
        chat1.getUserId1().setUsername("test");

        mockMvc.perform(patch("/rest/chat/{id}", chat1.getId())
                        .content(objectMapper.writeValueAsString(chat1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId1.password").value("1234"))
                .andExpect(jsonPath("$.userId1.code").value("1"))
                .andExpect(jsonPath("$.userId2.userName").value("Anna"))
                .andExpect(jsonPath("$.userId2.password").value("Qwerty"))
                .andExpect(jsonPath("$.userId2.code").value("2"))
                .andExpect(jsonPath("$.userId1.userName").value("test"));
    }

    @Test
    void putChat() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Chat chat1 = chatBuilder.setUserId1(user1).setUserId2(user2).build();
        chatRepository.save(chat1);
        Chat chat2 = chatBuilder.setUserId1(user2).build();

        mockMvc.perform(put("/rest/chat/{id}", chat1.getId())
                        .content(objectMapper.writeValueAsString(chat2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId2.password").value("Qwerty"))
                .andExpect(jsonPath("$.userId2.code").value("2"))
                .andExpect(jsonPath("$.userId2.userName").value("Anna"))
                .andExpect(jsonPath("$.userId1.password").value("Qwerty"))
                .andExpect(jsonPath("$.userId1.code").value("2"))
                .andExpect(jsonPath("$.userId1.userName").value("Anna"));
    }
}