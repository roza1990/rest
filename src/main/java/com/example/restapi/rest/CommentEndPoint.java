package com.example.restapi.rest;

import com.example.restapi.model.Comment;
import com.example.restapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentEndPoint {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/api/forumComment/getTopicComments/{postId}")
    public ResponseEntity getAllComments(@PathVariable("postId") int postId) {
        return ResponseEntity.ok(commentRepository.findCommentByPostId(postId));
    }

    @PostMapping("api/forumComment/addComment")
    public ResponseEntity addComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/forumComment/makeCommentHelpful")
    public ResponseEntity update(@RequestBody Comment comment) {
        if (commentRepository.findById(comment.getId()).isPresent()) {
            commentRepository.save(comment);
            return ResponseEntity
                    .ok(comment);
        }
        return ResponseEntity.notFound().build();
    }

}
