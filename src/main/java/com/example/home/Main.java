package com.example.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeeLogin.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Loans.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 402);
        stage.setTitle("Polestar Bank");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}