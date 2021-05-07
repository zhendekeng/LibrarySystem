package cn.zzy.library_web.service.impl;


import cn.zzy.library_web.dao.UserDao;
import cn.zzy.library_web.entity.Account;
import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.Date;

//mybatis事务管理
@Transactional
@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }


    @Override
    public Account getAccountByName(String accountName) {
        return userDao.getAccountByName(accountName);
    }

    @Override
    public Account getAccountById(int accountId) {
        return userDao.getAccountById(accountId);
    }

    @Override
    public boolean addUser(User user) {
        if (userDao.getAccountByName(user.getAccountName()) != null) return false;
        Date date = new Date();
        Timestamp timestamp =  new Timestamp(date.getTime());
        user.setUserRegistertime(timestamp);
        userDao.addAccount(user);
        userDao.addUser(user);
        return true;
    }


    @Override
    public boolean checkPass(int accountId, String oldPass) {
        Account account = userDao.getAccountById(accountId);
        if (account.getAccountPass().equals(oldPass)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean modifyPass(int accountId,String newPass) {
        if (userDao.modifyPass(accountId,newPass)){
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public boolean modifyUserInfo(int accountId, String userEmail, String userFullName) {
        return userDao.modifyUserInfo(accountId,userEmail,userFullName);
    }
}
