package cn.zzy.library_web.service.impl;

import cn.zzy.library_web.dao.LogDao;
import cn.zzy.library_web.entity.LendLog;
import cn.zzy.library_web.entity.StoreLog;
import cn.zzy.library_web.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LogServiceImplement implements LogService {

    @Autowired
    private LogDao logDao;
    @Override
    public List<StoreLog> getStoreLog(String info) {
        return logDao.getStoreLog(info);
    }

    @Override
    public List<LendLog> getLendLog(String info) {
        List<LendLog> lendLogList = logDao.getLendLog(info);
        for (LendLog lendLog : lendLogList){
            if (lendLog.getBackDate() == null){
                lendLog.setState("未还");
            }else {
                lendLog.setState("已还");
            }
        }
        return lendLogList;
    }
}
