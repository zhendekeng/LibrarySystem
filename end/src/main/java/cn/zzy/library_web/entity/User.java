package cn.zzy.library_web.entity;

import java.sql.Timestamp;

public class User extends Account{
    private int userId;
    private String userNickname;
    private String role;
    private String email;
    private Timestamp registerTime;
    public User() {
        super();
    }

    public User(int userId, String userName, String userNickname, String userPass,
                String role, String email, Timestamp registerTime) {
        super(userId,userName,userPass);
        this.userId = userId;
        this.userNickname = userNickname;
        this.role = role;
        this.email = email;
        this.registerTime = registerTime;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserId(int userId) {
        userId = userId;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }


    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString() + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
