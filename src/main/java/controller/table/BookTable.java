package controller.table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Book;
import model.BorrowInfo;

import java.time.LocalDate;

public class BookTable {
    private final IntegerProperty bookId;
    private final StringProperty bookName;
    private final StringProperty bookCategory;
    private final StringProperty borrowDate;
    private final StringProperty dueDate;
    private final StringProperty borrower;

    public BookTable(Book book){
        bookId = new SimpleIntegerProperty(book.getId());
        bookName = new SimpleStringProperty(book.getName());
        bookCategory = new SimpleStringProperty(book.getCategory().getName());
        borrowDate = new SimpleStringProperty();
        dueDate = new SimpleStringProperty();
        borrower = new SimpleStringProperty();
        BorrowInfo bi = book.getLastBorrow();
        if (bi != null) {
            borrowDate.setValue(bi.getBorrowDate().toString());

            LocalDate due = bi.getDueDate();
            dueDate.setValue(due.toString());
            String bid = bi.getBorrower().getId();
            if (due.isBefore(LocalDate.now()))
                bid += "（以超期）";
            borrower.setValue(bid);
        }

    }

    public IntegerProperty bookIdProperty(){
        return bookId;
    }
    public StringProperty bookNameProperty(){
        return bookName;
    }
    public StringProperty bookCategoryProperty(){
        return bookCategory;
    }
    public StringProperty borrowDateProperty(){
        return borrowDate;
    }
    public StringProperty dueDateProperty(){
        return dueDate;
    }
    public StringProperty borrowerProperty(){
        return borrower;
    }
}
