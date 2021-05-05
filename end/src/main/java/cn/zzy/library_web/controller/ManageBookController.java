package cn.zzy.library_web.controller;

import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.jwt.JWTHS256;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class ManageBookController {
    @Autowired
    private BookService bookService;

    @UserLoginToken
    @GetMapping(value = "/bookTypeList")
    public ResponseData getBookTypeList(){
        HashMap<String,Object> data = new HashMap<>();
        String result = "fail";
        List<BookType> bookTypes = bookService.getBookTypeList();
        if (bookTypes != null){
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")){
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("bookTypeList",bookTypes);
            return responseData;
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @GetMapping(value = "/bookDetail")
    public ResponseData getBookDetail(int bookId){
        HashMap<String, Object> data = new HashMap<>();
        String result = "fail";
        BookDetail bookDetail = bookService.getBookDetail(bookId);
        if (bookDetail != null){
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")){
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("bookDetail",bookDetail);
            return responseData;
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @GetMapping(value = "/oneTypeBook")
    public ResponseData getSingleBookList(int typeId, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        String result = "fail";
        List<BookInfo> bookInfos = bookService.getSingleBookList(typeId,userId);
        if (bookInfos != null) {
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")) {
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("oneTypeBookList", bookInfos);
            return responseData;
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @GetMapping(value = "/searchAllBook")
    public ResponseData getSearchBookList(String info, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        info = "%" + info + "%";
        String result = "fail";
        List<BookInfo> bookInfos = bookService.getSearchBookList(info,userId);
        if (bookInfos != null) {
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")) {
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("searchAllBookList", bookInfos);
            return responseData;
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @GetMapping(value = "/searchLendBook")
    public ResponseData getSearchSingleLendBookList(String info, HttpServletRequest request){
        int userId = JWTHS256.getTokenUserId(request);
        info = "%" + info + "%";
        String result = "fail";
        List<BookDetail> bookDetialList = bookService.getSearchSingleLendBookList(info,userId);
        if (bookDetialList != null) {
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")) {
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("searchLendBookList", bookDetialList);
            return responseData;
        }
        return ResponseData.notFound();
    }
    @UserLoginToken
    @PostMapping(value = "/addBook")
    public ResponseData addBook(@RequestBody BookDetail bookDetail, HttpServletRequest request){
        int accountId = JWTHS256.getTokenUserId(request);
        System.out.println(bookDetail);
        String result = "fail";
        if (bookService.addBook(bookDetail,accountId)){
            result = "success";
        }else {
            result = "error";
        }
        if (result.equals("success")) {
            ResponseData responseData = ResponseData.ok();
            return responseData;
        }
        return ResponseData.notFound();
    }
}
