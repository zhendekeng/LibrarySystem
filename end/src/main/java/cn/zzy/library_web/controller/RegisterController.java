package cn.zzy.library_web.controller;

import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public String register(@RequestBody User user){
        String result = "fail";
        System.out.println(user);
        if (userService.getUserByName(user.getUserName()) == null){
            Date date = new Date();
            Timestamp timestamp =  new Timestamp(date.getTime());
            user.setRegisterTime(timestamp);
            userService.addUser(user);
            result = "success";
        }
        else {
            result = "exist";
        }
        HashMap<String,Object> results = new HashMap<>();
        results.put("result",result);
        return JSON.toJSONString(results);
    }

}
