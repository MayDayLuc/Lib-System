package controller.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class Transformer {
    public static void jump(Stage lastStage, Object o, String fxml, User user) {
        lastStage.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(o.getClass().getResource(fxml));
        Parent root = getRoot(loader);
        BaseController baseController = (BaseController) loader.getController();
        baseController.setStage(stage);
        baseController.setUser(user);
        stage.setTitle("Library System");
        stage.setScene(new Scene(root, 900, 556));
        stage.show();
    }

    private static Parent getRoot(FXMLLoader loader) {
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static ChildController generateChild(Parental parentController, String fxml, User user) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(parentController.getClass().getResource(fxml));
        Parent root = getRoot(loader);
        ChildController childController = (ChildController) loader.getController();
        childController.setStage(stage);
        childController.setUser(user);
        childController.setParentController(parentController);

        stage.setTitle("Library System");
        stage.setScene(new Scene(root));
        stage.show();
        return childController;
    }
}
