package com.example.webserver.service;

import com.example.webserver.dto.MessageDTO;
import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.mapper.CustomerMapper;
import com.example.webserver.model.Message;
import com.example.webserver.model.User;
import com.example.webserver.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    CustomerMapper mapper;


    public Message putMet(Long id, Message req) throws ResourceNotFoundException {
        Message message = findById(id);
        message.setTime(req.getTime());
        message.setDoc(req.getDoc());
        message.setText(req.getText());
        message.setUserId(req.getUserId());
        messageRepository.save(message);
        return message;
    }

    public Message updateQuestion(Message d, Long id) throws ResourceNotFoundException {
        MessageDTO dto = new MessageDTO(d);
        dto.setId(id);
        Message message = findById(id);
        mapper.updateMessageFromDto(dto, message);
        messageRepository.save(message);
        return message;
    }


    public void deleteById(Long id) throws ResourceNotFoundException {
        Message message = findById(id);
        messageRepository.delete(message);
    }
    public void delete(User u1, User u2) {
        messageRepository.deleteByRecipientIdAndUserId(u1,u2);
        messageRepository.deleteByRecipientIdAndUserId(u2,u1);
    }
    public void deleteAll() {
        messageRepository.deleteAll();
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }
    public Message findById(Long id) throws ResourceNotFoundException {
        return messageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Message not found for id:" + id.toString() + ""));
    }
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

}
