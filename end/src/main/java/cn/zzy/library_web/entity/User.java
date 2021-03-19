package cn.zzy.library_web.entity;

import java.sql.Timestamp;

public class User {
    private int Id;
    private String userName;
    private String userNickname;
    private String password;
    private String role;
    private String email;
    private Timestamp registerTime;
    public User() {
    }

    public User(int Id, String userName, String userNickname, String password,
                String role, String email, Timestamp registerTime) {
        this.Id = Id;
        this.userName = userName;
        this.userNickname = userNickname;
        this.password = password;
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

    public void setId(int id) {
        Id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
