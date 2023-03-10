package com.example.crud.Service;

import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUser();
    public UserDto getUserById(int id);
    public List<UserDto> searchUser(String keyword);
    public UserDto createUser(CreateUserReq req);
    public UserDto updateUser(UpdateUserReq req, int id);
    public void deleteUser(int id);
    public boolean check(String email,String password);


}
