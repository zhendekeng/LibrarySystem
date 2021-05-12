package cn.zzy.library_web.dao;




import cn.zzy.library_web.entity.BookDetail;
import cn.zzy.library_web.entity.BookType;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BookDao {
    public List<BookType> getAllBookType();
    public BookDetail getOneBookDetail(int id);

    public List<BookDetail> getOneTypeBook(int typeId);
    public List<BookDetail> getSearchAllBook(String info);

    public boolean reduceBookNumber(int bookId);
    public boolean increaseBookNumber(int bookId);

    public boolean checkBookExist(String bookISBN);
    public boolean addBook(BookDetail bookDetail);

    public boolean modifyBook(BookDetail bookDetail);
    public boolean addStore(int adminId,int bookId,Timestamp date);
}
