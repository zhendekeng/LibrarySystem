package cn.zzy.library_web.entity;

import java.sql.Date;

public class BookDetail {
    private int id;
    private String name;
    private String author;
    private String publish;
    private String ISBN;
    private double price;
    private int number;
    private String state;
    private String introduction;
    private String language;
    private Date pubDate;
    private String type;

    public BookDetail() {
    }

    public BookDetail(int id, String name, String author, String publish, String ISBN,
                      double price, int number, String state, String introduction,
                      String language, Date pubDate, String type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.ISBN = ISBN;
        this.price = price;
        this.number = number;
        this.state = state;
        this.introduction = introduction;
        this.language = language;
        this.pubDate = pubDate;
        this.type = type;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", introduction='" + introduction + '\'' +
                ", language='" + language + '\'' +
                ", pubDate=" + pubDate +
                ", type='" + type + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish() {
        return publish;
    }

    public String getISBN() {
        return ISBN;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
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
}
