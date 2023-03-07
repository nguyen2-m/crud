package com.example.crud.model.mapper;

import com.example.crud.entity.Users;
import com.example.crud.model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(Users users){
        UserDto tmp = new UserDto();
        tmp.setId(users.getId());
        tmp.setName(users.getName());
        tmp.setEmail(users.getEmail());
        tmp.setPhone(users.getPhone());
        tmp.setAvatar(users.getAvatar());
        tmp.setBirthday(users.getBirthday());

        return tmp;
    }
}
