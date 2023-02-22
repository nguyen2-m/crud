package com.example.crud.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserReq {

    private String name;
    private String email;
    private String password;
    private String phone;

}
