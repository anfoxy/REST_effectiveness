package com.example.webserver.controller;

import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.model.Topic;
import com.example.webserver.respons.DeleteResponse;
import com.example.webserver.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class TopicController {

    @Autowired
    TopicService topicService;


    @GetMapping("/topic")
    public List<Topic> getAllTopic() {
        return topicService.findAll();
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(topicService.findById(id));
    }

    @PostMapping("/topic")
    public Topic createTopic(@RequestBody Topic topic){
        return  topicService.save(topic);
    }

    @DeleteMapping("/topic/{id}")
    @Transactional
    public ResponseEntity<DeleteResponse> deleteTopic(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        topicService.delete(topicService.findById(id));
        return ResponseEntity.ok(new DeleteResponse("Topic with id:"+id+" deleted"));
    }

    @DeleteMapping("/topic")

    public ResponseEntity<DeleteResponse> deleteTopicAll() throws ResourceNotFoundException {
        topicService.deleteAll();
        return ResponseEntity.ok(new DeleteResponse("Topic deleted"));
    }
    @PatchMapping(value = "/topic/{id}")
    public ResponseEntity<Topic> patchTopic(@PathVariable Long id, @RequestBody Topic topic) throws ResourceNotFoundException {
        return ResponseEntity.ok(topicService.updateTopic(topic,id));
    }

    @PutMapping("/topic/{id}")
    public ResponseEntity<Topic> putTopic(@PathVariable Long id,@RequestBody Topic req) throws ResourceNotFoundException {
        return ResponseEntity.ok(topicService.putMet(id,req));
    }

}














