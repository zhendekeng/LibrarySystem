package cn.zzy.library_web.controller;


import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.Account;
import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseData login(@RequestBody Account account){
        String result = "fail";
       // System.out.println(account);
        String accountName = account.getAccountName();
        String accountPass = account.getAccountPass();
        Account loginAccount = userService.getAccountByName(accountName);
        System.out.println(loginAccount);

        if (loginAccount != null){
            if (!loginAccount.getAccountPass().equals(accountPass)){
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
            String token = JWTHS256.generateToken(String.valueOf(loginAccount.getAccountId()), "Library-Security-Demo", loginAccount.getAccountName());
            responseData = ResponseData.ok();
            responseData.putDataValue("token",token);
            responseData.putDataValue("role",String.valueOf(loginAccount.getAccountRole()));
        }else if(result.equals("incorrect")) {
            responseData = ResponseData.passIncorrect();
        }else if (result.equals("noexist")) {
            responseData = ResponseData.userNoExist();
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
