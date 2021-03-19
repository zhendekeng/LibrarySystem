package cn.zzy.library_web.service.impl;

import cn.zzy.library_web.dao.BookDao;
import cn.zzy.library_web.dao.LendBookDao;
import cn.zzy.library_web.entity.BookDetial;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.service.BookService;
import cn.zzy.library_web.service.LendBookService;
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
    public List<BookType> getBookTypeList() {
        return bookDao.getBookTypeList();
    }

    @Override
    public List<BookInfo> getALlBookList(int userId) {
        List<BookInfo> bookInfoList = bookDao.getALlBookList();
        List<Integer> integerList = lendBookDao.getLendIdList(userId);
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
    public BookDetial getBookDetail(int id) {
        return bookDao.getBookDetail(id);
    }

    @Override
    public List<BookInfo> getSingleBookList(int typeId,int userId) {
        List<BookInfo> bookInfoList = bookDao.getSingleBookList(typeId);
        List<Integer> integerList = lendBookDao.getLendIdList(userId);
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
    public List<BookInfo> getSearchBookList(String info,int userId) {
        List<BookInfo> bookInfoList = bookDao.getSearchBookList(info);
        List<Integer> integerList = lendBookDao.getLendIdList(userId);
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
    public List<BookDetial> getSingleLendBookList(int userId) {
        List<Integer> integerList = lendBookDao.getLendIdList(userId);
        List<BookDetial> bookDetialList = new ArrayList<>();
        for (int k : integerList){
            bookDetialList.add(bookDao.getBookDetail(k));
        }
        return bookDetialList;
    }

    @Override
    public List<BookDetial> getSearchSingleLendBookList(String info, int userId) {
        List<Integer> bookIntegerList = lendBookDao.getLendIdListByInfo(info);
        List<Integer> integerList = lendBookDao.getLendIdList(userId);
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
        List<BookDetial> bookDetialList = new ArrayList<>();
        for (int k : integerList){
            bookDetialList.add(bookDao.getBookDetail(k));
        }
        return bookDetialList;
    }

}
