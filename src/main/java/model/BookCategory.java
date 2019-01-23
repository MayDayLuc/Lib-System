package model;

import javax.persistence.*;

@Entity
@Table(name = "book_category")
public class BookCategory {
    private int id;
    private String name;

    public BookCategory() {
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
}
