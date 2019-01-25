package controller.table;

import javafx.beans.property.*;
import model.Book;
import model.BorrowInfo;

public class UserBookTable {
    private final IntegerProperty bookId;
    private final StringProperty bookName;
    private final StringProperty bookCategory;
    private final StringProperty borrowDate;
    private final StringProperty dueDate;

    public UserBookTable(Book book){
        bookId = new SimpleIntegerProperty(book.getId());
        bookName = new SimpleStringProperty(book.getName());
        bookCategory = new SimpleStringProperty(book.getCategory().getName());
        borrowDate = new SimpleStringProperty();
        dueDate = new SimpleStringProperty();
        BorrowInfo bi = book.getLastBorrow();
        if (bi != null) {
            borrowDate.setValue(bi.getBorrowDate().toString());
            dueDate.setValue(bi.getDueDate().toString());
        }
    }

    public IntegerProperty bookIdProperty() {
        return bookId;
    }
    public StringProperty bookNameProperty() {
        return bookName;
    }
    public StringProperty bookCategoryProperty() {
        return bookCategory;
    }
    public StringProperty borrowDateProperty(){
        return borrowDate;
    }
    public StringProperty dueDateProperty(){
        return dueDate;
    }
}
