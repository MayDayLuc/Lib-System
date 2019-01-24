package controller.table;
import javafx.beans.property.*;
import model.Book;

public class UserBookTable {
    public final IntegerProperty bookId;
    public final StringProperty bookName;
    public final StringProperty bookCategory;
    public final StringProperty borrowDate;
    public final StringProperty dueDate;

    public UserBookTable(Book book){
        bookId = new SimpleIntegerProperty(book.getId());
        bookName = new SimpleStringProperty(book.getName());
        bookCategory = new SimpleStringProperty(book.getCategory().getName());
        borrowDate = new SimpleStringProperty(book.getLastBorrow().getBorrowDate().toString());
        dueDate = new SimpleStringProperty(book.getLastBorrow().getDueDate().toString());
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
