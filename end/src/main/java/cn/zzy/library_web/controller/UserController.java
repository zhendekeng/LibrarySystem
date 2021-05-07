package cn.zzy.library_web.controller;


import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @UserLoginToken
    @GetMapping(value = "/userInfo")
    public ResponseData getUserInfo(HttpServletRequest request){
        int accountId = JWTHS256.getTokenUserId(request);
        String result = "fail";
        User user = userService.getUserById(accountId);
        if (user != null) {
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")){
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("userInfo",user);
            return responseData;
        }
        return ResponseData.notFound();
    }
}
