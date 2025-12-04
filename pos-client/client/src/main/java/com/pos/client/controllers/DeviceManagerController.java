package com.pos.client.controllers;

import com.pos.client.context.DeviceContext;
import com.pos.client.devices.DeviceManager;
import com.pos.client.devices.PosDevice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeviceManagerController {

    @FXML private TableView<PosDevice> deviceTable;
    @FXML private TableColumn<PosDevice, String> colName;
    @FXML private TableColumn<PosDevice, String> colType;
    @FXML private TableColumn<PosDevice, String> colIdentifier;
    @FXML private TableColumn<PosDevice, Boolean> colConnected;

    @FXML private Button btnScan;
    @FXML private Button btnConnect;
    @FXML private Button btnDisconnect;

    private final DeviceManager manager = new DeviceManager();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colIdentifier.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        colConnected.setCellValueFactory(new PropertyValueFactory<>("connected"));

        loadDevices();
    }

    @FXML
    private void loadDevices() {
        deviceTable.getItems().setAll(manager.getDevices());
    }

    @FXML
    public void scanDevices() {
        manager.scanDevices();
        loadDevices();
    }

    @FXML
    public void connectToDevice() {
        PosDevice device = deviceTable.getSelectionModel().getSelectedItem();
        if (device != null && manager.connect(device)) {
            if ("Printer".equalsIgnoreCase(device.getType())) {
                DeviceContext.setPrinter(device);
            } else if ("Scanner".equalsIgnoreCase(device.getType())) {
                DeviceContext.setScanner(device);
            }
            deviceTable.refresh();
        }
    }

    @FXML
    public void disconnectDevice() {
        PosDevice device = deviceTable.getSelectionModel().getSelectedItem();
        if (device != null) {
            if ("Printer".equalsIgnoreCase(device.getType())) {
                DeviceContext.setPrinter(null);
            } else if ("Scanner".equalsIgnoreCase(device.getType())) {
                DeviceContext.setScanner(null);
            }
            manager.disconnect(device);
            deviceTable.refresh();
        }
    }
}
