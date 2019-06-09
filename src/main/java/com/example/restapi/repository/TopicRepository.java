package com.example.restapi.repository;

import com.example.restapi.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    List<Topic> findTopicByUserId(int  userId);
}
