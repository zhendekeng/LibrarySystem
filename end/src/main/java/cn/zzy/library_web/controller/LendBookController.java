package cn.zzy.library_web.controller;

import cn.zzy.library_web.entity.BookDetial;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.entity.LendInfo;
import cn.zzy.library_web.service.BookService;
import cn.zzy.library_web.service.LendBookService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8889", maxAge = 3600)
@RestController
public class LendBookController {

    @Autowired
    private LendBookService lendBookService;
    @PutMapping(value = "/lendBook")
    public String lendBook(int bookId,int userId){
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        Date now = new Date();
        Timestamp date =  new Timestamp(now.getTime());
        if (lendBookService.lendBook(bookId,userId,date)) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        return JSON.toJSONString(data);
    }
    @PutMapping(value = "/returnBook")
    public String returnBook(int bookId,int userId){
        System.out.println("userId = " + userId);
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        Date now = new Date();
        Timestamp date =  new Timestamp(now.getTime());
        if (lendBookService.returnBook(bookId,userId,date)) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/singleLendLogList")
    public String getLendList(int userId){
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<LendInfo> lendInfoList = lendBookService.getSingleLendList(userId);
        if (lendInfoList != null){
            result = "success";
        }else {
            result = "error";
        }
        data.put("result",result);
        if (result.equals("success")){
            data.put("singleLendLogList",lendInfoList);
        }
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/searchLendLogList")
    public String getSearchLendBookList(String info,int userId){
        info = "%" + info + "%";
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<LendInfo> lendInfoList = lendBookService.getSearchLendLogList(info,userId);
        if (lendInfoList != null) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        if (result.equals("success")) {
            data.put("searchLendLogList", lendInfoList);
        }
        return JSON.toJSONString(data);
    }

}
