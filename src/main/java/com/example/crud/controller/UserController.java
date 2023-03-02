package com.example.crud.controller;

import com.example.crud.Service.UserService;
import com.example.crud.entity.User;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.LoginReq;
import com.example.crud.request.UpdateUserReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
//@SessionAttributes("session")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    HttpSession session;


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

    @GetMapping("/information")
    public String getUserByEmail(Model model, HttpSession session) {
        UserDto data = (UserDto)session.getAttribute("user");

        if(data != null){
            model.addAttribute("user",data);
        }
//        UserDto result = userService.getUserByEmail((String) session.getAttribute("username"));
//        System.out.println(result);
//        model.addAttribute("name",result.getName());
//        model.addAttribute("phone",result.getPhone());
//        model.addAttribute("birthday",result.getBirthday());
//        model.addAttribute("avatar",result.getAvatar());
        return "information";
    }

    @PostMapping("/register")
    public String creatUser(@ModelAttribute CreateUserReq req, Model model,HttpSession session) {
        UserDto result = userService.createUser(req);
//        session.setAttribute("useremail",req.getEmail());
        LoginReq loginReq= new LoginReq();
        loginReq.setEmail(req.getEmail());
        loginReq.setPassword(req.getPassword());
        return login(loginReq,model,session);
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
    @GetMapping("/main")
    public String index(){
        return "index";

    }


    @PostMapping("/login")
    public String login(@ModelAttribute LoginReq user, Model model,HttpSession session) {

        String email = user.getEmail();
        String password = user.getPassword();

        Optional<User> result = userService.check(email,password);
        if(result==null){

            return mainLogin(user, model);

        }else{
            session.removeAttribute("user");
            session.setAttribute("user", new UserDto(result.get().getId(),result.get().getName(), result.get().getEmail(), result.get().getPhone(),result.get().getAvatar(),result.get().getBirthday()));
//            UserDto data = (UserDto)session.getAttribute("user");
            model.addAttribute("isLoginFailure", true);
            model.addAttribute("isLoginFailure1", false);
            model.addAttribute("name",result.get().getName());
//            System.out.println(result.get());
            return "index";
        }
    }

}