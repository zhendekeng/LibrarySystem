package cn.zzy.library_web.controller;


import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseData login(String userName,String password){
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
        ResponseData responseData = null;
        if (result.equals("success")) {
            // 生成token
            String token = JWTHS256.generateToken(String.valueOf(user.getId()), "Library-Security-Demo", user.getUserName());
            responseData = ResponseData.ok();
            responseData.putDataValue("token",token);
        }else if(result.equals("incorrect")){
            responseData = ResponseData.passIncorrect();
        }else {
            responseData = ResponseData.notFound();
        }
        return responseData;
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}
