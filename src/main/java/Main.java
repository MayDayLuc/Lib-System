import controller.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("controller/login.fxml"));
        Parent root = loader.load();
        ((BaseController) loader.getController()).setStage(primaryStage);
        primaryStage.setTitle("Library System");
        primaryStage.setScene(new Scene(root, 900, 556));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
