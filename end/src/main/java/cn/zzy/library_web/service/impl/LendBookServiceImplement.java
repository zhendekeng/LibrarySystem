package cn.zzy.library_web.service.impl;

import cn.zzy.library_web.dao.BookDao;
import cn.zzy.library_web.dao.LendBookDao;
import cn.zzy.library_web.entity.LendInfo;
import cn.zzy.library_web.service.LendBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
//mybatis事务管理
@Transactional
@Service
public class LendBookServiceImplement implements LendBookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private LendBookDao lendBookDao;
    @Override
    public boolean lendBook(int bookId, int userId) {
        Date now = new Date();
        Timestamp date =  new Timestamp(now.getTime());
        // 如果已经借过了就不能借了
        if (lendBookDao.lendBookExist(userId,bookId)) return false;
        if (lendBookDao.lendBook(bookId,userId,date) && bookDao.reduceBookNumber(bookId)){
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(int bookId, int userId) {
        Date now = new Date();
        Timestamp date =  new Timestamp(now.getTime());
        // 如果没有借过，那就不能归还
        if (!lendBookDao.returnBookExist(userId,bookId)) return false;
        if (lendBookDao.returnBook(bookId,userId,date) && bookDao.increaseBookNumber(bookId)){
            return true;
        }
        return false;
    }


    @Override
    public List<LendInfo> getSearchOnePeopleLendLog(String info, int userId) {
        List<LendInfo> lendInfoList = lendBookDao.getSearchOnePeopleLendLog(info,userId);
        for (LendInfo lendInfo : lendInfoList){
            if (lendInfo.getBackDate() == null){
                lendInfo.setState("未还");
            }else {
                lendInfo.setState("已还");
            }
        }
        return lendInfoList;
    }
}
