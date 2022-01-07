package com.example.home.employeelogin;

import com.example.home.database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeLoginController {

    @FXML
    private Button ExitButton;
    @FXML
    private TextField IdField;
    @FXML
    private PasswordField PassField;
    @FXML
    private Label WarningLabel;
    @FXML
    Button goBackBtn;
    @FXML
    Button loginButton;

    public void ExitButtonOnAction(ActionEvent event){ //exit scene

        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) { //logs in, gets connected to employee dashboard

        if (!IdField.getText().isBlank() && !PassField.getText().isBlank()) {
            login();
        } else{
            WarningLabel.setText("Don't forget to enter your credentials!");
        }
    }
    @FXML
    public void login () { //login method, runs sql query to check if employee exists


        DatabaseConnector connect = new DatabaseConnector();
        Connection connectDB = connect.getConnection();


        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement("SELECT * FROM employees WHERE  EmployeeID= ? and Password = ?");
            preparedStatement.setString(1, IdField.getText());
            preparedStatement.setString(2, PassField.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                WarningLabel.setText("Welcome back " + IdField.getText() + "!");

            } else {
                WarningLabel.setText("invalid login. Please try again!");
            }


        } catch (Exception event) {
            event.printStackTrace();

        }
    }

}







