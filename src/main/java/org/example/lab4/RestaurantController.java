package org.example.lab4;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class RestaurantController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addDishButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<FoodItem, String> itemNameColumn;

    @FXML
    private TableColumn<FoodItem, Double> itemPriceColumn;

    @FXML
    private TableColumn<FoodItem, Integer> itemQuantityColumn;

    @FXML
    private TableColumn<FoodItem, Double> itemTotalPriceColumn;

    @FXML
    private ListView<FoodItem> menuListView;

    @FXML
    private TableView<FoodItem> orderTableView;

    @FXML
    private Label totalPriceLabel;

    private ObservableList<FoodItem> menuItems = FXCollections.observableArrayList();
    private ObservableList<FoodItem> orderItems = FXCollections.observableArrayList();
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());

    @FXML
    void adDish(ActionEvent event) {
        Stage addStage = new Stage();
        addStage.setTitle("Добавить пункт меню");
        Label nameLabel = new Label("Название блюда:");
        TextField nameField = new TextField();
        Label priceLabel = new Label("Цена:");
        TextField priceField = new TextField();
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String priceText = priceField.getText();
            if (name.equals("") || priceText.equals("")) {
                HelloApplication.showAlert("Предупреждение", "Пустые поля", "Пожалуйста, заполните форму!");
                return;
            }
            try {
                double price = Double.parseDouble(priceText);
                if (price > 0) {
                    menuItems.add(new FoodItem(name, price,0));
                    addStage.close();
                } else {
                    HelloApplication.showAlert("Предупреждение", "Отрицательная цена", "Пожалуйста, введите положительный цену!");
                    priceField.setText("");
                    return;
                }
            } catch (NumberFormatException ex) {
                HelloApplication.showAlert("Предупреждение", "Нечисловая цена", "Пожалуйста, введите числовую цену!");
                priceField.setText("");
                return;
            }
        });
        VBox addLayout = new VBox(10);
        addLayout.setAlignment(Pos.CENTER);
        addLayout.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, addButton);
        Scene addScene = new Scene(addLayout, 300, 250);
        addStage.setScene(addScene);
        addStage.show();
    }

    @FXML
    void clearOrder(ActionEvent event) {
        orderItems.clear();
        updateTotalPrice();
    }

    @FXML
    void initialize() {
        assert addDishButton != null : "fx:id=\"addDishButton\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert itemNameColumn != null : "fx:id=\"itemNameColumn\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert itemPriceColumn != null : "fx:id=\"itemPriceColumn\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert itemQuantityColumn != null : "fx:id=\"itemQuantityColumn\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert itemTotalPriceColumn != null : "fx:id=\"itemTotalPriceColumn\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert menuListView != null : "fx:id=\"menuListView\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert orderTableView != null : "fx:id=\"orderTableView\" was not injected: check your FXML file 'Restaurant.fxml'.";
        assert totalPriceLabel != null : "fx:id=\"totalPriceLabel\" was not injected: check your FXML file 'Restaurant.fxml'.";

        menuItems.addAll(
            new FoodItem("Котлета", 12.99, 0),
            new FoodItem("Борщь", 17.50, 0),
            new FoodItem("Рассольник", 4.95, 0),
            new FoodItem("Картошка", 11.158, 0),
            new FoodItem("Мороженое", 6.456, 0),
            new FoodItem("Пирожок с картошкой", 23.546, 0),
            new FoodItem("Пирожок с капустой", 67.546, 0),
            new FoodItem("Пирожок с печенью", 436.5732, 0),
            new FoodItem("Пирожок с ливером", 236.678, 0),
            new FoodItem("Торог", 562.614, 0),
            new FoodItem("Пицца", 112.734, 0),
            new FoodItem("Ризотто", 109.461, 0),
            new FoodItem("Стейк", 1078.045, 0),
            new FoodItem("Запеканка", 913.927, 0)
        );
        menuListView.setItems(menuItems);
        menuListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(FoodItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - " + currencyFormat.format(item.getPrice()));
                }
            }
        });
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemTotalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());
        orderTableView.setItems(orderItems);
        menuListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FoodItem selectedItem = menuListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    showQuantityDialog(selectedItem);
                }
            }
        });
    }
    private void showQuantityDialog(FoodItem item) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Выбор количества");
        dialog.setHeaderText("Введите количество для " + item.getName());
        ButtonType addButtonType = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField quantityField = new TextField();
        quantityField.setPromptText("Количество");
        grid.add(new Label("Количество:"), 0, 0);
        grid.add(quantityField, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                if (quantityField.getText().equals("")) {
                    HelloApplication.showAlert("Ошибка", "Пустая строка", "Пожалуйста, введите количество.");
                }
                else {
                    try {
                        int quantity = Integer.parseInt(quantityField.getText());
                        if (quantity < 0) {
                            HelloApplication.showAlert("Ошибка", "Отрицательное количество", "Пожалуйста, введите положительное целое количество.");
                            quantityField.setText("");
                        } else {
                            return quantity;
                        }
                    } catch (NumberFormatException e) {
                        HelloApplication.showAlert("Ошибка", "Неверный формат", "Пожалуйста, введите целое количество.");
                        quantityField.setText("");
                    }
                }
            }
            return null;
        });
        dialog.showAndWait().ifPresent(quantity -> {
            if (quantity != null && quantity > 0) {
                addItemToOrder(item, quantity);
            }
        });
    }

    private void addItemToOrder(FoodItem item, int quantity) {
        FoodItem existingItem = orderItems.stream()
                .filter(orderItem -> orderItem.getName().equals(item.getName()))
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            FoodItem newItem = new FoodItem(item.getName(), item.getPrice(), quantity);
            orderItems.add(newItem);
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = orderItems.stream()
                .mapToDouble(FoodItem::getTotalPrice)
                .sum();
        totalPriceLabel.setText("Итого: " + currencyFormat.format(total));
    }

}































/**
        menuItems.addAll(
                new FoodItem("Котлета", 12.99),
                new FoodItem("Борщь", 17.50),
                new FoodItem("Рассольник", 4.95),
                new FoodItem("Картошка", 11.158),
                new FoodItem("Мороженое", 6.456),
                new FoodItem("Пирожок с картошкой", 23.546),
                new FoodItem("Пирожок с капустой", 67.546),
                new FoodItem("Пирожок с печенью", 436.5732),
                new FoodItem("Пирожок с ливером", 236.678),
                new FoodItem("Торог", 562.614),
                new FoodItem("Пицца", 112.734),
                new FoodItem("Ризотто", 109.461),
                new FoodItem("Стейк", 1078.045),
                new FoodItem("Запеканка", 913.927)
        );
        adDish.setOnAction(event -> {
            Stage addStage = new Stage();
            addStage.setTitle("Добавить пункт меню");
            Label nameLabel = new Label("Название блюда:");
            TextField nameField = new TextField();
            Label priceLabel = new Label("Цена:");
            TextField priceField = new TextField();
            Button addButton = new Button("Добавить");
            addButton.setOnAction(e -> {
                String name = nameField.getText();
                String priceText = priceField.getText();
                if (name.equals("") || priceText.equals("")) {
                    HelloApplication.showAlert("Предупреждение", "Пустые поля", "Пожалуйста, заполните форму!");
                    return;
                }
                try {
                    double price = Double.parseDouble(priceText);
                    if (price > 0) {
                        menuItems.add(new FoodItem(name, price));
                        addStage.close();
                    } else {
                        HelloApplication.showAlert("Предупреждение", "Отрицательная цена", "Пожалуйста, введите положительный цену!");
                        priceField.setText("");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    HelloApplication.showAlert("Предупреждение", "Нечисловая цена", "Пожалуйста, введите числовую цену!");
                    priceField.setText("");
                    return;
                }
            });
            VBox addLayout = new VBox(10);
            addLayout.setAlignment(Pos.CENTER);
            addLayout.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, addButton);
            Scene addScene = new Scene(addLayout, 300, 250);
            addStage.setScene(addScene);
            addStage.show();
        });
    }
**/