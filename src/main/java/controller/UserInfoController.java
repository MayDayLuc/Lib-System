package controller;

import controller.utils.AlertBox;
import controller.utils.ChildController;
import factory.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.User;
import model.enums.UserType;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController extends ChildController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField IDField;
    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<String> typeMenu;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField mailField;

    private boolean checkField() {
        if (IDField.getText().length() == 0) {
            new AlertBox().display("错误信息", "用户编号不能为空！");
            return false;
        }
        if (nameField.getText().length() == 0) {
            new AlertBox().display("错误信息", "姓名不能为空！");
            return false;
        }
        return true;
    }

    private class EditHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (!checkField())
                return;
            User user1 = new User(IDField.getText(),
                    nameField.getText(),
                    UserType.chineseToUserType(typeMenu.getValue()),
                    phoneField.getText(),
                    mailField.getText());
            ServiceFactory.getUserInfoService().updateUserInfo(user1);
            new AlertBox().display("提示", "编辑成功!");
            stage.close();
            parentController.refresh();
        }
    }

    private class AddHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (!checkField())
                return;
            User user1 = new User(IDField.getText(),
                    nameField.getText(),
                    UserType.chineseToUserType(typeMenu.getValue()),
                    phoneField.getText(),
                    mailField.getText());
            if (ServiceFactory.getUserInfoService().insertUser(user1)) {
                new AlertBox().display("提示", "添加成功!");
                stage.close();
                parentController.refresh();
            }
            else {
                new AlertBox().display("错误信息", "已有相同编号的用户!");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        typeMenu.getItems().addAll("教师", "本科生", "研究生");
        typeMenu.getSelectionModel().select(0);

        confirmButton.setOnAction(new AddHandler());
    }

    private User edit;

    @Override
    public void setEdit(Serializable bean) {
        edit = (User) bean;
        IDField.setText(edit.getId());
        IDField.cancelEdit();
        nameField.setText(edit.getName());
        typeMenu.getSelectionModel().select(edit.getType().ordinal());
        phoneField.setText(edit.getPhone());
        mailField.setText(edit.getEmail());
        confirmButton.setOnAction(new EditHandler());
    }
}
