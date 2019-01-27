package service.impl;

import dao.BorrowInfoDao;
import model.Book;
import model.BorrowInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BorrowInfoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Autowired
    private BorrowInfoDao borrowInfoDao;

    @Override
    @Transactional
    public List<Book> getMyBorrowedBooks(String uid) {
        ArrayList<Book> books = new ArrayList<>();
        for (BorrowInfo borrowInfo: getAllBorrowedBooks()){
            if (borrowInfo.getBorrower().getId().equals(uid))
                if (! borrowInfo.getDueDate().isBefore(LocalDate.now()))
                    books.add(borrowInfo.getBook());
        }
        return books;
    }

    @Override
    public int getBorrowedCnt(String uid) {
        return getMyBorrowedBooks(uid).size();
    }

    @Override
    @Transactional
    public List<BorrowInfo> getAllBorrowedBooks() {
        return borrowInfoDao.getAllBorrowInfos();
    }

    @Override
    public List<BorrowInfo> getKeyBorrow(String key) {
        if (key.length() == 0)
            return getAllBorrowedBooks();
        List<BorrowInfo> list = new ArrayList<>();
        for (BorrowInfo info: getAllBorrowedBooks()) {
            if (info.match(key))
                list.add(info);
        }
        return list;
    }
}
