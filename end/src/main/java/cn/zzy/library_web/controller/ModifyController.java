package cn.zzy.library_web.controller;


import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class ModifyController {

    @Autowired
    private UserService userService;
    @PutMapping(value = "/modifyUserInfo")
    public ResponseData modifyUserInfo(String userEmail, String userFullName, HttpServletRequest request){
        int accountId = JWTHS256.getTokenUserId(request);
        if (userService.modifyUserInfo(accountId,userEmail,userFullName)){
            return ResponseData.ok();
        }
        return ResponseData.notFound();
    }
    @PostMapping(value = "/modifyPass")
    public ResponseData modifyPass(@RequestBody Map<String,String> map, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        if (userService.checkPass(userId,map.get("oldPass"))){
            if (userService.modifyPass(userId,map.get("newPass"))) {
                return ResponseData.ok();
            }else {
                return ResponseData.notFound();
            }
        }else {
            return ResponseData.passIncorrect();
        }
    }
}
