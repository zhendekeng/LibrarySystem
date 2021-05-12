package cn.zzy.library_web.controller;

import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.LendLog;
import cn.zzy.library_web.entity.StoreLog;
import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// 跨域请求
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @UserLoginToken
    @GetMapping(value = "storeLog")
    public ResponseData getStoreLog(String info){
        info = "%" + info + "%";
        List<StoreLog> storeLogList = logService.getStoreLog(info);
        ResponseData responseData = null;
        if (storeLogList != null) {
            responseData = ResponseData.ok();
            responseData.putDataValue("storeLogList",storeLogList);
        }else {
            responseData = ResponseData.notFound();
        }

        return responseData;
    }
    @UserLoginToken
    @GetMapping(value = "allLendLog")
    public ResponseData getLendLog(String info){
        info = "%" + info + "%";
        List<LendLog> lendLogList = logService.getLendLog(info);
        ResponseData responseData = null;
        if (lendLogList != null) {
            responseData = ResponseData.ok();
            responseData.putDataValue("lendLogList",lendLogList);
        }else {
            responseData = ResponseData.notFound();
        }
        return responseData;
    }
}
