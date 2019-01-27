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
    private int permission;

    public Book() {
    }

    public Book(String name, BookCategory category, int permission) {
        this.name = name;
        this.category = category;
        this.permission = permission;
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
    @Cascade(value = {CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "bid")
    public BorrowInfo getLastBorrow() {
        return lastBorrow;
    }

    public void setLastBorrow(BorrowInfo lastBorrow) {
        this.lastBorrow = lastBorrow;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public boolean match(String key) {
        return ("" + id).contains(key)
                || name.contains(key)
                || category.getName().contains(key);
    }
}
