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
@Table(name="rest_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String commentTitle;
    @Column(columnDefinition="text")
    private String commentDesc;
    @Column
    private Date commentDate;
    @Column
    private String helpful;

    @ManyToOne
    private User user;
    @ManyToOne
    private Topic topic;

}
