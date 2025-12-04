package com.pos.client.controllers;

import com.pos.client.api.ApiClient;
import com.pos.client.api.ProductDto;
import com.pos.client.models.CartItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class PosController {
    @FXML
    private TextField skuInput;
    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> colName;
    @FXML private TableColumn<CartItem, Integer> colQty;
    @FXML private TableColumn<CartItem, Double> colPrice;

    private ApiClient apiClient = new ApiClient("http://localhost:8080");
    private List<CartItem> cart = new ArrayList<>();

    @FXML
    public void initialize(){
//        colName.setCellValueFactory(c-> c.getValue().productNameProperty());
//        colQty.setCellValueFactory(c-> c.getValue().quantityProperty().asObject());
//        colPrice.setCellValueFactory(c->c.getValue().priceProperty().asObject());
//        cartTable.getItems().setAll(cart);
    }

    @FXML
    private void onLookupProduct(){
        String sku = skuInput.getText();
        ProductDto product = apiClient.getProductBySku(sku);

        if (product == null) {
            showAlert("Product not found");
            return;
        }

        cart.add(new CartItem(product.getName(), 1, product.getPrice()));
        cartTable.getItems().setAll(cart);
    }

    @FXML
    private void onCheckout() {
        boolean success = apiClient.sendOrder(cart);
        if (success) {
            cart.clear();
            cartTable.getItems().clear();
            showAlert("Order completed!");
        } else {
            showAlert("Error completing checkout.");
        }
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    public void onLookupProduct(ActionEvent actionEvent) {
    }

    public void onCheckout(ActionEvent actionEvent) {
    }
}
