package com.example.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto  implements Serializable {

    private int id;
    private String name;
    private String email;

    private String phone;

    private String avatar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;




}
