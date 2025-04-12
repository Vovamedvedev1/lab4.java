package org.example.lab4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CheckboxesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane MainScene;

    @FXML
    private Button buttonProc;

    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check2;

    @FXML
    private CheckBox check3;

    @FXML
    private DatePicker dateCalendar;

    @FXML
    private Group group1;

    @FXML
    private Group group2;

    @FXML
    private Group group3;

    @FXML
    private TextField inputProc;

    @FXML
    private Label labelKemSU;

    @FXML
    private Label labelProc;

    @FXML
    private Hyperlink linkKemSU;

    @FXML
    private ProgressBar progressProc;

    @FXML
    void initialize() {
        assert MainScene != null : "fx:id=\"MainScene\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert buttonProc != null : "fx:id=\"buttonProc\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert check1 != null : "fx:id=\"check1\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert check2 != null : "fx:id=\"check2\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert check3 != null : "fx:id=\"check3\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert dateCalendar != null : "fx:id=\"dateCalendar\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert group1 != null : "fx:id=\"group1\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert group2 != null : "fx:id=\"group2\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert group3 != null : "fx:id=\"group3\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert inputProc != null : "fx:id=\"inputProc\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert labelKemSU != null : "fx:id=\"labelKemSU\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert labelProc != null : "fx:id=\"labelProc\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert linkKemSU != null : "fx:id=\"linkKemSU\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        assert progressProc != null : "fx:id=\"progressProc\" was not injected: check your FXML file 'Checkboxes.fxml'.";
        Pane parent = (Pane) MainScene;
        for (Node child : parent.getChildren()) {
            if (child instanceof Group)
                child.setVisible(false);
        }
        check1.setOnAction(e -> group1.setVisible(check1.isSelected()));
        check2.setOnAction(e -> group2.setVisible(check2.isSelected()));
        check3.setOnAction(e -> group3.setVisible(check3.isSelected()));
        buttonProc.setOnAction(actionEvent -> {
            if (inputProc.getText().trim().isEmpty()) {
                HelloApplication.showAlert("Предупреждение", "Ошибка", "Пожалуйста, введите процент!");
                return;
            }
            else {
                try {
                    double procent = Double.parseDouble(inputProc.getText().trim());
                    if (procent < 0 || procent>1) {
                        HelloApplication.showAlert("Предупреждение", "Ошибка", "Процент должен быть числом в диапозоне от 0 до 1!");
                        progressProc.setProgress(0);
                        return;
                    }
                    else {
                        progressProc.setProgress(procent);
                    }
                }
                catch (Exception e) {
                    HelloApplication.showAlert("Ошибка", "Строковое значения процента недопустимо", e.getMessage());
                    progressProc.setProgress(0);
                }
            }
        });

    }

}
