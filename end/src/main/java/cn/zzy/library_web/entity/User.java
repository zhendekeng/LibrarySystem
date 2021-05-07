package cn.zzy.library_web.entity;

import java.sql.Timestamp;

public class User extends Account{
    private int userId;
    private String userFullName;
    private String userEmail;
    private Timestamp userRegistertime;
    public User() {
        super();
    }

    public User(int accountId, String accountName, String accountPass, int accountRole,
                int userId, String userFullName, String userEmail, Timestamp userRegistertime) {
        super(accountId, accountName, accountPass, accountRole);
        this.userId = userId;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userRegistertime = userRegistertime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegistertime=" + userRegistertime +
                '}';
    }

    public User(int userId, String userFullName, String userEmail, Timestamp userRegistertime) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userRegistertime = userRegistertime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserRegistertime(Timestamp userRegistertime) {
        this.userRegistertime = userRegistertime;
    }



    public int getUserId() {
        return userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Timestamp getUserRegistertime() {
        return userRegistertime;
    }

}
