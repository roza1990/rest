package com.example.restapi.repository;

import com.example.restapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByPostId(int  postId);
}
