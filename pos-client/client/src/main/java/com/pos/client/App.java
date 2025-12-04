package com.pos.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pos-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Mini POS Client");
        stage.setScene(scene);
        stage.show();
    }
}
