package sample;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController {
    @FXML
    private TextField idField;
    @FXML
    private TextField pswField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    private void login() throws IOException {
        FXRobotHelper.getStages().get(0).close();
        Parent root=null;
        FXMLLoader loader=null;
        Stage stage=new Stage();
        if(idField.getText().equals("161250106") && pswField.getText().equals("123")){
            loader=new FXMLLoader(getClass().getResource("user.fxml"));
            root = loader.load();
            stage.setTitle("Library System");
            stage.setScene(new Scene(root, 900, 556));
            stage.show();
        }
        else {
            new alertBox().display("错误信息", "用户名或密码错误");
        }
    }

    private void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        loginButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        idField.setOnKeyPressed(new LoginHandler());
        pswField.setOnKeyPressed(new LoginHandler());
        loginButton.setOnKeyPressed(new LoginHandler());
    }

    public class LoginHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent arg0) {
            if(arg0.getCode()==KeyCode.ENTER) {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
