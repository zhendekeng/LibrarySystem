package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.User;

import java.util.List;

public interface UserService {
    //通过主键获取
    public User getUserById(int userId);

    //用户名（唯一）获取
    public User getUserByName(String userName);

    public void addUser(User user);

    public boolean deleteUser(int userId);

    public boolean updateUser(User user);

}
