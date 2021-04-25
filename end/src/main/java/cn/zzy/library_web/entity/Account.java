package cn.zzy.library_web.entity;

public class Account {
    private int userId;
    private String userName;
    private String userPass;

    public String getUserName() {
        return userName;
    }

    public Account() {
    }

    public Account(int userId,String userName, String userPass) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
