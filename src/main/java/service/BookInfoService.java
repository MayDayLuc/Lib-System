package service;

import model.Book;
import model.BookCategory;

import java.util.List;

public interface BookInfoService {
    public List<Book> getAllBooks();

    public void insertBook(Book book);

    public void updateBookInfo(Book book);

    public List<BookCategory> getAllBookCategories();

    public List<Book> getKeyBooks(String key);
}
