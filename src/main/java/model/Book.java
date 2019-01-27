package model;

import model.enums.EBookType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    private int id;
    private String name;
    private BookCategory category;
    private boolean available;
    private BorrowInfo lastBorrow;
    private int permission;
    private Set<EBook> electronicBooks;

    public Book() {
    }

    public Book(String name, BookCategory category, int permission, List<String> eBooks) {
        this.name = name;
        this.category = category;
        this.permission = permission;
        setElectronicBooks(eBooks);
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

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    public Set<EBook> getElectronicBooks() {
        return electronicBooks;
    }

    public void setElectronicBooks(Set<EBook> electronicBooks) {
        this.electronicBooks = electronicBooks;
    }

    public boolean match(String key) {
        return ("" + id).contains(key)
                || name.contains(key)
                || category.getName().contains(key);
    }

    public void setElectronicBooks(List<String> eBooks) {
        for (int i = 0; i < eBooks.size(); i ++) {
            EBookType t = EBookType.values()[i];
            String eBook = eBooks.get(i);
            if (eBook == null)
                continue;
            EBook e;
            if ((e = contains(t)) != null) {
                e.setPath(eBook);
                continue;
            }
            e = new EBook();
            e.setPath(eBook);
            e.setBook(this);
            e.setType(t);
            electronicBooks.add(e);
        }
    }

    private EBook contains(EBookType t) {
        for (EBook eBook: electronicBooks) {
            if (eBook.getType() == t)
                return eBook;
        }
        return null;
    }
}
