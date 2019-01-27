import controller.utils.BaseController;
import factory.ServiceFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.net.URL;

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
        File file = new File("target/classes/controller");
        URL url = Main.class.getResource("");
        System.out.println(file.getAbsolutePath());
        System.out.println(url.getPath());
        ServiceFactory.setApplicationContext(new ClassPathXmlApplicationContext("applicationContext.xml"));
        launch(args);
    }
}
