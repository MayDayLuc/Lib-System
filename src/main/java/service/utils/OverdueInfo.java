package service.utils;

import model.Book;
import model.User;

public class OverdueInfo {
    private Book book;
    private User borrower;
    private int overdueDays;
    private double penalty;

    public OverdueInfo(Book book, User borrower, int overdueDays) {
        this.book = book;
        this.borrower = borrower;
        this.overdueDays = overdueDays;
        this.penalty = overdueDays * 2;
    }

    public Book getBook() {
        return book;
    }

    public User getBorrower() {
        return borrower;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public double getPenalty() {
        return penalty;
    }
}
