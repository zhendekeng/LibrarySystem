package cn.zzy.library_web.entity;


import java.sql.Timestamp;

public class LendLog {
    private int id;
    private String bookName;
    private String bookAuthor;
    private String bookPublish;
    private String bookISBN;
    private String userFullName;
    private Timestamp lendDate;
    private Timestamp backDate;
    private String state;

    public LendLog() {
    }

    @Override
    public String toString() {
        return "LendLog{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublish='" + bookPublish + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", lendDate=" + lendDate +
                ", backDate=" + backDate +
                ", state='" + state + '\'' +
                '}';
    }

    public LendLog(int id, String bookName, String bookAuthor, String bookPublish,
                   String bookISBN, String userFullName, Timestamp lendDate, Timestamp backDate,
                   String state) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublish = bookPublish;
        this.bookISBN = bookISBN;
        this.userFullName = userFullName;
        this.lendDate = lendDate;
        this.backDate = backDate;
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setLendDate(Timestamp lendDate) {
        this.lendDate = lendDate;
    }

    public void setBackDate(Timestamp backDate) {
        this.backDate = backDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public String getUserFullName() {
        return userFullName;
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
}
