package cn.zzy.library_web.dao;

import cn.zzy.library_web.entity.Account;
import cn.zzy.library_web.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //通过主键获取
    public User getUserById(int userId);

    //用户名（唯一）获取
    public User getUserByName(String userName);

    //用户名（唯一）获取 账号密码
    public Account getAccountByName(String userName);

    public void addUser(User user);

    public boolean deleteUser(int userId);

    public boolean modifyPass(int userId,String newPass);

    public boolean modifyUserInfo(int userId,String email,String nickName);
}
