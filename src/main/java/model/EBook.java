package model;

import model.enums.EBookType;

import javax.persistence.*;

@Entity
@Table(name = "ebook")
public class EBook {
    private int id;
    private EBookType type;
    private String path;
    private Book book;

    public EBook() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(value = EnumType.STRING)
    public EBookType getType() {
        return type;
    }

    public void setType(EBookType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToOne
    @JoinColumn(name = "bid")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
