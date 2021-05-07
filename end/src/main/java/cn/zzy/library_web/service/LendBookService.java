package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.LendInfo;

import java.sql.Timestamp;
import java.util.List;

public interface LendBookService {
    public boolean lendBook(int bookId, int userId);
    public boolean returnBook(int bookId, int userId);
    public List<LendInfo> getSearchOnePeopleLendLog(String info, int userId);
}
