package service.impl;

import dao.BorrowInfoDao;
import model.Book;
import model.BorrowInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BorrowInfoService;
import service.OverdueService;
import service.utils.OverdueInfo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class OverdueServiceImpl implements OverdueService {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @Override
    public boolean isOverDue(String uid) {
        for (BorrowInfo borrowInfo: borrowInfoService.getAllBorrowedBooks()){
            Book book = borrowInfo.getBook();
            if (borrowInfo.getBorrower().getId().equals(uid))
                if (!book.isAvailable() && book.getLastBorrow().getId() == borrowInfo.getId())
                    if (borrowInfo.getDueDate().isBefore(LocalDate.now()))
                        return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<OverdueInfo> getPenaltyList() {
        List<OverdueInfo> list = new ArrayList<>();
        for (BorrowInfo info: borrowInfoService.getAllBorrowedBooks()) {
            Book book = info.getBook();
            LocalDate due = info.getDueDate();
            LocalDate now = LocalDate.now();
            if (! book.isAvailable() && due.isBefore(now))
                list.add(new OverdueInfo(book, info.getBorrower(), (int) due.until(now, ChronoUnit.DAYS)));
        }
        return list;
    }

    @Override
    @Transactional
    public boolean checkOverDue(Book book) {
        for (BorrowInfo info: borrowInfoService.getAllBorrowedBooks()) {
            Book b = info.getBook();
            if (book.getId() != b.getId())
                continue;
            LocalDate due = info.getDueDate();
            LocalDate now = LocalDate.now();
            if (! b.isAvailable() && due.isBefore(now))
                return false;
        }
        return true;
    }
}
