package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.BookDetial;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;

import java.util.List;

public interface BookService {
    public List<BookType> getBookTypeList();
    public List<BookInfo> getALlBookList(int userId);
    public BookDetial getBookDetail(int id);
    public List<BookInfo> getSingleBookList(int typeId,int userId);
    public List<BookInfo> getSearchBookList(String info,int userId);
    public List<BookDetial> getSingleLendBookList(int userId);
    public List<BookDetial> getSearchSingleLendBookList(String info,int userId);
}
