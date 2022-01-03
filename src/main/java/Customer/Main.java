package Customer;

import Employee.EmployeeLogin;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeLogin.class.getResource("EmployeeLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 600);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();



    }


    private void logout() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}