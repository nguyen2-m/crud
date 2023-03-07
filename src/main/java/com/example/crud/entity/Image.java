package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String name;

    private String link;


    private String type;

    //    @Lob
//    @Column(name = "image", length = 1000)
//    private byte[] imageData;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;


}














