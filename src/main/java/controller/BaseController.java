package controller;

import javafx.stage.Stage;
import model.User;

public abstract class BaseController {
    protected Stage stage;
    protected User user;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
