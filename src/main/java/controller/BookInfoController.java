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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Book;
import model.BookCategory;
import model.BorrowInfo;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class BookInfoController extends ChildController implements Initializable{
    @FXML
    private Button addDOCButton;
    @FXML
    private Button addPDFButton;
    @FXML
    private Button addEPUBButton;
    @FXML
    private Label DOCLabel;
    @FXML
    private Label PDFLabel;
    @FXML
    private Label EPUBLabel;

    @FXML
    private TextField bookNameField;
    @FXML
    private ChoiceBox<String> bookTypeBox;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label borrowerLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private boolean checkField() {
        if (bookNameField.getText().length() == 0) {
            new AlertBox().display("错误信息", "图书名称不能为空！");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        bookTypeBox.getItems().addAll("社会学", "历史");
        bookTypeBox.getSelectionModel().select(0);

        confirmButton.setOnAction(new AddHandler());
    }

    private Book edit;
    private BookCategory bC = edit.getCategory();
    private BorrowInfo bI = edit.getLastBorrow();

    @Override
    public void setEdit(Serializable bean) {
        edit = (Book) bean;
        bookNameField.setText(edit.getName());
        bookTypeBox.getSelectionModel().select(bC.getName());
        startTimeLabel.setText(bI.getBorrowDate().toString());
        endTimeLabel.setText(bI.getDueDate().toString());
        borrowerLabel.setText(bI.getBorrower().getName());
        confirmButton.setOnAction(new EditHandler());
    }

    private class EditHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            if (!checkField())
                return;
            Book book1 = new Book(bookNameField.getText(),

                    );
            if(ServiceFactory.getBookInfoService().insertBook(book1)){
                new AlertBox().display("提示", "添加成功!");
                stage.close();
                parentController.refresh();
            }
        }
    }

    private class AddHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            if (!checkField())
                return;
            Book book1 = new Book(bookNameField.getText(),

                    );
            ServiceFactory.getBookInfoService().updateBokkInfo(book1);
            new AlertBox().display("提示", "编辑成功!");
            stage.close();
            parentController.refresh();
        }
    }
}
