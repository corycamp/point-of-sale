package com.pos.client.controllers;

import com.pos.client.api.ApiClient;
import com.pos.client.api.ProductDto;
import com.pos.client.context.DeviceContext;
import com.pos.client.devices.PosDevice;
import com.pos.client.models.CartItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PosController {
    @FXML
    private TextField skuInput;
    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> colName;
    @FXML private TableColumn<CartItem, Integer> colQty;
    @FXML private TableColumn<CartItem, Double> colPrice;

    private final ApiClient apiClient = new ApiClient("http://localhost:8080");
    private final List<CartItem> cart = new ArrayList<>();

    @FXML
    public void initialize(){
        colName.setCellValueFactory(c-> c.getValue().productNameProperty());
        colQty.setCellValueFactory(c-> c.getValue().quantityProperty().asObject());
        colPrice.setCellValueFactory(c->c.getValue().priceProperty().asObject());
        cartTable.getItems().setAll(cart);
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
        PosDevice printer = DeviceContext.getPrinter();
        if (printer != null && printer.isConnected()) {
            String receipt = generateReceiptText();
            printer.send(receipt); // POSDevice.send() could handle RS232/USB communication
        }

        // Example: read scanner if needed
        PosDevice scanner = DeviceContext.getScanner();
        if (scanner != null && scanner.isConnected()) {
            // Optionally read barcode input or simulate
        }

        // Clear cart, persist order to API/DB
        boolean success = apiClient.sendOrder(cart);
        if (success) {
            cart.clear();
            cartTable.getItems().clear();
            showAlert("Order completed!");
        } else {
            showAlert("Error completing checkout.");
        }
    }

    @FXML
    private void openDeviceManager() throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/device-manager.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Device Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    public void onLookupProduct(ActionEvent actionEvent) {
        this.onLookupProduct();
    }

    public void onCheckout(ActionEvent actionEvent) {
       this.onCheckout();
    }

    private String generateReceiptText() {
        // Simple example, can be extended
        StringBuilder sb = new StringBuilder();
        cartTable.getItems().forEach(item -> {
            sb.append(item.getProductName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = ")
                    .append(item.getPrice())
                    .append("\n");
        });
        return sb.toString();
    }
}
