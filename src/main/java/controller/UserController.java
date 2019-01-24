package controller;

import controller.utils.AlertBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Book;
import model.BookCategory;
import model.BorrowInfo;
import model.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserController extends BaseController implements Initializable {
    @FXML
    private Label userType;
    @FXML
    private Label idLabel;
    @FXML
    private TabPane userTab;
    @FXML
    private Tab bookTab;
    @FXML
    private AnchorPane bookPane;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> bookIDCol;
    @FXML
    private TableColumn<Book, String> bookNameCol;
    @FXML
    private TableColumn<Book, BookCategory> bookTypeCol;
    @FXML
    private TableColumn<BorrowInfo, LocalDate> startTimeCol;
    @FXML
    private TableColumn<BorrowInfo, LocalDate> endTimeCol;
    @FXML
    private Button readDOCButton;
    @FXML
    private Button readPDFButton;
    @FXML
    private Button readEPUBButton;

    @FXML
    private Tab infoTab;
    @FXML
    private AnchorPane infoPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label collegeLabel;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField mailField;
    @FXML
    private Button saveButton;

    private void modifyInfo(){
        if(phoneField.getText()==null||mailField.getText()==null){
            new AlertBox().display("提示", "信息不能为空");
        }
        else{
            user.setPhone(phoneField.getText());
            user.setEmail(mailField.getText());
            new AlertBox().display("提示", "保存成功");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
               modifyInfo();
            }
        });

    }

    @Override
    public void setUser(User user){
        userType.setText(user.getType().toString());
        idLabel.setText(user.getId());
        nameLabel.setText(user.getName());
        //collegeLabel.setText(user.getCollege());
        phoneField.setText(user.getPhone());
        mailField.setText(user.getEmail());

    }
}
