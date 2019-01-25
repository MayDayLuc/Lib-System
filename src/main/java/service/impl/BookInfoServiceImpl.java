package service.impl;

import dao.BookCategoryDao;
import dao.BookDao;
import model.Book;
import model.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BookInfoService;

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
}
