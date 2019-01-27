package service.impl;

import dao.BookCategoryDao;
import dao.BookDao;
import model.Book;
import model.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BookInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookCategoryDao bookCategoryDao;

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    @Transactional
    public void insertBook(Book book) {
        book.setAvailable(true);
        bookDao.insertBook(book);
    }

    @Override
    @Transactional
    public void updateBookInfo(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public List<BookCategory> getAllBookCategories() {
        return bookCategoryDao.getAllBookCategories();
    }

    @Override
    @Transactional
    public List<Book> getKeyBooks(String key) {
        if (key.length() == 0)
            return getAllBooks();
        List<Book> list = new ArrayList<>();
        for (Book book: getAllBooks()) {
            if (book.match(key))
                list.add(book);
        }
        return list;
    }
}
