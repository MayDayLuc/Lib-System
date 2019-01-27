package controller.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Notification;

public class NotifyTable {
    private final StringProperty user;
    private final StringProperty operation;
    private final StringProperty time;

    public NotifyTable(Notification notification) {
        user = new SimpleStringProperty(notification.getUser().getName());
        operation = new SimpleStringProperty(notification.getOperation());
        time = new SimpleStringProperty(notification.getTime().toString());
    }
    public StringProperty userProperty() {
        return user;
    }

    public StringProperty operationProperty() {
        return operation;
    }

    public StringProperty timeProperty() {
        return time;
    }
}
