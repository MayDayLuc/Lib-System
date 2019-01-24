package controller;

import controller.table.UserBookTable;
import controller.utils.AlertBox;
import factory.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Book;
import model.BookCategory;
import model.BorrowInfo;
import model.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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

    private HashMap<UserBookTable, Integer> map= new HashMap<>();

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

        readDOCButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                UserBookTable book = bookTable.getSelectionModel().getSelectedItem();
                if (book == null){
                    new AlertBox().display("错误信息", "请选择书");
                    return;
                }
                int index=map.get(book);
                Book b=list.get(index);
                //打开阅读器
            }
        });

        readPDFButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                UserBookTable book = bookTable.getSelectionModel().getSelectedItem();
                if (book == null){
                    new AlertBox().display("错误信息", "请选择书");
                    return;
                }
                int index=map.get(book);
                Book b=list.get(index);
                //打开阅读器
            }
        });

        readEPUBButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                UserBookTable book = bookTable.getSelectionModel().getSelectedItem();
                if (book == null){
                    new AlertBox().display("错误信息", "请选择书");
                    return;
                }
                int index=map.get(book);
                Book b=list.get(index);
                //打开阅读器
            }
        });

    }

    public void refresh(){
        list = ServiceFactory.getBorrowInfoService().getMyBorrowedBooks(idLabel.getText());
        ObservableList<UserBookTable> data = FXCollections.observableArrayList();
        map.clear();
        for(int i=0;i<list.size();i++) {
            Book book=list.get(i);
            UserBookTable userbookTable = new UserBookTable(book);
            data.add(userbookTable);
            map.put(userbookTable, i);
            bookTable.setItems(data);
            bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            bookTypeCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
            startTimeCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            endTimeCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        }
    }

    @Override
    public void setUser(User user){
        userType.setText(transform(user.getType()));
        idLabel.setText(user.getId());
        nameLabel.setText(user.getName());
        //collegeLabel.setText(user.getCollege());
        phoneField.setText(user.getPhone());
        mailField.setText(user.getEmail());

        refresh();
    }
    private List<Book> list;


}
