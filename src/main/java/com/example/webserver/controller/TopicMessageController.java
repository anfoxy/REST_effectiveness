package com.example.webserver.controller;

import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.model.Topic;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.respons.DeleteResponse;
import com.example.webserver.service.TopicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class TopicMessageController {

    @Autowired
    TopicMessageService topicMessageService;


    @GetMapping("/topicMessage")
    public List<TopicMessage> getAllTopicMessage() {
        return topicMessageService.findAll();
    }

    @GetMapping("/topicMessage/{id}")
    public ResponseEntity<TopicMessage> getTopicMessageById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(topicMessageService.findById(id));
    }

    @PostMapping("/topicMessage")
    public TopicMessage createTopicMessage(@RequestBody TopicMessage topic){
        return  topicMessageService.save(topic);
    }

    @DeleteMapping("/topicMessage/{id}")
    public ResponseEntity<DeleteResponse> deleteTopicMessage(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        topicMessageService.delete(id);
        return ResponseEntity.ok(new DeleteResponse("TopicMessage with id:"+id+" deleted"));
    }


    @DeleteMapping("/topicMessage")
    public ResponseEntity<DeleteResponse> deleteTopicMessageAll() throws ResourceNotFoundException {
        topicMessageService.deleteAll();
        return ResponseEntity.ok(new DeleteResponse("TopicMessage deleted"));
    }

    @PatchMapping(value = "/topicMessage/{id}")
    public ResponseEntity<TopicMessage> patchTopicMessage(@PathVariable Long id, @RequestBody TopicMessage topic) throws ResourceNotFoundException {
        return ResponseEntity.ok(topicMessageService.updateQuestion(topic,id));
    }

    @PutMapping("/topicMessage/{id}")
    public ResponseEntity<TopicMessage> putTopicMessage(@PathVariable Long id,@RequestBody TopicMessage req) throws ResourceNotFoundException {
        return ResponseEntity.ok(topicMessageService.putMet(id,req));
    }

}














