package controller.table;

import javafx.beans.property.*;
import model.Book;
import service.utils.OverdueInfo;

public class OverdueTable {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty borrower;
    private final IntegerProperty days;
    private final DoubleProperty penalty;

    public OverdueTable(OverdueInfo info) {
        Book book = info.getBook();
        id = new SimpleIntegerProperty(book.getId());
        name = new SimpleStringProperty(book.getName());
        borrower = new SimpleStringProperty(info.getBorrower().getId());
        days = new SimpleIntegerProperty(info.getOverdueDays());
        penalty = new SimpleDoubleProperty(info.getPenalty());
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty borrowerProperty() {
        return borrower;
    }

    public IntegerProperty daysProperty() {
        return days;
    }

    public DoubleProperty penaltyProperty() {
        return penalty;
    }
}
