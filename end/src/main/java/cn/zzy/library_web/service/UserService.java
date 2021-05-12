package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.Account;
import cn.zzy.library_web.entity.User;

import java.util.List;

public interface UserService {
    //通过主键获取
    public User getUserById(int accountId);


    //用户名（唯一）获取 账号密码
    public Account getAccountByName(String accountName);

    public Account getAccountById(int accountId);

    public boolean addUser(User user);
    public Integer getUserOrAdminId(int accountId);


    public boolean checkPass(int accountId,String oldPass);
    public boolean modifyPass(int accountId,String newPass);
    public boolean modifyUserInfo(int accountId,String userEmail,String userFullName);
}
