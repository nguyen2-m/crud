package com.example.crud.Service;

import com.example.crud.entity.User;
import com.example.crud.exception.DuplicateRecordException;
import com.example.crud.exception.NotFoundException;
import com.example.crud.model.dto.UserDto;
import com.example.crud.model.mapper.UserMapper;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.UpdateUserReq;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceIml implements UserService {
    public static ArrayList<User> user = new ArrayList<User>();

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

    @Override
    public UserDto getUserById(int id) {
        for(User user : user){
            if(user.getId()==id){
                return UserMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("User khong ton tai trong he thong");
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<UserDto> result = new ArrayList<>();
        for (User user : user){
            if (user.getName().contains(keyword)){
                result.add(UserMapper.toUserDto(user));
            }
        }
        return result;
    }

    @Override
    public UserDto createUser(CreateUserReq req) {
        User user1 = new User();
        user1.setId(user.size() + 1 );
        user1.setEmail(req.getEmail());
        user1.setName(req.getName());
        user1.setPhone(req.getPhone());
        user1.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        System.out.println(user1.getPassword());
        user.add(user1);
        return UserMapper.toUserDto(user1);
    }

    @Override
    public UserDto updateUser(UpdateUserReq req, int id) {
        for (User user2 : user) {
            if (user2.getId() == id) {
                if (!user2.getEmail().equals(req.getEmail())) {
                    // Check new email exist
                    for (User tmp : user) {
                        if (tmp.getEmail().equals(req.getEmail())) {
                            throw new DuplicateRecordException("New email already exists in the system");
                        }
                    }
                    user2.setEmail(req.getEmail());
                }
                user2.setName(req.getName());
                user2.setPhone(req.getPhone());
                user2.setAvatar(req.getAvatar());
                user2.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
                return UserMapper.toUserDto(user2);
            }
        }

        throw new NotFoundException("No user found");
    }

    @Override
    public boolean deleteUser(int id) {
        for (User user3 : user) {
            if (user3.getId() == id) {
                user.remove(user3);
                return true;
            }
        }

        throw new NotFoundException("No user found");
    }
}














