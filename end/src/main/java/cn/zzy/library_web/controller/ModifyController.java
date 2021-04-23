package cn.zzy.library_web.controller;


import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class ModifyController {

    @Autowired
    private UserService userService;
    @PutMapping(value = "/modifyUserInfo")
    public ResponseData modifyUserInfo(String email, String nickName, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        HashMap<String,Object> data = new HashMap<>();
        String result = "fail";
        if (userService.modifyUserInfo(userId,email,nickName)){
            return ResponseData.ok();
        }
        return ResponseData.notFound();
    }
    @PostMapping(value = "/modifyPass")
    public ResponseData modifyPass(String oldPass,String newPass,HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        HashMap<String,Object> data = new HashMap<>();
        String result = "fail";
        if (userService.checkPass(userId,oldPass)){
            if (userService.modifyPass(userId,newPass)) {
                return ResponseData.ok();
            }else {
                return ResponseData.notFound();
            }
        }else {
            return ResponseData.passIncorrect();
        }
    }
}
