package Customer;

import DBConnection.DatabaseConnection;
import Employee.EmployeeLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSceneController {
    Stage stage;
    Scene scene;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private TextField IDnumber;

    @FXML
    private Label validateLabel;

    @FXML
    Button exitBtn;

    @FXML
    Button signUpBtn;
    @FXML
    Button forgotPassButton;
@FXML
Button EmployeeButton;

    protected void loginCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("mainPage.fxml"));
        Stage window = (Stage) loginButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 849, 609));

    }
    @FXML
    public void loginButtonOnAction(ActionEvent event) {


        if (!IDnumber.getText().isBlank() & !password.getText().isBlank()) {
            validateLogin();
        } else {
            validateLabel.setText("enter both ID number and password.");
        }
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB  = connectNow.getConnection();

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement("SELECT * FROM CustomerInformation.customer_details WHERE  IDnumber= ? and Password = ?");
            preparedStatement.setString(1,IDnumber.getText() );
            preparedStatement.setString(2, password.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loginCustomer();

            } else {
                validateLabel.setText("invalid login. Please try again!");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();}
    }

    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
    public void signupBtn(ActionEvent event) throws Exception {
        DatabaseConnection connect = new DatabaseConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(signupController.class.getResource("signUp.fxml"));
        Stage signupStage = (Stage) signUpBtn.getScene().getWindow();
        signupStage.setScene(new Scene(fxmlLoader.load(), 780, 600));
        signupStage.show();

    }
    public void forgotPassBtn(javafx.event.ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(UpdatePassword.class.getResource("updatePassword.fxml"));
        Stage newPassStage = (Stage) forgotPassButton.getScene().getWindow();
        newPassStage.setScene(new Scene(fxmlLoader.load(), 635, 458));
        newPassStage.show();

    }

    @FXML
    public void employeePage (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeLogin.class.getResource("EmployeeLogin.fxml"));
        Stage stage = (Stage) EmployeeButton.getScene().getWindow();
       stage.setScene(new Scene(fxmlLoader.load(), 654, 450));
        stage.show();


    }
}
