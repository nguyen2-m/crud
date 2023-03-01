package com.example.crud.controller;

import com.example.crud.Service.UserService;
import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.LoginReq;
import com.example.crud.request.UpdateUserReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<UserDto> user = userService.searchUser(name);

        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDto> user = userService.getListUser();

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> creatUser(@RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserReq req, Errors errors, @PathVariable int id) {
        UserDto result = userService.updateUser(req, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/profile")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws Exception {
//        ImageUploadResponse response = imageDataService.uploadImage(file);
        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads/";
                String toUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(toUploads).exists()) {
                    new File(toUploads).mkdir();
                }

//                log.info("toUploads = {}", toUploads);
                System.out.println(toUploads);

                String orgName = file.getOriginalFilename();
                String filePath = toUploads + orgName;
                File dest = new File(filePath);
                file.transferTo(dest);
                System.out.println(filePath);
                return ResponseEntity.ok("Upload success");
            } finally {

            }
        }
        return null;
    }
    @GetMapping("/main")
    public String index(){
        return "index";
    }

    @GetMapping("/main/login")
    public String mainLogin(@ModelAttribute LoginReq loginReq, Model model){
        model.addAttribute("isLoginFailure", false);
        model.addAttribute("isLoginFailure1", true);
        return "index";

    }
    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("date", new Date());
        return "date";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute LoginReq user, Model model) {
        String email = user.getEmail();
        String password = user.getPassword();
//        System.out.println(email);
//        System.out.println(password);

        Optional<User> result = userService.check(email,password);
        if(result==null){
            System.out.println(email);
            return mainLogin(user, model);

        }else{
            model.addAttribute("isLoginFailure", true);
            model.addAttribute("isLoginFailure1", false);
            model.addAttribute("name",result.get().getName());
            return "index";
        }
    }

}