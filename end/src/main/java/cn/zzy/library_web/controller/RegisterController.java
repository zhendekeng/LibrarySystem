package cn.zzy.library_web.controller;

import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public ResponseData register(@RequestBody User user){
        if (userService.addUser(user)){
            return ResponseData.ok();
        }
        else {
            return ResponseData.userExist();
        }
    }

}
