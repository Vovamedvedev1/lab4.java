package org.example.lab4;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TextFlagController {
    public static final HashMap<String, Color> colors = new HashMap<>() {{
        put("Красный", Color.RED);
        put("Синий", Color.BLUE);
        put("Зеленый", Color.GREEN);
        put("Желтый", Color.YELLOW);
        put("Оранжевый", Color.ORANGE);
        put("Розовый", Color.PINK);
        put("Коричневый", Color.BROWN);
        put("Бордовый", Color.DARKVIOLET);
    }};
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Rectangle botFlag;

    @FXML
    private Button clearButton;

    @FXML
    private ToggleGroup groupBot;

    @FXML
    private ToggleGroup groupMiddle;

    @FXML
    private ToggleGroup groupTop;

    @FXML
    private Label labelResult;

    @FXML
    private Rectangle middleFlag;

    @FXML
    private Button resultButton;

    @FXML
    private Rectangle topFlag;

    @FXML
    void initialize() {
        assert botFlag != null : "fx:id=\"botFlag\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert groupBot != null : "fx:id=\"groupBot\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert groupMiddle != null : "fx:id=\"groupMiddle\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert groupTop != null : "fx:id=\"groupTop\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert labelResult != null : "fx:id=\"labelResult\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert middleFlag != null : "fx:id=\"middleFlag\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert resultButton != null : "fx:id=\"resultButton\" was not injected: check your FXML file 'TextFlag.fxml'.";
        assert topFlag != null : "fx:id=\"topFlag\" was not injected: check your FXML file 'TextFlag.fxml'.";

        resultButton.setOnAction(actionEvent -> {
            RadioButton butTop = (RadioButton) groupTop.getSelectedToggle();
            if (butTop == null) {
                HelloApplication.showAlert("Предупреждение", "Выберите цвет верхней полосы флага!!!", "Выберите цвет верхней полосы флага!!!");
                return;
            }
            String colorTextTop = butTop.getText();
            RadioButton butMiddle = (RadioButton) groupMiddle.getSelectedToggle();
            if (butMiddle == null) {
                HelloApplication.showAlert("Предупреждение", "Выберите цвет средней полосы флага!!!", "Выберите цвет средней полосы флага!!!");
                return;
            }
            String colorTextMiddle = butMiddle.getText();
            RadioButton butBot = (RadioButton) groupBot.getSelectedToggle();
            if (butBot == null) {
                HelloApplication.showAlert("Предупреждение", "Выберите цвет нижней полосы флага!!!", "Выберите цвет нижней полосы флага!!!");
                return;
            }
            String colorTextBot = butBot.getText();
            labelResult.setText(colorTextTop + "; "+ colorTextMiddle + "; " + colorTextBot);
            topFlag.setFill(TextFlagController.colors.getOrDefault(colorTextTop, Color.WHITE));
            middleFlag.setFill(TextFlagController.colors.getOrDefault(colorTextMiddle, Color.WHITE));
            botFlag.setFill(TextFlagController.colors.getOrDefault(colorTextBot, Color.WHITE));

        });
        clearButton.setOnAction(actionEvent -> {
            labelResult.setText("");
            if (groupTop.getSelectedToggle() != null)
                groupTop.getSelectedToggle().setSelected(false);
            if (groupMiddle.getSelectedToggle() != null)
                groupMiddle.getSelectedToggle().setSelected(false);
            if (groupBot.getSelectedToggle() != null)
                groupBot.getSelectedToggle().setSelected(false);
            topFlag.setFill(Color.WHITE);
            middleFlag.setFill(Color.WHITE);
            botFlag.setFill(Color.WHITE);
        });
    }
}

