package com.example.crud.controller;

import com.example.crud.Service.UserService;
import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
import com.example.crud.model.mapper.UserMapper;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.UpdateUserReq;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword", required = false,defaultValue = "") String name){
        List<UserDto> user = userService.searchUser(name);
        return ResponseEntity.ok(user);
    }
    @GetMapping("")
    public ResponseEntity<?> getListUser(){
        List<UserDto> user = userService.getListUser();
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        UserDto result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping("")
    public ResponseEntity<?> creatUser(@RequestBody CreateUserReq req){
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserReq req, @PathVariable int id){
        UserDto result = userService.updateUser(req, id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }
}
