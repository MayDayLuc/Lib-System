package controller;

import controller.table.BookTable;
import controller.table.BorrowTable;
import controller.table.UserBookTable;
import controller.table.UserTable;
import controller.utils.*;
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
import model.BorrowInfo;
import model.User;
import service.BorrowService;
import service.enums.AddBookResult;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminController extends BaseController implements Initializable, Parental {
    @FXML
    private Label adminLabel;
    @FXML
    private TabPane adminTab;

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

    @FXML
    private TableView<BorrowTable> borrowTable;
    @FXML
    private TableColumn<BorrowTable, Integer> borrowBookIDCol;
    @FXML
    private TableColumn<BorrowTable, String> borrowBookNameCol;
    @FXML
    private TableColumn<BorrowTable, String> borrowBorrowerCol;
    @FXML
    private TableColumn<BorrowTable, String> borrowBorrowDateCol;
    @FXML
    private TableColumn<BorrowTable, String> borrowDueDateCol;
    @FXML
    private TextField borrowSearchField;

    @FXML
    private Tab registerTab;
    @FXML
    private Label registerBorrowerLabel;
    @FXML
    private TableView<UserBookTable> registerTable;
    @FXML
    private TableColumn<UserBookTable, Integer> registerBookIDCol;
    @FXML
    private TableColumn<UserBookTable, String> registerBookNameCol;
    @FXML
    private TableColumn<UserBookTable, String> registerBookCategoryCol;
    @FXML
    private TableColumn<UserBookTable, String> registerBorrowDateCol;
    @FXML
    private TableColumn<UserBookTable, String> registerDueDateCol;
    @FXML
    private Button registerDeleteButton;
    @FXML
    private Label warningsLabel;
    @FXML
    private Button registerConfirmButton;

    private HashMap<UserTable, User> userMap = new HashMap<>();
    private HashMap<User, UserTable> reverseUserMap = new HashMap<>();
    private ObservableList<UserTable> userData = FXCollections.observableArrayList();

    private HashMap<BookTable, Book> bookMap = new HashMap<>();
    private HashMap<Book, BookTable> reverseBookMap = new HashMap<>();
    private ObservableList<BookTable> bookData = FXCollections.observableArrayList();

    private HashMap<BorrowTable, BorrowInfo> borrowMap = new HashMap<>();
    private ObservableList<BorrowTable> borrowData = FXCollections.observableArrayList();

    private HashMap<UserBookTable, Book> registerMap = new HashMap<>();
    private HashMap<Book, UserBookTable> reverseRegisterMap = new HashMap<>();
    private ObservableList<UserBookTable> registerData = FXCollections.observableArrayList();

    private void refreshBorrowTab(){
        borrowMap.clear();
        borrowData.clear();
        for (BorrowInfo borrowInfo: ServiceFactory.getBorrowInfoService().getAllBorrowedBooks()){
            BorrowTable borrowT = new BorrowTable(borrowInfo);
            borrowMap.put(borrowT,borrowInfo);
            borrowData.add(borrowT);
        }
    }

    private void initBorrowTab(){
        borrowTable.setItems(borrowData);
        borrowBookIDCol.setCellValueFactory(new PropertyValueFactory<>("borrowBookId"));
        borrowBookNameCol.setCellValueFactory(new PropertyValueFactory<>("borrowBookName"));
        borrowBorrowerCol.setCellValueFactory(new PropertyValueFactory<>("borrowBorrower"));
        borrowBorrowDateCol.setCellValueFactory(new PropertyValueFactory<>("borrowBorrowDate"));
        borrowDueDateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDueDate"));
    }


    private void refreshBookTab() {
        bookMap.clear();
        reverseBookMap.clear();
        bookData.clear();
        for (Book book: ServiceFactory.getBookInfoService().getAllBooks()) {
            BookTable bt = new BookTable(book);
            bookMap.put(bt, book);
            reverseBookMap.put(book, bt);
            bookData.add(bt);
        }
    }

    private void initBookTab(){
        bookTable.setItems(bookData);
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookTypeCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        borrowerCol.setCellValueFactory(new PropertyValueFactory<>("borrower"));
        Parental parent = this;
        addBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transformer.generateChild(parent, "bookInfo.fxml", user);
            }
        });
        editBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Book toEdit = bookMap.get(bookTable.getSelectionModel().getSelectedItem());
                if (toEdit == null) {
                    new AlertBox().display("错误信息", "请选择需要编辑的图书！");
                    return;
                }
                ChildController childController = Transformer.generateChild(parent, "bookInfo.fxml", user);
                childController.setEdit(toEdit);
            }
        });
        borrowBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BookTable bt = bookTable.getSelectionModel().getSelectedItem();
                Book toBorrow = bookMap.get(bt);
                if (toBorrow == null) {
                    new AlertBox().display("错误信息", "请选择需要借阅的图书！");
                    return;
                }
                AddBookResult result = borrowService.addBook(toBorrow);
                checkNum();
                if (result == AddBookResult.REPEAT) {
                    new AlertBox().display("错误信息", "已经借阅该图书！");
                    return;
                }
                if (result == AddBookResult.NOT_AVAILABLE) {
                    new AlertBox().display("错误信息", "该图书还未归还！");
                    return;
                }
                bt.borrowerProperty().setValue("√");
                UserBookTable ubt = new UserBookTable(toBorrow);
                registerData.add(ubt);
                registerMap.put(ubt, toBorrow);
                reverseRegisterMap.put(toBorrow, ubt);
                if (result == AddBookResult.NO_PERMISSION) {
                    ubt.setPermission(false);
                }
                adminTab.getSelectionModel().select(registerTab);
            }
        });
    }


    private void refreshUserTab() {
        userMap.clear();
        reverseUserMap.clear();
        userData.clear();
        for (User user: ServiceFactory.getUserInfoService().getAllUsers()) {
            UserTable ut = new UserTable(user);
            userMap.put(ut, user);
            reverseUserMap.put(user, ut);
            userData.add(ut);
        }
    }

    private void initUserTab() {
        userTable.setItems(userData);
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
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
                if (toEdit == null) {
                    new AlertBox().display("错误信息", "请选择需要编辑的用户！");
                    return;
                }
                ChildController childController = Transformer.generateChild(parent, "userInfo.fxml", user);
                childController.setEdit(toEdit);
            }
        });
        borrowUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserTable ut = userTable.getSelectionModel().getSelectedItem();
                User selected = userMap.get(ut);
                if (selected == null) {
                    new AlertBox().display("错误信息", "请选择需要借阅的用户！");
                    return;
                }
                UserTable lut = reverseUserMap.get(borrowService.getUser());
                if (lut != null)
                    lut.idProperty().setValue(lut.idProperty().get().substring(1));
                registerBorrowerLabel.setText(selected.getId());
                ut.idProperty().setValue("√" + ut.idProperty().get());
                boolean permissionChanged = borrowService.setUser(selected);
                checkNum();
                if (permissionChanged)
                    checkPermissions();
                for (UserBookTable ubt: registerData) {
                    Book book = registerMap.get(ubt);
                    BorrowInfo info = book.getLastBorrow();
                    if (info != null)
                        ubt.setBorrow(info);
                }
                adminTab.getSelectionModel().select(registerTab);
            }
        });
    }


    private void refreshRegisterTab() {
        registerMap.clear();
        reverseRegisterMap.clear();
        registerData.clear();
        registerBorrowerLabel.setText("");
        borrowService = ServiceFactory.getBorrowService();
    }

    private void initRegisterTab() {
        registerTable.setItems(registerData);
        registerBookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        registerBookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        registerBookCategoryCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        registerBorrowDateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        registerDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        registerDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserBookTable ubt = registerTable.getSelectionModel().getSelectedItem();
                Book toDelete = registerMap.get(ubt);
                if (toDelete == null) {
                    new AlertBox().display("错误信息", "请选择需要删除的图书！");
                    return;
                }
                borrowService.remove(toDelete);
                checkNum();
                reverseBookMap.get(toDelete).borrowerProperty().setValue("");
                registerData.remove(ubt);
            }
        });
        registerConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (borrowService.getUser() == null) {
                    new AlertBox().display("错误信息", "没有选择借阅者！");
                    return;
                }
                if (registerData.size() == 0) {
                    new AlertBox().display("错误信息", "没有选择图书！");
                    return;
                }
                String warning = warningsLabel.getText();
                Set<Book> noPermission = borrowService.getNoPermission();
                if (warning.length() == 0 && noPermission.size() == 0) {
                    borrowService.commit();
                    new AlertBox().display("提示信息", "借阅成功！");
                    refresh();
                }
                else {
                    String errorMessage = "";
                    if (warning.length() > 0)
                        errorMessage += warning + System.lineSeparator();
                    if (noPermission.size() > 0)
                        errorMessage += "表中存在图书没有借阅权限！" + System.lineSeparator();
                    new AlertBox().display("错误信息", errorMessage);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        initUserTab();
        initBookTab();
        initBorrowTab();
        initRegisterTab();
    }

    private void checkNum() {
        if (borrowService.isOverdue()) {
            warningsLabel.setText("请先归还超期图书");
            return;
        }
        String warnings = "";
        if (borrowService.isOverSingleLimit())
            warnings += "超过单次借阅数量限制 ";
        if (borrowService.isOverLimit())
            warnings += "超过借阅总数量限制";
        warningsLabel.setText(warnings);
    }

    private void checkPermissions() {
        Set<Book> noPermissions = borrowService.getNoPermission();
        for (UserBookTable ubt: registerData) {
            Book book = registerMap.get(ubt);
            if (noPermissions.contains(book))
                ubt.setPermission(false);
            else
                ubt.setPermission(true);
        }
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
        adminLabel.setText(user.getId());
    }

    private BorrowService borrowService;

    @Override
    public void refresh() {
        refreshUserTab();
        refreshBookTab();
        refreshBorrowTab();
        refreshRegisterTab();
    }
}
