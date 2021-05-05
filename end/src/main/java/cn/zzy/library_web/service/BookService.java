package cn.zzy.library_web.service;



import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;

import java.util.List;

public interface BookService {
    public List<BookType> getBookTypeList();
    public BookDetail getBookDetail(int id);
    public List<BookInfo> getSingleBookList(int typeId,int userId);
    public List<BookInfo> getSearchBookList(String info,int userId);
    public List<BookDetail> getSearchSingleLendBookList(String info,int userId);
    public boolean addBook(BookDetail bookDetial,int adminId);
}
