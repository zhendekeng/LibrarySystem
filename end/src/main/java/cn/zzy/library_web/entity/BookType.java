package cn.zzy.library_web.entity;

public class BookType {
    private int typeId;
    private String typeName;

    public BookType() {
    }

    public BookType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
