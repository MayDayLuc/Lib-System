package controller.table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Book;
import model.BorrowInfo;

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
            dueDate.setValue(bi.getDueDate().toString());
            borrower.setValue(bi.getBorrower().getId());
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
