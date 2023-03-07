package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private Date createdAt;
    private String description;
    private Date modifiedAt;
    private Date publishedAt;
    private String slug;
    private int status;
    private String thumbnail;
    private String title;
    @OneToOne
    @JoinColumn(name = "created_by")
    private Users users;
    @OneToOne
    @JoinColumn(name = "modified_by")
    private Users modified_by;

}
