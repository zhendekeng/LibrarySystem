package cn.zzy.library_web.entity;


public class BookInfo {
    private int id;
    private String name;
    private String author;
    private String publish;
    private String ISBN;
    private double price;
    private int number;
    private String state;

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", state='" + state + '\'' +
                '}';
    }

    public void setState(String state) {
        this.state = state;
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

    public BookInfo() {
    }

    public BookInfo(int id, String name, String author, String publish, String ISBN, double price, int number) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.ISBN = ISBN;
        this.price = price;
        this.number = number;
    }
}
