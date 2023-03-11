package com.example.crud.controller;

import com.example.crud.Service.ImageDataService;
import com.example.crud.Service.UserService;
import com.example.crud.entity.Image;
import com.example.crud.entity.Users;
import com.example.crud.exception.InternalServerException;
import com.example.crud.exception.NotFoundException;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.CreateUserReq;
import com.example.crud.request.LoginReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
//@SessionAttributes("session")
@RequestMapping("/users")
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
    public String information(Model model, HttpSession session) {
//        UserDto data = (UserDto)session.getAttribute("user");
        UserDto data = userService.getUserById((int)session.getAttribute("id"));
        System.out.println(data.getName());
//        System.out.println(data.getBirthday());

        if(data != null){
            model.addAttribute("users",data);
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

    private static String UPLOAD_DIR = System.getProperty("user.home") + "/media/upload"; //tạo đường dẫn foder
    @Autowired
    private HttpServletRequest request;

//    @PostMapping("/save")
//    public String updateUser(@Valid @ModelAttribute UserDto req, @RequestParam("avatar_file") MultipartFile file) {
//            File uploadDir = new File(UPLOAD_DIR); //tạo file upploadDir có đường dẫn .../media/upload";
//            if (!uploadDir.exists()) {              //nếu file chưa tồn tại.
//                uploadDir.mkdirs();                 // tạo file đường dẫn tới file
//            }
//            String originalFilename = file.getOriginalFilename();   // lấy được tên file
//            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);// trả về chỉ số index của dấu . xong + 1 mục đích lấy đuôi file
//            if (originalFilename != null && originalFilename.length() > 0) {    //kiểm tra tên file khác null, và độ dài tên file lớn hơn 0
//                if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
////                  nếu đuôi file ảnh khác những đuôi này thì ngoại lệ,
//                    throw new NotFoundException("dgd");
//                }
//                try {
//
////                    img.setId(Long.valueOf(UUID.randomUUID().toString())); //set ID bị lỗi,không hiểu lắm
//                    String link = "/users/media/static/" + req.getId() + "." + extension; //khởi tạo đường link để gọi tới hàm lấy ảnh
//                    // Create file
//                    File serverFile = new File(UPLOAD_DIR + "/" + req.getId() + "." + extension); //tạo đương dẫn file theo id
//                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
////                  FileOutputStream để ghi dữ liệu dạng byte từ file.BufferedOutputStream giúp ghi dữ liệu  dạngbyte hiệu quả hơn. ->>không hiểu lắm.
//                    stream.write(file.getBytes()); //chuyển về dạng byte
//
//                    stream.close(); //đóng
//                    req.setAvatar(link); //set link
//                    userService.updateUser(req, req.getId()); //update vào database
//                } catch (Exception e) {
//                    throw new InternalServerException("Lỗi khi upload file");
//                }
//            }
////            throw new NotFoundException("File không hợp lệ");
//            return "redirect:/users/information";
//    }

    @PostMapping("/saveimage")
    public String updateUser(@Valid @ModelAttribute UserDto req, @RequestParam("inpFile") MultipartFile file, HttpSession session) {
        File uploadDir = new File(UPLOAD_DIR); //tạo file upploadDir có đường dẫn .../media/upload";
        if (!uploadDir.exists()) {              //nếu file chưa tồn tại.
            uploadDir.mkdirs();                 // tạo file đường dẫn tới file
        }
        String originalFilename = file.getOriginalFilename();   // lấy được tên file
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);// trả về chỉ số index của dấu . xong + 1 mục đích lấy đuôi file
        if (originalFilename != null && originalFilename.length() > 0) {    //kiểm tra tên file khác null, và độ dài tên file lớn hơn 0
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
//                  nếu đuôi file ảnh khác những đuôi này thì ngoại lệ,
                throw new NotFoundException("dgd");
            }
            try {

//                    img.setId(Long.valueOf(UUID.randomUUID().toString())); //set ID bị lỗi,không hiểu lắm
                String link = "/users/media/static/" + req.getId() + "." + extension; //khởi tạo đường link để gọi tới hàm lấy ảnh
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + req.getId() + "." + extension); //tạo đương dẫn file theo id
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                  FileOutputStream để ghi dữ liệu dạng byte từ file.BufferedOutputStream giúp ghi dữ liệu  dạngbyte hiệu quả hơn. ->>không hiểu lắm.
                stream.write(file.getBytes()); //chuyển về dạng byte

                stream.close(); //đóng
                req.setAvatar(link); //set link
                System.out.println(session.getAttribute("id"));
                userService.updateImageUser(req, (int)session.getAttribute("id")); //update vào database
            } catch (Exception e) {
                throw new InternalServerException("Lỗi khi upload file");
            }
        }
//            throw new NotFoundException("File không hợp lệ");
        return "redirect:/users/information";
    }

    @GetMapping("/media/static/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {  //lấy ra tên file.
        File file = new File(UPLOAD_DIR + "/" + filename);      //lấy ra đường dẫn
        if (!file.exists()) {         //nếu không tồn tại==> ngoại lệ
            throw new NotFoundException("File không tồn tại");
        }
        UrlResource resource; //khai báo biến để lưu URL
        try {
            resource = new UrlResource(file.toURI()); //lưu URL
        } catch (MalformedURLException e) {
            throw new NotFoundException("File không tồn tại");
        }
        return ResponseEntity.ok()  //trả về ok với đường link ảnh.
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);

    }

    @GetMapping("/main")
    public String index(){
        return "index";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginReq user, Model model,HttpSession session) {

        String email = user.getEmail();
        String password = user.getPassword();

        Optional<Users> result = userService.check(email,password);
//        User userSession = result.get();

        if(result==null){
            model.addAttribute("isLoginFailure", false);
            model.addAttribute("isLoginFailure1", true);
//            return index();
        }else{
//
            session.setAttribute("id",result.get().getId());
            model.addAttribute("isLoginFailure", true);
            model.addAttribute("isLoginFailure1", false);
            model.addAttribute("name",result.get().getName());
            model.addAttribute("avatar",result.get().getAvatar());
            System.out.println(result.get().getId());
            System.out.println(result.get().getAvatar());
        }
        return "index";
    }

}