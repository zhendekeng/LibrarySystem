package cn.zzy.library_web.service;



import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;

import java.util.List;

public interface BookService {
    public List<BookType> getAllBookType();

    public BookDetail getOneBookDetail(int id);

    public List<BookInfo> getOneTypeBook(int typeId, int userId);
    public List<BookInfo> getSearchAllBook(String info, int userId);
    public List<BookDetail> getSearchOnePeopleBorrowBook(String info, int userId);
    public boolean addBook(BookDetail bookDetial,int adminId);
}
