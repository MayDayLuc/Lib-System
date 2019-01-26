package service;

import model.Book;
import model.User;
import service.enums.AddBookResult;

import java.util.Set;

public interface BorrowService {
    public void setUser(User user);

    public AddBookResult addBook(Book book);

    public void remove(Book book);

    public User getUser();

    public boolean isOverdue();

    public boolean isOverSingleLimit();

    public boolean isOverLimit();

    public Set<Book> getNoPermission();
}
