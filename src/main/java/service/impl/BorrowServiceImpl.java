package service.impl;

import model.Book;
import model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import service.BorrowService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Scope("prototype")
@Service
public class BorrowServiceImpl implements BorrowService {

    private User user;
    private Set<Book> books = new HashSet<>();

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
    }
}
