package controller;

import controller.utils.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class LoginController extends BaseController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField pswField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    private void login() {

        if(idField.getText().equals("161250106") && pswField.getText().equals("123")){
            stage.close();
            Stage stage=new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
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
        else {
            new AlertBox().display("错误信息", "用户名或密码错误");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loginButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                login();
            }
        });
        idField.setOnKeyPressed(new LoginHandler());
        pswField.setOnKeyPressed(new LoginHandler());
        loginButton.setOnKeyPressed(new LoginHandler());
    }

    private class LoginHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent arg0) {
            if(arg0.getCode() == KeyCode.ENTER) {
                login();
            }
        }
    }
}
