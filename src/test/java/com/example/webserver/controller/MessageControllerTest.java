package com.example.webserver.controller;

import com.example.webserver.builder.MessageBuilder;
import com.example.webserver.builder.UserBuilder;
import com.example.webserver.model.Message;
import com.example.webserver.model.User;
import com.example.webserver.repository.MessageRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageBuilder messageBuilder;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @BeforeEach
    void after() throws Exception{
        messageRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    void getAllMessage() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        Message message1 = messageBuilder.setText("text").setTime(formatter.parse("2021-01-03")).setUserId(user1).build();
        messageRepository.save(message1);

        User user2 = userBuilder.setUsername("Anna").setCode("2").setPassword("Qwerty").build();
        userRepository.save(user2);

        Message message2 = messageBuilder.setText("text2").setTime(formatter.parse("2022-02-04")).setUserId(user2).build();
        messageRepository.save(message2);

        mockMvc.perform(get("/rest/message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId.password").value("1234"))
                .andExpect(jsonPath("$[0].userId.code").value("1"))
                .andExpect(jsonPath("$[0].userId.userName").value("Nastya"))
                .andExpect(jsonPath("$[0].text").value("text"))
                .andExpect(jsonPath("$[0].time").value("2021-01-02T21:00:00.000+00:00"))

                .andExpect(jsonPath("$[1].text").value("text2"))
                .andExpect(jsonPath("$[1].time").value("2022-02-03T21:00:00.000+00:00"))
                .andExpect(jsonPath("$[1].userId.password").value("Qwerty"))
                .andExpect(jsonPath("$[1].userId.code").value("2"))
                .andExpect(jsonPath("$[1].userId.userName").value("Anna"));
    }

    @Test
    void getMessageById() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        Message message1 = messageBuilder.setText("text").setTime(formatter.parse("2021-01-03")).setUserId(user1).build();
        messageRepository.save(message1);

        mockMvc.perform(get("/rest/message/{id}", message1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId.password").value("1234"))
                .andExpect(jsonPath("$.userId.code").value("1"))
                .andExpect(jsonPath("$.userId.userName").value("Nastya"))
                .andExpect(jsonPath("$.text").value("text"))
                .andExpect(jsonPath("$.time").value("2021-01-02T21:00:00.000+00:00"));
    }

    @Test
    void createMessage() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        Message message1 = messageBuilder.setText("text").setTime(formatter.parse("2021-01-03")).setUserId(user1).build();

        mockMvc.perform(post("/rest/message")
                        .content(objectMapper.writeValueAsString(message1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId.password").value("1234"))
                .andExpect(jsonPath("$.userId.code").value("1"))
                .andExpect(jsonPath("$.userId.userName").value("Nastya"))
                .andExpect(jsonPath("$.text").value("text"))
                .andExpect(jsonPath("$.time").value("2021-01-02T21:00:00.000+00:00"));
    }

    @Test
    void deleteMessage() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        Message message1 = messageBuilder.setText("text").setTime(formatter.parse("2021-01-03")).setUserId(user1).build();
        messageRepository.save(message1);

        mockMvc.perform(delete("/rest/message/{id}", message1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Message with id:"+message1.getId()+" deleted"));
    }

    @Test
    void patchMessage() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        Message message1 = messageBuilder.setText("text").setTime(formatter.parse("2021-01-03")).setUserId(user1).build();
        messageRepository.save(message1);
        message1.setText("rrr");

        mockMvc.perform(patch("/rest/message/{id}", message1.getId())
                        .content(objectMapper.writeValueAsString(message1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId.password").value("1234"))
                .andExpect(jsonPath("$.userId.code").value("1"))
                .andExpect(jsonPath("$.userId.userName").value("Nastya"))
                .andExpect(jsonPath("$.text").value("rrr"))
                .andExpect(jsonPath("$.time").value("2021-01-02T21:00:00.000+00:00"));
    }

    @Test
    void putMessage() throws Exception {
        User user1 = userBuilder.setUsername("Nastya").setCode("1").setPassword("1234").build();
        userRepository.save(user1);

        Message message1 = messageBuilder.setText("text").setTime(formatter.parse("2021-01-03")).setUserId(user1).build();
        messageRepository.save(message1);

        Message message2 = messageBuilder.setText("text2").build();


        mockMvc.perform(put("/rest/message/{id}", message1.getId())
                        .content(objectMapper.writeValueAsString(message2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId.password").value("1234"))
                .andExpect(jsonPath("$.userId.code").value("1"))
                .andExpect(jsonPath("$.userId.userName").value("Nastya"))
                .andExpect(jsonPath("$.text").value("text2"))
                .andExpect(jsonPath("$.time").value("2021-01-02T21:00:00.000+00:00"));
    }
}