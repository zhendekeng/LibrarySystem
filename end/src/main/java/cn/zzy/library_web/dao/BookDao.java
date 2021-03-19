package cn.zzy.library_web.dao;

import cn.zzy.library_web.entity.BookDetial;
import cn.zzy.library_web.entity.BookInfo;
import cn.zzy.library_web.entity.BookType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    public List<BookType> getBookTypeList();
    public List<BookInfo> getALlBookList();
    public BookDetial getBookDetail(int id);
    public List<BookInfo> getSingleBookList(int typeId);
    public List<BookInfo> getSearchBookList(String info);
    public boolean reduceBook(int bookId);
    public boolean increaseBook(int bookId);
}
