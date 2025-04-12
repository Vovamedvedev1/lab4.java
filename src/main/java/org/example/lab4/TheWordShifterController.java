package org.example.lab4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class TheWordShifterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonGuide;

    @FXML
    private TextField LeftString;

    @FXML
    private TextField RightString;

    @FXML
    void initialize() {
        assert ButtonGuide != null : "fx:id=\"ButtonGuide\" was not injected: check your FXML file 'TheWordShifterController.fxml'.";
        assert LeftString != null : "fx:id=\"LeftString\" was not injected: check your FXML file 'TheWordShifterController.fxml'.";
        assert RightString != null : "fx:id=\"RightString\" was not injected: check your FXML file 'TheWordShifterController.fxml'.";
        ButtonGuide.setOnAction(actionEvent -> {
            if (ButtonGuide.getText().equals("→"))
                this.shifter(LeftString, RightString, "←", "#FFC0CB", "левое поле");
            else if (ButtonGuide.getText().equals("←"))
                this.shifter(RightString, LeftString, "→", "#00FF00", "правое поле");
        });
    }

    void shifter(TextField first, TextField second, String arrow, String color, String err) {
        String textFirst = first.getText().trim();
        if (textFirst.isEmpty()) {
            HelloApplication.showAlert("Предупреждение", "Ошибка", "Пожалуйста, введите текст в " + err + "!");
            return;
        }
        String textSecond = second.getText().trim();
        String separator = textSecond.isEmpty() ? "" : "; ";
        textSecond += (separator + textFirst);
        second.setText(textSecond);
        first.setText("");
        ButtonGuide.setText(arrow);
        ButtonGuide.setStyle("-fx-background-color: " + color + ";");
    }
}