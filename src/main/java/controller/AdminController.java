package controller;

import controller.table.BookTable;
import controller.table.UserTable;
import controller.utils.ChildController;
import controller.utils.Parental;
import controller.utils.Transformer;
import factory.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminController extends BaseController implements Initializable, Parental {
    @FXML
    private Label adminLabel;

    @FXML
    private TableView<UserTable> userTable;
    @FXML
    private TableColumn<UserTable, String> userIDCol;
    @FXML
    private TableColumn<UserTable, String> userNameCol;
    @FXML
    private TableColumn<UserTable, String> userTypeCol;
    @FXML
    private TableColumn<UserTable, String> phoneCol;
    @FXML
    private TableColumn<UserTable, String> mailCol;
    @FXML
    private Button addUserButton;
    @FXML
    private Button editUserButton;
    @FXML
    private Button borrowUserButton;
    @FXML
    private TextField userSearchField;

    @FXML
    private TableView<BookTable> bookTable;
    @FXML
    private TableColumn<BookTable, Integer> bookIDCol;
    @FXML
    private TableColumn<BookTable, String> bookNameCol;
    @FXML
    private TableColumn<BookTable, String> bookTypeCol;
    @FXML
    private TableColumn<BookTable, String> startTimeCol;
    @FXML
    private TableColumn<BookTable, String> endTimeCol;
    @FXML
    private TableColumn<BookTable, String> borrowerCol;
    @FXML
    private TextField bookSearchField;
    @FXML
    private Button borrowBookButton;
    @FXML
    private Button backBookButton;
    @FXML
    private Button addBookButton;
    @FXML
    private Button editBookButton;

    private HashMap<UserTable, User> userMap = new HashMap<>();
    private ObservableList<UserTable> userData = FXCollections.observableArrayList();
    private HashMap<BookTable, Book> bookMap = new HashMap<>();
    private ObservableList<BookTable> bookData = FXCollections.observableArrayList();

    private void refreshBookTab() {
        bookMap.clear();
        bookData.clear();
        for (Book book: ServiceFactory.getBookInfoService().getAllBooks()) {
            BookTable bt = new BookTable(book);
            bookMap.put(bt, book);
            bookData.add(bt);
            bookTable.setItems(bookData);
            bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            bookTypeCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
            startTimeCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            endTimeCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            borrowerCol.setCellValueFactory(new PropertyValueFactory<>("borrower"));
        }
    }

    private void initBookTab(){
        refreshBookTab();
        Parental parent = this;
        addBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { Transformer.generateChild(parent, "bookInfo.fxml", user); }
        });
        editBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Book toEdit = bookMap.get(bookTable.getSelectionModel().getSelectedItem());
                ChildController childController = Transformer.generateChild(parent, "bookInfo.fxml", user);
                childController.setEdit(toEdit);
            }
        });

    }

    private void refreshUserTab() {
        userMap.clear();
        userData.clear();
        for (User user: ServiceFactory.getUserInfoService().getAllUsers()) {
            UserTable ut = new UserTable(user);
            userMap.put(ut, user);
            userData.add(ut);
            userTable.setItems(userData);
            userIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            userTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
    }

    private void initUserTab() {
        refreshUserTab();
        Parental parent = this;
        addUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transformer.generateChild(parent, "userInfo.fxml", user);
            }
        });
        editUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User toEdit = userMap.get(userTable.getSelectionModel().getSelectedItem());
                ChildController childController = Transformer.generateChild(parent, "userInfo.fxml", user);
                childController.setEdit(toEdit);
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUserTab();
        initBookTab();
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
        adminLabel.setText(user.getId());
    }

    @Override
    public void refresh() {
        refreshUserTab();
        refreshBookTab();
    }
}
