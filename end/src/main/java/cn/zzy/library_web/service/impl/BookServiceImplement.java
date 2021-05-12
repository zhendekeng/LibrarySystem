package cn.zzy.library_web.service.impl;

import cn.zzy.library_web.dao.BookDao;
import cn.zzy.library_web.dao.LendBookDao;


import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookType;
import cn.zzy.library_web.service.BookService;

import cn.zzy.library_web.service.UserService;
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
    @Autowired
    private UserService userService;
    @Override
    public List<BookType> getAllBookType() {
        return bookDao.getAllBookType();
    }



    @Override
    public BookDetail getOneBookDetail(int id) {
        return bookDao.getOneBookDetail(id);
    }

    @Override
    public List<BookDetail> getOneTypeBook(int typeId, int accountId) {
        List<BookType> bookTypeList = getAllBookType();
        List<BookDetail> bookDetailList = bookDao.getOneTypeBook(typeId);
        int userId = userService.getUserOrAdminId(accountId);
        List<Integer> integerList = lendBookDao.getOnePeopleBorrowBook(userId);
        for (BookDetail bookDetail : bookDetailList){
            int bookId = bookDetail.getId();
            boolean flag = false;
            for (Integer k : integerList){
                if (k == bookId){
                    bookDetail.setState("归还");
                    flag = true;
                    break;
                }
            }
            if (!flag){
                if (bookDetail.getNumber() == 0) {
                    bookDetail.setState("已空");
                }else {
                    bookDetail.setState("租借");
                }
            }
            // 把书籍类型编号改为类型名称
            for (BookType bookType : bookTypeList){
                if (bookDetail.getType().equals(String.valueOf(bookType.getId()))){
                    bookDetail.setType(bookType.getName());
                }
            }
        }
        return bookDetailList;
    }

    @Override
    public List<BookDetail> getSearchAllBook(String info, int accountId) {
        List<BookType> bookTypeList = getAllBookType();
        List<BookDetail> allBook = bookDao.getSearchAllBook(info);
        int userId = userService.getUserOrAdminId(accountId);
        List<Integer> integerList = lendBookDao.getOnePeopleBorrowBook(userId);
        for (BookDetail bookDetail : allBook){
            int bookId = bookDetail.getId();
            boolean flag = false;
            for (Integer k : integerList){
                if (k == bookId){
                    bookDetail.setState("归还");
                    flag = true;
                    break;
                }
            }
            if (!flag){
                if (bookDetail.getNumber() == 0) {
                    bookDetail.setState("已空");
                }else {
                    bookDetail.setState("租借");
                }
            }
            // 把书籍类型编号改为类型名称
            for (BookType bookType : bookTypeList){
                if (bookDetail.getType().equals(String.valueOf(bookType.getId()))){
                    bookDetail.setType(bookType.getName());
                }
            }
        }
        return allBook;
    }


    @Override
    public List<BookDetail> getSearchOnePeopleBorrowBook(String info, int accountId) {
        List<Integer> bookIntegerList = lendBookDao.getLendIdListByInfo(info);
        int userId = userService.getUserOrAdminId(accountId);
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
    public String addBook(BookDetail bookDetial,int accountId) {
        if (bookDao.checkBookExist(bookDetial.getISBN())) return "bookExist";
        if (!bookDao.addBook(bookDetial)) return "error";
        int adminId = userService.getUserOrAdminId(accountId);
       // System.out.println("bookId = " + bookDetial.getId());
        Date date = new Date();
        Timestamp timestamp =  new Timestamp(date.getTime());
        bookDao.addStore(adminId,bookDetial.getId(),timestamp);
        return "success";
    }

    @Override
    public boolean modifyBook(BookDetail bookDetail) {
        List<BookType> bookTypeList = getAllBookType();
        // 把书籍类型编号改为类型名称
        for (BookType bookType : bookTypeList){
            if (bookDetail.getType().equals(String.valueOf(bookType.getName()))){
                bookDetail.setType(String.valueOf(bookType.getId()));
            }
        }
        return bookDao.modifyBook(bookDetail);
    }

}
