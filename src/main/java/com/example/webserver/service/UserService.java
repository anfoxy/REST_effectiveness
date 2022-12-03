package com.example.webserver.service;

import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.dto.UserDTO;
import com.example.webserver.mapper.CustomerMapper;
import com.example.webserver.model.Chat;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.model.User;
import com.example.webserver.repository.ChatRepository;
import com.example.webserver.repository.ProjectStaffRepository;
import com.example.webserver.repository.TopicMessageRepository;
import com.example.webserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    TopicMessageRepository topicMessageRepository;

    @Autowired
    ProjectStaffRepository projectStaffRepository;
    @Autowired
    ChatService chatService;
    @Autowired
    CustomerMapper mapper;

    public User putMet(Long id, User s) throws ResourceNotFoundException {
        User user = findById(id);
        user.setUsername(s.getUsername());
        user.setPassword(s.getPassword());
        user.setCode(s.getCode());
        user.setMatchingPassword(s.getMatchingPassword());
        user.setUserLN(s.getUserLN());
        user.setUserFN(s.getUserFN());
        user.setRoles(s.getRoles());
        userRepository.save(user);
        return user;
    }



    public User updateUser(User s, Long id) throws ResourceNotFoundException {
        UserDTO dto = new UserDTO(s);
        dto.setId(id);
        User user = findById(id);
        mapper.updateUserFromDto(dto, user);
        userRepository.save(user);
        return user;
    }

    public Boolean login(String username, String password) {
        return !userRepository.searchUserByLoginAndPassword(username, password).isEmpty();
    }
    public Boolean register(User user) {
        if(userRepository.findUserForRegister(user.getUsername(),user.getUserFN(), user.getCode(), user.getUserLN()).isEmpty()){
            userRepository.save(user);
            return true;
        } else return false;
    }



    public void delete(Long id) throws ResourceNotFoundException {
        User user = findById(id);
        userRepository.deleteUserRoleByIdUser(id);

        Iterable<Chat> chats = chatRepository.findAllByUserId1(user);
        chats.forEach(c -> {
            try {
                chatService.delete(c);
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Iterable<TopicMessage> topicMessages = topicMessageRepository.findAllByUserId(user);
        topicMessageRepository.deleteAll(topicMessages);
        projectStaffRepository.deleteByUserId(user);


        userRepository.delete(user);

    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found for id:" + id + ""));
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
