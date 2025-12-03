module com.client.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.PosClient to javafx.fxml;
}