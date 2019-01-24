package service.impl;

import dao.BookDao;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BookInfoService;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
}
