package dao.impl;

import dao.BookDao;
import model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoIml extends BaseDaoImpl implements BookDao {
    @Override
    public void updateBook(Book book) {
        update(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return getAllList(Book.class);
    }

    @Override
    public void insertBook(Book book) {
        save(book);
    }
}
