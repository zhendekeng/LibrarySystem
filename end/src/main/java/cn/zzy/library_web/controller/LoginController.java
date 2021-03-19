package cn.zzy.library_web.controller;

import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.service.UserService;
import com.alibaba.fastjson.JSON;
import javafx.util.converter.ShortStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8889", maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/login")
    public String login(String userName,String password){
        System.out.println(userName);
        System.out.println(password);
        String result = "fail";
        User user = userService.getUserByName(userName);
        if (user != null){
            if (!user.getPassword().equals(password)){
                result = "incorrect";
            }
            else{
                result = "success";
            }
        }
        else {
            result = "noexist";
        }
        System.out.println(user);
        HashMap<String,Object> results = new HashMap<>();
        results.put("result",result);
        results.put("user",user);
        return JSON.toJSONString(results);
    }
}