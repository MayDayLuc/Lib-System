package controller;

import controller.table.UserBookTable;
import controller.utils.AlertBox;
import controller.utils.BaseController;
import factory.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Book;
import model.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static model.enums.UserType.transform;

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
    private TableView<UserBookTable> bookTable;
    @FXML
    private TableColumn<UserBookTable, Integer> bookIDCol;
    @FXML
    private TableColumn<UserBookTable, String> bookNameCol;
    @FXML
    private TableColumn<UserBookTable, String> bookTypeCol;
    @FXML
    private TableColumn<UserBookTable, String> startTimeCol;
    @FXML
    private TableColumn<UserBookTable, String> endTimeCol;
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

    private HashMap<UserBookTable, Book> map= new HashMap<>();
    private ObservableList<UserBookTable> data = FXCollections.observableArrayList();

    private void modifyInfo(){
        user.setPhone(phoneField.getText());
        user.setEmail(mailField.getText());
        ServiceFactory.getUserInfoService().updateUserInfo(user);
        new AlertBox().display("提示", "保存成功");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void refresh(){
        data.clear();
        map.clear();
        for(Book book: ServiceFactory.getBorrowInfoService().getMyBorrowedBooks(user.getId())) {

            UserBookTable userbookTable = new UserBookTable(book);
            data.add(userbookTable);
            map.put(userbookTable, book);
        }
        bookTable.setItems(data);
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookTypeCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    @Override
    public void setUser(User user){
        super.setUser(user);
        userType.setText(transform(user.getType()));
        idLabel.setText(user.getId());
        nameLabel.setText(user.getName());
        //collegeLabel.setText(user.getCollege());
        phoneField.setText(user.getPhone());
        mailField.setText(user.getEmail());

        refresh();
        setButtonFunction();
    }

    private void setButtonFunction() {
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                modifyInfo();
            }
        });
    }


}
