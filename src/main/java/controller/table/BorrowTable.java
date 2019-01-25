package controller.table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.BorrowInfo;

public class BorrowTable {
    private final IntegerProperty borrowBookId;
    private final StringProperty borrowBookName;
    private final StringProperty borrowBorrower;
    private final StringProperty borrowBorrowDate;
    private final StringProperty borrowDueDate;

    public BorrowTable(BorrowInfo borrowInfo){
        borrowBookId = new SimpleIntegerProperty(borrowInfo.getId());
        borrowBookName = new SimpleStringProperty(borrowInfo.getBook().getName());
        borrowBorrower = new SimpleStringProperty(borrowInfo.getBorrower().getName());
        borrowBorrowDate = new SimpleStringProperty(borrowInfo.getBorrowDate().toString());
        borrowDueDate = new SimpleStringProperty(borrowInfo.getDueDate().toString());
    }

    public IntegerProperty borrowBookIdProperty(){return borrowBookId;}
    public StringProperty borrowBookNameProperty(){return borrowBookName;}
    public StringProperty borrowBorrowerProperty(){return borrowBorrower;}
    public StringProperty borrowBorrowDateProperty(){return borrowBorrowDate;}
    public StringProperty borrowDueDateProperty(){return borrowDueDate;}
}
