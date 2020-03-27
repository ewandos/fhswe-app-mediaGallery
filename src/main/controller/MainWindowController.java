package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends AbstractController{

    // Variable-name must be the fx:id
    public Label label;
    public TextField field;
    public Button changeText;
    public Button clear;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        super.initialize(url, resources);
        label.textProperty().bind(field.textProperty());
    }

    @FXML
    public void handleChangeText(ActionEvent actionEvent) {

        String text = field.textProperty().get();
        label.textProperty().set(text);
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        label.textProperty().bind(field.textProperty());
        label.setText("");
    }
}
