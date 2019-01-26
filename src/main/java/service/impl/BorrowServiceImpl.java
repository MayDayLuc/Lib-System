package service.impl;

import model.Book;
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

import java.util.HashSet;
import java.util.Set;

@Scope("prototype")
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowInfoService borrowInfoService;
    @Autowired
    private OverdueService overdueService;

    private User user;
    private Set<Book> books = new HashSet<>();
    private BorrowMethod method;

    @Override
    @Transactional
    public void setUser(User user) {
        boolean permissionChanged = true;
        if (this.user != null && this.user.getType() == user.getType())
            permissionChanged = false;
        this.user = user;
        if (permissionChanged) {
            method = BorrowMethod.getBorrowMethod(user.getType(), books.size(), borrowInfoService.getBorrowedCnt(user.getId()));
            check();
        }
    }

    private boolean overdue;
    private boolean overSingleLimit;
    private boolean overLimit;

    private boolean hasPermission(Book book) {
        if (user == null)
            return true;
        return (book.getPermission() & (1 << user.getType().ordinal())) > 0;
    }

    private Set<Book> noPermission = new HashSet<Book>();

    private void check() {
        if (overdue = overdueService.isOverDue(user.getId()))
            return;
        overSingleLimit = method.isOverSingleLimit();
        overLimit = method.isOverLimit();
        noPermission.clear();
        for (Book book: books) {
            if (! hasPermission(book)) {
                noPermission.add(book);
            }
        }
    }

    @Override
    public AddBookResult addBook(Book book) {
        if (books.contains(book))
            return AddBookResult.REPEAT;
        books.add(book);
        if (! hasPermission(book)) {
            noPermission.add(book);
            return AddBookResult.NO_PERMISSION;
        }
        return AddBookResult.SUCCESS;
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
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
}
