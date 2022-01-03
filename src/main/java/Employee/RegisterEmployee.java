package Employee;

import DBConnection.DatabaseConnection;
import Customer.LoginSceneController;
import Customer.signupController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class RegisterEmployee implements Initializable {



    @FXML
    Label registrationLabel;
    @FXML
    TextField employeeID;
    @FXML
    TextField Username;
    @FXML
    PasswordField Password;
    @FXML
    TextField name;
    @FXML
    TextField Surname;
    @FXML
    Button signUpButton;
    @FXML
    Button goBackBtn;



            @FXML
            public void Register() throws SQLException {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectDB = connection.getConnection();
                if( employeeID.getText().isEmpty() ||Username.getText().isEmpty() || Password.getText().isEmpty() || name.getText().isBlank() || Surname.getText().isEmpty()){
                    registrationLabel.setText("You must fill all boxes!");
                } else if
                (Password.getText().length()<8){
                    registrationLabel.setText("Please choose at least 8 characters, numbers, and symbols");
                } else {

                    String ID = employeeID.getText();
                    String password = Password.getText();
                    String userName = Username.getText();
                    String firstName = name.getText();
                    String surName = Surname.getText();


                    String Fields = "INSERT INTO employeedetails(EmployeeID, Username, Password, Name, Surname) VALUES('";
                    String Values = ID + "','" + password + "','" + userName + "','" + firstName + "','" + surName + "')";
                    String register = Fields + Values;

                    try {

                        Statement statement = connectDB.createStatement();

                        if (statement.execute(register)) {

                            registrationLabel.setText("Your account have been registered successfully!");


                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                }


            public void goBackBtn() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(LoginSceneController.class.getResource("mainPage.fxml")); //Change the fxml to the name of the employee home page
                Stage stage1 = (Stage) goBackBtn.getScene().getWindow();
                stage1.setScene(new Scene(fxmlLoader.load(), 600, 600));


            }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

