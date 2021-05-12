package cn.zzy.library_web.service;



import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookType;

import java.util.List;

public interface BookService {
    public List<BookType> getAllBookType();

    public BookDetail getOneBookDetail(int id);

    public List<BookDetail> getOneTypeBook(int typeId, int accountId);
    public List<BookDetail> getSearchAllBook(String info, int accountId);
    public List<BookDetail> getSearchOnePeopleBorrowBook(String info, int accountId);
    public String addBook(BookDetail bookDetial,int accountId);
    public boolean modifyBook(BookDetail bookDetail);
}
