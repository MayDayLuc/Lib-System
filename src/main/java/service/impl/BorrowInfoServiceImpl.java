package service.impl;

import dao.BorrowInfoDao;
import model.Book;
import model.BorrowInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BorrowInfoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Autowired
    private BorrowInfoDao borrowInfoDao;

    @Override
    public List<Book> getMyBorrowedBooks(String uid) {
        ArrayList<Book> books = new ArrayList<>();
        for (BorrowInfo borrowInfo: borrowInfoDao.getAllBorrowInfos()){
            if (borrowInfo.getBorrower().getId().equals(uid))
                if (! borrowInfo.getDueDate().isBefore(LocalDate.now()))
                    books.add(borrowInfo.getBook());
        }
        return books;
    }
}
