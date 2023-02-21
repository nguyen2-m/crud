package com.example.crud.Service;

import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getListUser();
}
