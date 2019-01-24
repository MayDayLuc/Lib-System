package dao;

import model.Book;

import java.util.List;

public interface BookDao {
    public void updateBook(Book book);

    public List<Book> getAllBooks();
}
