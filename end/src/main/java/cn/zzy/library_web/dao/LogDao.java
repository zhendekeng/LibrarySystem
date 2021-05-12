package cn.zzy.library_web.dao;

import cn.zzy.library_web.entity.LendLog;
import cn.zzy.library_web.entity.StoreLog;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LogDao {
    public List<StoreLog> getStoreLog(String info);
    public List<LendLog> getLendLog(String info);
}
