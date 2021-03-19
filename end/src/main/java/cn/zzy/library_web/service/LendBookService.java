package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.LendInfo;

import java.sql.Timestamp;
import java.util.List;

public interface LendBookService {
    public boolean lendBook(int bookId, int userId, Timestamp date);
    public boolean returnBook(int bookId, int userId, Timestamp date);
    public List<LendInfo> getSingleLendList(int userId);
    public List<LendInfo> getSearchLendLogList(String info,int userId);
}
