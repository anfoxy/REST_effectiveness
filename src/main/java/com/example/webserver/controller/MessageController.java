package com.example.webserver.controller;

import com.example.webserver.exception.ResourceNotFoundException;
import com.example.webserver.model.Message;
import com.example.webserver.respons.DeleteResponse;
import com.example.webserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MessageController {

    @Autowired
    MessageService messageService;


    @GetMapping("/message")
    public List<Message> getAllMessage() {
        return messageService.findAll();
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(messageService.findById(id));
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Message message){
        return  messageService.save(message);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<DeleteResponse> deleteMessage(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        messageService.deleteById(id);
        return ResponseEntity.ok(new DeleteResponse("Message with id:"+id+" deleted"));
    }

    @DeleteMapping("/message")
    public ResponseEntity<DeleteResponse> deleteMessageAll() throws ResourceNotFoundException {
        messageService.deleteAll();
        return ResponseEntity.ok(new DeleteResponse("Message deleted"));
    }

    @PatchMapping(value = "/message/{id}")
    public ResponseEntity<Message> patchMessage(@PathVariable Long id, @RequestBody Message message) throws ResourceNotFoundException {
        return ResponseEntity.ok(messageService.updateQuestion(message,id));
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> putMessage(@PathVariable Long id,@RequestBody Message req) throws ResourceNotFoundException {
        return ResponseEntity.ok(messageService.putMet(id,req));
    }
}














