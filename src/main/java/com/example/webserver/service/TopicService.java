package com.example.webserver.service;

import com.example.webserver.dto.TopicDTO;
import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.mapper.CustomerMapper;
import com.example.webserver.model.Topic;
import com.example.webserver.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    TopicMessageService topicMessageService;
    @Autowired
    CustomerMapper mapper;


    public Topic putMet(Long id, Topic req) throws ResourceNotFoundException {
        Topic topic = findById(id);
        topic.setNameTopic(req.getNameTopic());
        topic.setProjectId(req.getProjectId());
        topic.setCategory(req.getCategory());
        topicRepository.save(topic);
        return topic;
    }

    public Topic updateTopic(Topic d, Long id) throws ResourceNotFoundException {
        TopicDTO dto = new TopicDTO(d);
        dto.setId(id);
        Topic topic = findById(id);
        mapper.updateTopicFromDto(dto, topic);
        topicRepository.save(topic);
        return topic;
    }


    public void delete(Topic topic) throws ResourceNotFoundException {
        topicMessageService.deleteByTopic(topic);
        topicRepository.delete(topic);
    }

    public void deleteAll() throws ResourceNotFoundException {
        topicRepository.deleteAll();
    }

    public Topic save(Topic topic){
        return topicRepository.save(topic);
    }
    public Topic findById(Long id) throws ResourceNotFoundException {
        return topicRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Topic not found for id:" + id.toString() + ""));
    }
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

}
