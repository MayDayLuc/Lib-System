package controller.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    private boolean flag;
    public boolean display(String title , String message){
        Stage window = new Stage();
        window.setTitle(title);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Button confirm = new Button("ȷ��");
        confirm.setOnAction(e -> {window.close();flag=true;});
        confirm.setOnKeyPressed(e ->{if(e.getCode()== KeyCode.ENTER)window.close();flag=true;});

        Button cancel = new Button("ȡ��");
        cancel.setOnAction(e -> {window.close();flag=false;});
        cancel.setOnKeyPressed(e ->{if(e.getCode()==KeyCode.ENTER)window.close();flag=false;});

        Label label = new Label(message);

        HBox button=new HBox(10);

        button.getChildren().addAll(confirm, cancel);
        button.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label , button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        return flag;
    }
}
