package controller.utils;

import controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Transformer {
    public static void jump(Stage lastStage, Object o, String fxml) {
        lastStage.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(o.getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((BaseController) loader.getController()).setStage(stage);
        stage.setTitle("Library System");
        stage.setScene(new Scene(root, 900, 556));
        stage.show();
    }
}
