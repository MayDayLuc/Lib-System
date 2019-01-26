package service;

import model.Book;
import model.BorrowInfo;

import java.util.List;

public interface BorrowInfoService {
    public List<Book> getMyBorrowedBooks(String uid);
    public int getBorrowedCnt(String uid);
    public List<BorrowInfo> getAllBorrowedBooks();
    public List<BorrowInfo> getKeyBorrow(String key);
}
