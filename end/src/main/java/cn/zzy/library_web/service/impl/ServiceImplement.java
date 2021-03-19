package cn.zzy.library_web.service.impl;


import cn.zzy.library_web.dao.UserDao;
import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//mybatis事务管理
@Transactional
@Service
public class ServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
}