package com.example.crud.Service;

import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
import com.example.crud.model.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceIml implements UserService {
    private static ArrayList<User> user = new ArrayList<User>();

    static {
        user.add(new User(1,"hoa nguyen","nguyenhoa@gmail.com","09065953782","avatar","123"));
        user.add(new User(2,"hoa dang","nguyenhoa@gmail.com","09065953782","avatar","123"));
        user.add(new User(3,"hoa than","nguyenhoa@gmail.com","09065953782","avatar","123"));
        user.add(new User(4,"hoa mo","nguyenhoa@gmail.com","09065953782","avatar","123"));

    }

    @Override
    public List<UserDto> getListUser() {
        List<UserDto> result = new ArrayList<UserDto>();
        for(User user : user){
            result.add(UserMapper.toUserDto(user));
        }
        return result;
    }
}
