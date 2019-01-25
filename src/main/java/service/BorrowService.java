package service;

import model.Book;
import model.User;

public interface BorrowService {
    public void setUser(User user);

    public void addBook(Book book);

    public void remove(Book book);
}
