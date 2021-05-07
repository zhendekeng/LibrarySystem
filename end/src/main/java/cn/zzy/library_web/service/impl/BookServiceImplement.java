package cn.zzy.library_web.service.impl;

import cn.zzy.library_web.dao.BookDao;
import cn.zzy.library_web.dao.LendBookDao;


import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//mybatis事务管理
@Transactional
@Service
public class BookServiceImplement implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private LendBookDao lendBookDao;
    @Override
    public List<BookType> getAllBookType() {
        return bookDao.getAllBookType();
    }



    @Override
    public BookDetail getOneBookDetail(int id) {
        return bookDao.getOneBookDetail(id);
    }

    @Override
    public List<BookInfo> getOneTypeBook(int typeId, int userId) {
        List<BookInfo> bookInfoList = bookDao.getOneTypeBook(typeId);

        List<Integer> integerList = lendBookDao.getOnePeopleBorrowBook(userId);
        for (BookInfo bookInfo : bookInfoList){
            int bookId = bookInfo.getId();
            boolean flag = false;
            for (Integer k : integerList){
                if (k == bookId){
                    bookInfo.setState("归还");
                    flag = true;
                    break;
                }
            }
            if (!flag){
                if (bookInfo.getNumber() == 0) {
                    bookInfo.setState("已空");
                }else {
                    bookInfo.setState("租借");
                }
            }
        }
        return bookInfoList;
    }

    @Override
    public List<BookInfo> getSearchAllBook(String info, int userId) {
        List<BookInfo> bookInfoList = bookDao.getSearchAllBook(info);
        List<Integer> integerList = lendBookDao.getOnePeopleBorrowBook(userId);
        for (BookInfo bookInfo : bookInfoList){
            int bookId = bookInfo.getId();
            boolean flag = false;
            for (Integer k : integerList){
                if (k == bookId){
                    bookInfo.setState("归还");
                    flag = true;
                    break;
                }
            }
            if (!flag){
                if (bookInfo.getNumber() == 0) {
                    bookInfo.setState("已空");
                }else {
                    bookInfo.setState("租借");
                }
            }
        }
        return bookInfoList;
    }


    @Override
    public List<BookDetail> getSearchOnePeopleBorrowBook(String info, int userId) {
        List<Integer> bookIntegerList = lendBookDao.getLendIdListByInfo(info);
        List<Integer> integerList = lendBookDao.getOnePeopleBorrowBook(userId);
        int index = 0;
        for (int k : integerList){
            boolean flag = false;
            for (int x : bookIntegerList){
                if (x == k){
                    flag = true;
                    break;
                }
            }
            if (!flag) integerList.remove(index);
            index++;
        }
        List<BookDetail> bookDetialList = new ArrayList<>();
        for (int k : integerList){
            bookDetialList.add(bookDao.getOneBookDetail(k));
        }
        return bookDetialList;
    }

    @Override
    public boolean addBook(BookDetail bookDetial,int adminId) {
        if (!bookDao.addBook(bookDetial)) return false;
       // System.out.println("bookId = " + bookDetial.getId());
        Date date = new Date();
        Timestamp timestamp =  new Timestamp(date.getTime());
        return bookDao.addStore(adminId,bookDetial.getId(),timestamp);
    }

}
