package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    private int id;
    private String name;
    private BookCategory category;
    private boolean available;
    private BorrowInfo lastBorrow;

    public Book() {
    }

    public Book(String name, BookCategory category) {
        this.name = name;
        this.category = category;
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

    @ManyToOne
    @Cascade(value = {CascadeType.SAVE_UPDATE})
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
