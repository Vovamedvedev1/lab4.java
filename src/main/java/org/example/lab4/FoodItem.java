package org.example.lab4;

import javafx.beans.property.*;

public class FoodItem {
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty quantity;
    private DoubleProperty totalPrice;

    public FoodItem(String name, double price, int quantity) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.totalPrice = new SimpleDoubleProperty(calculateTotalPrice());
        this.quantity.addListener((observable, oldValue, newValue) -> {
            totalPrice.set(calculateTotalPrice());
        });
        this.price.addListener((observable, oldValue, newValue) ->{
            totalPrice.set(calculateTotalPrice());
        });
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
        this.totalPrice.set(calculateTotalPrice());
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
        this.totalPrice.set(calculateTotalPrice());
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public DoubleProperty totalPriceProperty() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        return price.get() * quantity.get();
    }
}