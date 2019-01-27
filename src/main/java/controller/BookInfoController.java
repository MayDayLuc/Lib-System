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
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.Book;
import model.BookCategory;
import model.BorrowInfo;
import model.EBook;
import model.enums.EBookType;
import model.enums.UserType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
    private CheckBox teacherCheckBox;
    @FXML
    private CheckBox undergraduateCheckBox;
    @FXML
    private CheckBox graduateCheckBox;
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

        String doc = DOCLabel.getText();
        if (doc.length() > 0 && ! new File(doc).exists()) {
            new AlertBox().display("错误信息", "DOC文件不存在！");
            return false;
        }

        String pdf = PDFLabel.getText();
        if (pdf.length() > 0 && ! new File(pdf).exists()) {
            new AlertBox().display("错误信息", "PDF文件不存在！");
            return false;
        }

        String epub = EPUBLabel.getText();
        if (epub.length() > 0 && ! new File(epub).exists()) {
            new AlertBox().display("错误信息", "EPUB文件不存在！");
            return false;
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

    private final FileChooser fileChooser = new FileChooser();

    private Label[] eBookLabels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        eBookLabels = new Label[] {DOCLabel, PDFLabel, EPUBLabel};

        getBookCategories();

        confirmButton.setOnAction(new AddHandler());

        permissionCheckBoxes = new CheckBox[] {teacherCheckBox, undergraduateCheckBox, graduateCheckBox};

        addDOCButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.setTitle("选择DOC");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("DOC", "*.doc"),
                        new FileChooser.ExtensionFilter("DOCX", "*.docx")
                );
                File file = fileChooser.showOpenDialog(stage);
                if (file != null)
                    DOCLabel.setText(file.getPath());
            }
        });

        addPDFButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.setTitle("选择PDF");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PDF", "*.pdf")
                );
                File file = fileChooser.showOpenDialog(stage);
                if (file != null)
                    PDFLabel.setText(file.getPath());
            }
        });

        addEPUBButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.setTitle("选择EPUB");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("EPUB", "*.epub")
                );
                File file = fileChooser.showOpenDialog(stage);
                if (file != null)
                    EPUBLabel.setText(file.getPath());
            }
        });
    }

    private CheckBox[] permissionCheckBoxes;

    private Book edit;

    @Override
    public void setEdit(Serializable bean) {
        edit = (Book) bean;
        BookCategory bc = edit.getCategory();
        bookNameField.setText(edit.getName());

        setPermission(edit.getPermission());

        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i).getId() == bc.getId())
                bookTypeBox.getSelectionModel().select(i);
        }

        for (EBook eBook: edit.getElectronicBooks())
            eBookLabels[eBook.getType().ordinal()].setText(eBook.getPath());

        confirmButton.setOnAction(new EditHandler());
    }

    private void setPermission(int permission) {
        for (int i = 0; i < UserType.getUserNum(); i ++) {
            if ((permission & (1 << i)) == 0)
                permissionCheckBoxes[i].setSelected(false);
        }
    }

    private int getPermission() {
        int permission = 0;
        for (int i = 0; i < UserType.getUserNum(); i ++) {
            if (permissionCheckBoxes[i].isSelected())
                permission |= (1 << i);
        }
        return permission;
    }

    private List<String> getEBooks(String book) {
        List<String> eBooks = new ArrayList<>();
        for (Label label: eBookLabels) {
            String eBook = label.getText();
            if (eBook.length() == 0){
                eBooks.add(null);
                continue;
            }
            File file = new File(eBook);
            String[] tmps = eBook.split("\\.");
            String postfix = tmps[tmps.length - 1];

            File file1Dir = new File(getClass().getResource("/").getPath().replaceAll("%20", " "));
            String path = "/ebooks/" + book + "/" + book + "." + postfix;
            String target1 = file1Dir.getPath() + path;
            File file1 = new File(target1);
            File parentFile1 = file1.getParentFile();
            if (! parentFile1.exists())
                parentFile1.mkdirs();

            String target2 = "src/main/resources" + path;
            File file2 = new File(target2);
            File parentFile2 = file2.getParentFile();
            if (! parentFile2.exists())
                parentFile2.mkdirs();

            try {
                if (! file.getAbsolutePath().equals(file1.getAbsolutePath())) {
                    file1.delete();
                    Files.copy(file.toPath(), file1.toPath());
                }

                if (! file.getAbsolutePath().equals(file2.getAbsolutePath())) {
                    file2.delete();
                    Files.copy(file.toPath(), file2.toPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            eBooks.add(target1);
        }
        return eBooks;
    }

    private class EditHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            if (!checkField())
                return;
            String bookName = bookNameField.getText();
            List<String> eBooks = getEBooks(bookName);
            edit.setName(bookName);
            edit.setCategory(list.get(bookTypeBox.getSelectionModel().getSelectedIndex()));
            edit.setPermission(getPermission());
            edit.setElectronicBooks(eBooks);
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
            String bookName = bookNameField.getText();
            List<String> eBooks = getEBooks(bookName);
            Book book1 = new Book(bookName,
                    list.get(bookTypeBox.getSelectionModel().getSelectedIndex()),
                    getPermission(),
                    eBooks
                    );
            ServiceFactory.getBookInfoService().insertBook(book1);
            new AlertBox().display("提示", "添加成功!");
            stage.close();
            parentController.refresh();
        }
    }
}
