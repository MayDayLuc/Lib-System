package model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    private int id;
    private String name;
    private BookCategory category;
    private boolean available;
    private BorrowInfo lastBorrow;

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "cid")
    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @OneToOne
    @JoinColumn(name = "bid")
    public BorrowInfo getLastBorrow() {
        return lastBorrow;
    }

    public void setLastBorrow(BorrowInfo lastBorrow) {
        this.lastBorrow = lastBorrow;
    }
}
