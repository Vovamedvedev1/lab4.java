package org.example.lab4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Calculator;

    @FXML
    private Button Checkboxes;

    @FXML
    private Button Restaurant;

    @FXML
    private Button TextFlag;

    @FXML
    private Button TheWordShifter;

    @FXML
    void clickOnMenuButton(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-background-color: red;");
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
                    sourceButton.setStyle("-fx-background-color: #ffa500;");
                })
        );
        timeline.play();
        String title = "";
        String name = "";
        if (sourceButton == TheWordShifter) {
            title = "Перекидыватель слов";
            name = "TheWordShifterController.fxml";
        } else if (sourceButton == Checkboxes) {
            title = "Чекбоксы";
            name = "Checkboxes.fxml";
        } else if (sourceButton == Restaurant) {
            title = "Ресторан";
            name = "Restaurant.fxml";
        } else if (sourceButton == TextFlag) {
            title = "Текстовый флаг";
            name = "TextFlag.fxml";
        } else if (sourceButton == Calculator) {
            title = "Калькулятор";
            name = "Сalculator.fxml";
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            AnchorPane root = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            HelloApplication.showAlert("Ошибка", "Ошибка открытия окна " + title, e.getMessage());

        }
    }

    @FXML
    void initialize() {
        assert Calculator != null : "fx:id=\"Calculator\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Checkboxes != null : "fx:id=\"Checkboxes\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Restaurant != null : "fx:id=\"Restaurant\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert TextFlag != null : "fx:id=\"TextFlag\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert TheWordShifter != null : "fx:id=\"TheWordShifter\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
