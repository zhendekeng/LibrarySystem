package cn.zzy.library_web.controller;


import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8889", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/userInfo")
    public String getUserInfo(int userId){
        String result = "fail";
        User user = userService.getUserById(userId);
        if (user != null) {
            result = "success";
        }else {
            result = "error";
        }
        HashMap<String,Object> results = new HashMap<>();
        results.put("result",result);
        if (result.equals("success")){
            results.put("userInfo",user);
        }
        return JSON.toJSONString(results);
    }
}
