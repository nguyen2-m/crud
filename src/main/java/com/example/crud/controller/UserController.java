package com.example.crud.controller;

import com.example.crud.Service.UserService;
import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
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

    @GetMapping("")
    public ResponseEntity<?> getListUser(){
        List<UserDto> user = userService.getListUser();
        return ResponseEntity.ok(user);
    }
    @PostMapping("")
    public ResponseEntity<?> creatUser(){
        return null;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(){
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(){
        return null;
    }
}
