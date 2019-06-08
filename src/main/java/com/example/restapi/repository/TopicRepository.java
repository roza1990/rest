package com.example.restapi.repository;

import com.example.restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Post, Integer> {
}
