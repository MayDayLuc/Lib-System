package service;

import model.Book;

import java.util.List;

public interface BookInfoService {
    public List<Book> getAllBooks();
    public boolean insertBook(Book book);
    public void updateBokkInfo(Book book);
}
