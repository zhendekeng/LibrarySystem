package cn.zzy.library_web.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class BookDetial extends BookInfo{
    private String introduction;
    private String language;
    private Date pubDate;
    private String type;

    public BookDetial() {
    }

    public BookDetial(int id, String name, String author, String publish, String ISBN,
                      double price, int number, String introduction, String language, Date pubDate, String type) {
        super(id,name,author,publish,ISBN,price,number);
        this.introduction = introduction;
        this.language = language;
        this.pubDate = pubDate;
        this.type = type;
    }


    public String getIntroduction() {
        return introduction;
    }

    public String getLanguage() {
        return language;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getType() {
        return type;
    }



    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "BookDetial{" +
                super.toString() +
                ", introduction='" + introduction + '\'' +
                ", language='" + language + '\'' +
                ", pubDate=" + pubDate +
                ", type='" + type + '\'' +
                '}';
    }
}
