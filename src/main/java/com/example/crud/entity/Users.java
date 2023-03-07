package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "full_name")
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone",length = 11)
    private String phone;
    @Column(name = "avatar",nullable = true)
    private String avatar;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "role",columnDefinition = "varchar(255) default 'USER'")
    private  String role;

    @OneToMany(mappedBy = "users")
    private List<Image> image;

}
