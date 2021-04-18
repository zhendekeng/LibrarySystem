package cn.zzy.library_web.controller;

import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.BookDetial;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.service.BookService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class ManageBookController {
    @Autowired
    private BookService bookService;
    @GetMapping(value = "/bookTypeList")
    public String getBookTypeList(){
        HashMap<String,Object> data = new HashMap<>();
        String result = "fail";
        List<BookType> bookTypes = bookService.getBookTypeList();
        if (bookTypes != null){
            result = "success";
        }else {
            result = "error";
        }
        data.put("result",result);
        if (result.equals("success")){
            data.put("bookTypeList",bookTypes);
        }
        return JSON.toJSONString(data);
    }
    @UserLoginToken
    @GetMapping(value = "/allBookList")
    public String getAllBookList(HttpServletRequest request) {
        int userId = JWTHS256.getTokenUserId(request);
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<BookInfo> bookInfos = bookService.getALlBookList(userId);
        if (bookInfos != null) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        if (result.equals("success")) {
            data.put("allBookList", bookInfos);
        }
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/BookDetail")
    public String getBookDetail(int id){
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        BookDetial bookDetail = bookService.getBookDetail(id);
        if (bookDetail != null){
            result = "success";
        }else {
            result = "error";
        }
        data.put("result",result);
        if (result.equals("success")){
            data.put("bookDetail",bookDetail);
        }
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/singleBookList")
    public String getSingleBookList(int typeId,int userId){
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<BookInfo> bookInfos = bookService.getSingleBookList(typeId,userId);
        if (bookInfos != null) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        if (result.equals("success")) {
            data.put("singleBookList", bookInfos);
        }
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/searchBookList")
    public String getSearchBookList(String info,int userId){
        info = "%" + info + "%";
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<BookInfo> bookInfos = bookService.getSearchBookList(info,userId);
        if (bookInfos != null) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        if (result.equals("success")) {
            data.put("searchBookList", bookInfos);
        }
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/singleLendBookList")
    public String getSingleLendBookList(int userId){
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<BookDetial> bookDetialList = bookService.getSingleLendBookList(userId);
        if (bookDetialList != null) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        if (result.equals("success")) {
            data.put("singleLendBookList", bookDetialList);
        }
        return JSON.toJSONString(data);
    }
    @GetMapping(value = "/searchLendBookList")
    public String getSearchSingleLendBookList(String info,int userId){
        info = "%" + info + "%";
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        List<BookDetial> bookDetialList = bookService.getSearchSingleLendBookList(info,userId);
        if (bookDetialList != null) {
            result = "success";
        }else {
            result = "error";
        }
        data.put("result", result);
        if (result.equals("success")) {
            data.put("searchLendBookList", bookDetialList);
        }
        return JSON.toJSONString(data);
    }
}
