package com.example.crud.Service;

import com.example.crud.entity.User;
import com.example.crud.exception.InternalServerException;
import com.example.crud.exception.NotFoundException;
import com.example.crud.model.dto.UserDto;
import com.example.crud.model.mapper.UserMapper;
import com.example.crud.repository.UserRepository;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.UpdateUserReq;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceIml implements UserService {
//    public static ArrayList<User> user = new ArrayList<User>();

//    static {
//        user.add(new User(1,"hoa nguyen","nguyenhoa@gmail.com","09065953782","avatar","123"));
//        user.add(new User(2,"hoa dang","nguyenhoa@gmail.com","09065953782","avatar","123"));
//        user.add(new User(3,"hoa than","nguyenhoa@gmail.com","09065953782","avatar","123"));
//        user.add(new User(4,"hoa mo","nguyenhoa@gmail.com","09065953782","avatar","123"));
//
//    }
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getListUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> result = new ArrayList<UserDto>();
        for(User user : users){
            result.add(UserMapper.toUserDto(user));
        }
        return result;

    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isEmpty()){
            throw new NotFoundException("User khong ton tai trong he thong");
//            if(user.getId()==id){
//                return UserMapper.toUserDto(user);
//            }
        }
//        throw new NotFoundException("User khong ton tai trong he thong");
        return UserMapper.toUserDto(user.get());
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users){
            if (user.getName().contains(keyword)){
                result.add(UserMapper.toUserDto(user));
            }
        }
        return result;

    }

    @Override
    public UserDto createUser(CreateUserReq req) {
        User rs = userRepository.findByEmail(req.getEmail());
        if(rs != null) {
            throw new InternalServerException("Email is already in system");
        }
//        User user1 = new User(result.get().getId(), result.get().getName(), result.get().getEmail(), result.get().getPhone(), result.get().getAvatar());
//
//        user1.setEmail(req.getEmail());
//        user1.setName(req.getName());
//        user1.setPhone(req.getPhone());
//
//        user1.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
//
//        userRepository.save(user1);
        return null;
    }

    @Override
    public UserDto updateUser(UserDto req, int id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("User khong ton tai trong he thong");

        }
        User rs = userRepository.findByEmail(req.getEmail());
//        if(rs != null) {
//            throw new InternalServerException("Email is already in system");
//        }
        User user = result.get();
        user.setEmail(req.getEmail());
        user.setBirthday(req.getBirthday());
        user.setName(req.getName());
        user.setPhone(req.getPhone());
//        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
        user.setAvatar(req.getAvatar());

        try {
            userRepository.save(user);
        }catch (Exception ex){
            throw new InternalServerException("Database error. Can't update user");
        }
        return UserMapper.toUserDto(user);
//        for (User user2 : user) {
//            if (user2.getId() == id) {
//                if (!user2.getEmail().equals(req.getEmail())) {
//                    // Check new email exist
//                    for (User tmp : user) {
//                        if (tmp.getEmail().equals(req.getEmail())) {
//                            throw new DuplicateRecordException("New email already exists in the system");
//                        }
//                    }
//                    user2.setEmail(req.getEmail());
//                }
//                user2.setName(req.getName());
//                user2.setPhone(req.getPhone());
//                user2.setAvatar(req.getAvatar());
//                user2.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
//                return UserMapper.toUserDto(user2);
//            }
//        }
//
//        throw new NotFoundException("No user found");
//        return null;
    }

    @Override
    public void deleteUser(int id) {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("No user found");
        }
        try{
            userRepository.deleteById(id);
        }
        catch (Exception ex){
            throw new InternalServerException("Database error. Can't update user");
        }
//        for (User user3 : user) {
//            if (user3.getId() == id) {
//                user.remove(user3);
//                return true;
//            }
//        }
//
//        throw new NotFoundException("No user found");

    }

    @Override
    public Optional<User> check(String email, String password) {

//        List<User> users = userRepository.findAll();
//        for (User user : users){
//            if (user.getEmail().equals(email)&&user.getPassword().equals(password)){
//                return true;
//            }
//        }
        User user = userRepository.findByEmail(email);
        return user!=null && BCrypt.checkpw(password, user.getPassword()) ? Optional.of(user) : null;

    }

}














