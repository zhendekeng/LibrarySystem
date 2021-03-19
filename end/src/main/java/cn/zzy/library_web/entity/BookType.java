package cn.zzy.library_web.entity;

public class BookType {
    private int id;
    private String name;

    public BookType() {
    }

    public BookType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
