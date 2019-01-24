package service;

import model.Book;

import java.util.List;

public interface BorrowInfoService {
    public List<Book> getMyBorrowedBooks(String uid);
}
