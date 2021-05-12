package cn.zzy.library_web.dao;

import cn.zzy.library_web.entity.Account;
import cn.zzy.library_web.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //通过主键获取
    public User getUserById(int accountId);

    //用户名（唯一）获取 账号密码
    public Account getAccountByName(String accountName);

    //主键（唯一）获取 账号密码
    public Account getAccountById(int accountId);

    public void addUser(User user);

    public void addAccount(Account account);

    public Integer getUserId(int accountId);
    public Integer getAdminId(int accountId);

    public boolean modifyPass(int accountId,String newPass);

    public boolean modifyUserInfo(int accountId,String userEmail,String userFullName);
}
