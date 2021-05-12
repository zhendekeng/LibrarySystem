package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.LendLog;
import cn.zzy.library_web.entity.StoreLog;

import java.util.List;


public interface LogService {
    public List<StoreLog> getStoreLog(String info);
    public List<LendLog> getLendLog(String info);
}
