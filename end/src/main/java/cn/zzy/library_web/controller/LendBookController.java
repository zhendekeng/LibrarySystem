package cn.zzy.library_web.controller;


import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.LendInfo;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.LendBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class LendBookController {

    @Autowired
    private LendBookService lendBookService;

    @UserLoginToken
    @PostMapping(value = "/lendBook")
    public ResponseData lendBook(int bookId, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        String result = "fail";
        Date now = new Date();
        Timestamp date =  new Timestamp(now.getTime());
        if (lendBookService.lendBook(bookId,userId,date)) {
            return ResponseData.ok();
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @PostMapping(value = "/returnBook")
    public ResponseData returnBook(int bookId, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        System.out.println("userId = " + userId);
        String result = "fail";
        Date now = new Date();
        Timestamp date =  new Timestamp(now.getTime());
        if (lendBookService.returnBook(bookId,userId,date)) {
            return ResponseData.ok();
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @GetMapping(value = "/lendLog")
    public ResponseData getSearchLendBookList(String info, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        info = "%" + info + "%";
        String result = "fail";
        List<LendInfo> lendInfoList = lendBookService.getSearchLendLogList(info,userId);
        if (lendInfoList != null) {
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")) {
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("lendInfoList",lendInfoList);
            return  responseData;
        }
        return ResponseData.notFound();
    }

}
