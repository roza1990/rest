package com.example.restapi.rest;

import com.example.restapi.model.Topic;
import com.example.restapi.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicEndPoint {
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/api/forumTopic/getTopicById/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        Optional<Topic> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/api/forumTopic/getTopicByUserId/{userId}")
    public ResponseEntity getAllTopicByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(topicRepository.findTopicByUserId(userId));
    }


}
