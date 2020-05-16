package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

class PopUpView {
    // hide public constructor to avoid instantiation
    private PopUpView() {}

    // displays a little pop up window containing the message
    // can be closed pressing the "okay" button
    static void display(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label label1 = new Label(message);
        Button button1 = new Button("Okay");
        button1.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout);
        window.setScene(scene1);
        window.showAndWait();
    }
}