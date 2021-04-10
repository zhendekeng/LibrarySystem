package cn.zzy.library_web.controller;

import cn.zzy.library_web.entity.BookDetial;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.service.BookService;
import cn.zzy.library_web.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class ModifyController {

    @Autowired
    private UserService userService;
    @PutMapping(value = "/modifyUserInfo")
    public String modifyUserInfo(int userId,String email,String nickName){
        HashMap<String,Object> data = new HashMap<>();
        String result = "fail";
        if (userService.modifyUserInfo(userId,email,nickName)){
            result = "success";
        }else {
            result = "error";
        }
        data.put("result",result);
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/modifyPass")
    public String modifyPass(int userId,String oldPass,String newPass){
        HashMap<String,Object> data = new HashMap<>();
        String result = "fail";
        if (userService.checkPass(userId,oldPass)){
            if (userService.modifyPass(userId,newPass)) {
                result = "success";
            }else {
                result = "Error";
            }
        }else {
            result = "passError";
        }
        data.put("result",result);
        return JSON.toJSONString(data);
    }
}
