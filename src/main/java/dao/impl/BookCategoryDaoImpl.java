package dao.impl;

import dao.BookCategoryDao;
import model.BookCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookCategoryDaoImpl extends BaseDaoImpl implements BookCategoryDao {
    @Override
    public List<BookCategory> getAllBookCategories() {
        return getAllList(BookCategory.class);
    }
}
