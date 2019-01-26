package controller.mocks;

import model.Book;
import model.BookCategory;
import model.BorrowInfo;
import model.User;
import service.BorrowInfoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BorrowInfoServiceMock implements BorrowInfoService {
    @Override
    public List<Book> getMyBorrowedBooks(String userId){
        User user1 = new User();
        user1.setId("161250105");
        BookCategory category1 = new BookCategory();
        category1.setName("人文社会");
        Book book1 = new Book();
        book1.setId(111111115);
        book1.setName("《渣渣辉》");
        book1.setCategory(category1);
        BorrowInfo borrowInfo1 = new BorrowInfo();
        borrowInfo1.setBorrower(user1);
        borrowInfo1.setBorrowDate(LocalDate.now());
        borrowInfo1.setDueDate(LocalDate.of(2019,2,22));
        book1.setLastBorrow(borrowInfo1);

        List<Book> BookList = new ArrayList<>();
        BookList.add(book1);
        return  BookList;
    }

    @Override
    public int getBorrowedCnt(String uid) {
        return 0;
    }

    @Override
    public List<BorrowInfo> getAllBorrowedBooks() {
        return null;
    }
}
