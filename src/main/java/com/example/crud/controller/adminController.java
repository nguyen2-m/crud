package com.example.crud.controller;

import com.example.crud.Service.AdminService;
import com.example.crud.entity.Product;
import com.example.crud.entity.Users;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.LoginReq;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public String login(@ModelAttribute LoginReq user, Model model, HttpSession session) {
        return null;
    }
    @GetMapping("/list")
    public ResponseEntity<?> getListUser() {
        List<Product> product = adminService.getListProduct();
        return ResponseEntity.ok(product);
    }
}
