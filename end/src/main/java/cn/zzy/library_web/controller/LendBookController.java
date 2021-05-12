package cn.zzy.library_web.controller;


import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.LendInfo;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.LendBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class LendBookController {

    @Autowired
    private LendBookService lendBookService;

    @UserLoginToken
    @PostMapping(value = "/lendBook")
    public ResponseData lendBook(@RequestBody Map<String,String> map, HttpServletRequest request){
      //  System.out.println("bookId = " + map);
        int accountId = JWTHS256.getTokenUserId(request);
        System.out.println("accountId = " + accountId);

        if (lendBookService.lendBook(Integer.parseInt(map.get("bookId")),accountId)) {
            return ResponseData.ok();
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @PostMapping(value = "/returnBook")
    public ResponseData returnBook(@RequestBody Map<String,String> map, HttpServletRequest request){
        int accountId = JWTHS256.getTokenUserId(request);
      //  System.out.println("userId = " + userId);
        if (lendBookService.returnBook(Integer.parseInt(map.get("bookId")),accountId)) {
            return ResponseData.ok();
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @GetMapping(value = "/lendLog")
    public ResponseData getSearchOnePeopleLendLog(String info, HttpServletRequest request){
        int accountId = JWTHS256.getTokenUserId(request);
        info = "%" + info + "%";
        String result = "fail";
        List<LendInfo> lendInfoList = lendBookService.getSearchOnePeopleLendLog(info,accountId);
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
