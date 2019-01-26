package service.impl;

import dao.BookDao;
import model.Book;
import model.BorrowInfo;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BorrowInfoService;
import service.BorrowService;
import service.OverdueService;
import service.enums.AddBookResult;
import service.utils.BorrowMethod;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Scope("prototype")
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowInfoService borrowInfoService;
    @Autowired
    private OverdueService overdueService;
    @Autowired
    private BookDao bookDao;

    private User user;
    private Set<Book> books = new HashSet<>();
    private BorrowMethod method;

    @Override
    @Transactional
    public boolean setUser(User user) {
        boolean permissionChanged = true;
        if (this.user != null && this.user.getType() == user.getType())
            permissionChanged = false;
        this.user = user;
        method = BorrowMethod.getBorrowMethod(user.getType(), books.size(), borrowInfoService.getBorrowedCnt(user.getId()));
        if (permissionChanged)
            check();
        for (Book book: books) {
            BorrowInfo info = book.getLastBorrow();
            if (info != null) {
                info.setBorrower(user);
                info.setBook(book);
                info.setBorrowDate(LocalDate.now());
                info.setDueDate(method.getDue());
            }
        }
        return permissionChanged;
    }

    private boolean overdue;
    private boolean overSingleLimit;
    private boolean overLimit;

    private boolean hasPermission(Book book) {
        if (user == null)
            return true;
        return (book.getPermission() & (1 << user.getType().ordinal())) > 0;
    }

    private Set<Book> noPermission = new HashSet<>();

    private void checkNum() {
        overSingleLimit = method.isOverSingleLimit();
        overLimit = method.isOverLimit();
    }

    private void checkOverdue() {
        overdue = overdueService.isOverDue(user.getId());
    }

    private void checkPermissions() {
        noPermission.clear();
        for (Book book: books) {
            if (! hasPermission(book)) {
                noPermission.add(book);
                book.setLastBorrow(null);
            }
            else if (book.getLastBorrow() == null) {
                book.setLastBorrow(new BorrowInfo());
            }
        }
    }

    private void check() {
        checkOverdue();
        checkNum();
        checkPermissions();
    }

    @Override
    public AddBookResult addBook(Book book) {
        if (books.contains(book))
            return AddBookResult.REPEAT;
        if (! book.isAvailable())
            return AddBookResult.NOT_AVAILABLE;
        books.add(book);
        book.setLastBorrow(null);
        if (! hasPermission(book)) {
            noPermission.add(book);
            return AddBookResult.NO_PERMISSION;
        }
        if (user != null) {
            method.add();
            checkNum();
            BorrowInfo info = new BorrowInfo();
            info.setBorrower(user);
            info.setBook(book);
            info.setBorrowDate(LocalDate.now());
            info.setDueDate(method.getDue());
            book.setLastBorrow(info);
        }
        return AddBookResult.SUCCESS;
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
        noPermission.remove(book);
        if (user != null) {
            method.subtract();
            checkNum();
        }
        book.setLastBorrow(null);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean isOverdue() {
        return overdue;
    }

    @Override
    public boolean isOverSingleLimit() {
        return overSingleLimit;
    }

    @Override
    public boolean isOverLimit() {
        return overLimit;
    }

    @Override
    public Set<Book> getNoPermission() {
        return noPermission;
    }

    @Override
    @Transactional
    public void commit() {
        if (isOverdue() || isOverLimit() || isOverSingleLimit() || noPermission.size() > 0)
            return;
        for (Book book: books) {
            book.setAvailable(false);
            bookDao.updateBook(book);
        }
    }
}
