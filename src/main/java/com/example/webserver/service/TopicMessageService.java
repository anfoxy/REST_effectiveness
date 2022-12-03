package com.example.webserver.service;

import com.example.webserver.dto.TopicDTO;
import com.example.webserver.dto.TopicMessageDTO;
import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.mapper.CustomerMapper;
import com.example.webserver.model.Topic;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.repository.TopicMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicMessageService {
    @Autowired
    TopicMessageRepository topicMessageRepository;

    @Autowired
    CustomerMapper mapper;


    public TopicMessage putMet(Long id, TopicMessage req) throws ResourceNotFoundException {
        TopicMessage topic = findById(id);
        topic.setUserId(req.getUserId());
        topic.setTime(req.getTime());
        topic.setText(req.getText());
        topic.setDoc(req.getDoc());
        topicMessageRepository.save(topic);
        return topic;
    }

    public TopicMessage updateQuestion(TopicMessage d, Long id) throws ResourceNotFoundException {
        TopicMessageDTO dto = new TopicMessageDTO(d);
        dto.setId(id);
        TopicMessage topic = findById(id);
        mapper.updateTopicMessageFromDto(dto, topic);
        topicMessageRepository.save(topic);
        return topic;
    }

    public void delete(Long id) throws ResourceNotFoundException {
        TopicMessage topic = findById(id);
        topicMessageRepository.delete(topic);
    }

    public void deleteByTopic(Topic topic) throws ResourceNotFoundException {
        topicMessageRepository.deleteByTopicId(topic);
    }


    public void deleteAll() {
        topicMessageRepository.deleteAll();
    }

    public TopicMessage save(TopicMessage topic){
        return topicMessageRepository.save(topic);
    }
    public TopicMessage findById(Long id) throws ResourceNotFoundException {
        return topicMessageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("TopicMessage not found for id:" + id.toString() + ""));
    }
    public List<TopicMessage> findAll() {
        return topicMessageRepository.findAll();
    }

}
