package controller.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.User;
import model.enums.UserType;

public class UserTable {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty type;
    private final StringProperty phone;
    private final StringProperty email;

    public UserTable(User user) {
        id = new SimpleStringProperty(user.getId());
        name = new SimpleStringProperty(user.getName());
        type = new SimpleStringProperty(UserType.transform(user.getType()));
        phone = new SimpleStringProperty(user.getPhone());
        email = new SimpleStringProperty(user.getEmail());
    }


    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }
}
