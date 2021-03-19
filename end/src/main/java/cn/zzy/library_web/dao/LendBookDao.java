package cn.zzy.library_web.dao;

import cn.zzy.library_web.entity.LendInfo;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LendBookDao {
    public boolean lendBook(int bookId, int userId, Timestamp date);
    public boolean returnBook(int bookId, int userId, Timestamp date);
    public List<Integer> getLendIdList(int userId);
    public List<Integer> getLendIdListByInfo(String info);
    public boolean lendBookExist(int userId,int bookId);
    public boolean returnBookExist(int userId,int bookId);
    public List<LendInfo> getSingleLendList(int userId);
    public List<LendInfo> getSearchLendLogList(String info,int userId);
}
