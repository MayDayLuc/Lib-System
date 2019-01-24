package controller;

import controller.Table.userBookTable;
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
import java.util.ArrayList;
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
    private TableView<userBookTable> bookTable;
    @FXML
    private TableColumn<Book, Integer> bookIDCol;
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

    private HashMap<userBookTable, Integer> map=new HashMap<userBookTable, Integer>();

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
        refresh();
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
               modifyInfo();
            }
        });

        readDOCButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                userBookTable book = bookTable.getSelectionModel().getSelectedItem();
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
                userBookTable book = bookTable.getSelectionModel().getSelectedItem();
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
                userBookTable book = bookTable.getSelectionModel().getSelectedItem();
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
        ObservableList<userBookTable> data = FXCollections.observableArrayList();
        map.clear();
        for(int i=0;i<list.size();i++) {
            Book book=list.get(i);
            if(book.getLastBorrow().getBorrower().equals(user.getName())){
                userBookTable userbookTable = new userBookTable(book);
                data.add(userbookTable);
                map.put(userbookTable, i);
                bookTable.setItems(data);
                bookIDCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookID"));
                bookNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));
                bookTypeCol.setCellValueFactory(new PropertyValueFactory<Book, BookCategory>("bookCategory"));
                startTimeCol.setCellValueFactory(new PropertyValueFactory<BorrowInfo, LocalDate>("startTime"));
                endTimeCol.setCellValueFactory(new PropertyValueFactory<BorrowInfo, LocalDate>("endTime"));

            }

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

    }
    private List<Book> list = ServiceFactory.getBorrowInfoService().getMyBorrowedBooks(idLabel.getText());


}
