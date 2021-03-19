package cn.zzy.library_web.entity;

import java.sql.Timestamp;

public class LendInfo {
    private int id;
    private String bookName;
    private String userName;
    private Timestamp lendDate;
    private Timestamp backDate;
    private String state;

    @Override
    public String toString() {
        return "LendInfo{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", userName='" + userName + '\'' +
                ", lendDate=" + lendDate +
                ", backDate=" + backDate +
                ", state='" + state + '\'' +
                '}';
    }

    public LendInfo() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLendDate(Timestamp lendDate) {
        this.lendDate = lendDate;
    }

    public void setBackDate(Timestamp backDate) {
        this.backDate = backDate;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getUserName() {
        return userName;
    }

    public Timestamp getLendDate() {
        return lendDate;
    }

    public Timestamp getBackDate() {
        return backDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LendInfo(int id, String bookName, String userName, Timestamp lendDate, Timestamp backDate) {
        this.id = id;
        this.bookName = bookName;
        this.userName = userName;
        this.lendDate = lendDate;
        this.backDate = backDate;
    }
}
