package controller;

import controller.utils.AlertBox;
import controller.utils.ChildController;
import factory.ServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
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
    private TextField newTypeField;
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
        int index = bookTypeBox.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new AlertBox().display("错误信息", "请选择图书类型！");
            return false;
        }

        if (index == list.size() - 1) {
            String type = newTypeField.getText();
            if (type.length() == 0) {
                new AlertBox().display("错误信息", "请输入新的图书类型！");
                return false;
            }
            list.get(index).setName(type);
        }
        return true;
    }

    private ArrayList<BookCategory> list = new ArrayList<>();
    private void getBookCategories() {
        ObservableList<String> types = bookTypeBox.getItems();
        for (BookCategory category: ServiceFactory.getBookInfoService().getAllBookCategories()) {
            types.add(category.getName());
            list.add(category);
        }
        types.add("其它……");
        list.add(new BookCategory());
        bookTypeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("其它……")) {
                    newTypeField.setVisible(true);
                    newTypeField.setAccessibleHelp("请输入新的类别");
                }
                else
                    newTypeField.setVisible(false);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        getBookCategories();

        confirmButton.setOnAction(new AddHandler());
    }

    private Book edit;

    @Override
    public void setEdit(Serializable bean) {
        edit = (Book) bean;
        BookCategory bc = edit.getCategory();
        BorrowInfo bi = edit.getLastBorrow();
        bookNameField.setText(edit.getName());

        for (int i = 0; i < list.size(); i ++)
            if (list.get(i).getId() == bc.getId())
                bookTypeBox.getSelectionModel().select(i);
        if (bi != null) {
            startTimeLabel.setText(bi.getBorrowDate().toString());
            endTimeLabel.setText(bi.getDueDate().toString());
            borrowerLabel.setText(bi.getBorrower().getName());
        }
        confirmButton.setOnAction(new EditHandler());
    }

    private class EditHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            if (!checkField())
                return;
            edit.setName(bookNameField.getText());
            edit.setCategory(list.get(bookTypeBox.getSelectionModel().getSelectedIndex()));
            ServiceFactory.getBookInfoService().updateBookInfo(edit);
            new AlertBox().display("提示", "编辑成功!");
            stage.close();
            parentController.refresh();
        }
    }

    private class AddHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            if (!checkField())
                return;
            Book book1 = new Book(bookNameField.getText(),
                    list.get(bookTypeBox.getSelectionModel().getSelectedIndex())
                    );
            ServiceFactory.getBookInfoService().insertBook(book1);
            new AlertBox().display("提示", "添加成功!");
            stage.close();
            parentController.refresh();
        }
    }
}
