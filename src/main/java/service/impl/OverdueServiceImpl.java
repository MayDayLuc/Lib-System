package service.impl;

import dao.BorrowInfoDao;
import model.Book;
import model.BorrowInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BorrowInfoService;
import service.OverdueService;

import java.time.LocalDate;

@Service
public class OverdueServiceImpl implements OverdueService {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @Autowired
    private BorrowInfoDao borrowInfoDao;
    @Override
    public boolean isOverDue(String uid) {
        for (BorrowInfo borrowInfo: borrowInfoService.getAllBorrowedBooks()){
            Book book = borrowInfo.getBook();
            if (borrowInfo.getBorrower().getId().equals(uid))
                if (book.getLastBorrow().getId() == borrowInfo.getId())
                    if (borrowInfo.getDueDate().isBefore(LocalDate.now()))
                        return true;
        }
        return false;
    }
}
