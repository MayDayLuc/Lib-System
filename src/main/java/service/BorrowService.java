package service;

import model.Book;
import model.User;

public interface BorrowService {
    public void setUser(User user);

    public boolean addBook(Book book);

    public void remove(Book book);

    public User getUser();
}
