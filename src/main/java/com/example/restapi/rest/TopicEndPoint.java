package com.example.restapi.rest;

import com.example.restapi.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicEndPoint {
    @Autowired
    private TopicRepository topicRepository;



    @GetMapping("/api/forumTopic/getTopicById/{postId}")
    public ResponseEntity getTopicById(@PathVariable("postId") int postId) {
        return ResponseEntity.ok(topicRepository.findById(postId));
    }


}
