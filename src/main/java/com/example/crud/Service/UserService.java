package com.example.crud.Service;

import com.example.crud.entity.Users;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.CreateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public List<UserDto> getListUser();
    public UserDto getUserByEmail(String email);
    public List<UserDto> searchUser(String keyword);
    public UserDto createUser(CreateUserReq req);
    public UserDto updateUser(UserDto req, int id);
    public void deleteUser(int id);
    public Optional<Users> check(String email, String password);

    public UserDto getUserById(int id);


}
