package cn.zzy.library_web.entity;

import java.sql.Timestamp;

public class StoreLog {
    private int putId;
    private int putAdminId;
    private String bookName;
    private String bookAuthor;
    private String bookPublish;
    private String bookISBN;
    private Timestamp putDate;

    @Override
    public String toString() {
        return "StoreLog{" +
                "putId=" + putId +
                ", putAdminId=" + putAdminId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublish='" + bookPublish + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", putDate=" + putDate +
                '}';
    }

    public StoreLog() {
    }

    public StoreLog(int putId, int putAdminId, String bookName, String bookAuthor,
                    String bookPublish, String bookISBN, Timestamp putDate) {
        this.putId = putId;
        this.putAdminId = putAdminId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublish = bookPublish;
        this.bookISBN = bookISBN;
        this.putDate = putDate;
    }

    public void setPutId(int putId) {
        this.putId = putId;
    }

    public void setPutAdminId(int putAdminId) {
        this.putAdminId = putAdminId;
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

    public void setPutDate(Timestamp putDate) {
        this.putDate = putDate;
    }

    public int getPutId() {
        return putId;
    }

    public int getPutAdminId() {
        return putAdminId;
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

    public Timestamp getPutDate() {
        return putDate;
    }
}
