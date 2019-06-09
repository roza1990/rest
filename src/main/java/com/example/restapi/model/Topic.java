package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rest_topic")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String title;
  @Column(columnDefinition="text")
  private String description;
  @ManyToOne
  private User user;

//  @ManyToOne
//  private Comment comment;
  @Column
  private Date postDate;
  @Column
  private int view;
}

