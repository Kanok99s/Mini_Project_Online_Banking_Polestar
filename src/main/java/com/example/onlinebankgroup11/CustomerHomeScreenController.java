package com.example.onlinebankgroup11;


import MYSQLConnector.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CustomerHomeScreenController  extends LoginSceneController implements Initializable  {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label accountLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Text REAccountNum;
    @FXML
    private Text REAccountType;
    @FXML
    private Text REfirstname;
    @FXML
    private Text SDAccountNum;
    @FXML
    private Text SDaccountType;
    @FXML
    private Text SDfirstname;
    @FXML
    private Text SDsurname;
    @FXML
    private TextField amount;
    @FXML
    private Text currentBalance;
    @FXML
    private TextField receiverID;
    @FXML
    private Text warningMSG;

    @FXML
    private Text resultTxT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //display in home screen
    public void displayName(String username, String surname, int accountNumber, double balance) {

        nameLabel.setText(username);
        surnameLabel.setText(surname);
        accountLabel.setText(String.valueOf(accountNumber));
        balanceLabel.setText(String.valueOf(balance + " Kr"));

    }




    private void loadPage(String page)  throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
        root = loader.load();
        bp.setCenter(root);

    }


    @FXML // chnage the scene so that on the home page, it shows the transfer scene and now the transfer word
    void transferPressed(ActionEvent event) throws SQLException, IOException {
        getData(loginID);
        bp.setCenter(ap);

    }
    @FXML
    void accountDetailsButtonPressed(ActionEvent event) throws IOException, SQLException {
        getData(loginID);
        //bp.setCenter(ap);
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDetails.fxml"));
        root = loader.load();
        bp.setCenter(root);
        AccountDetailsController controller = loader.getController();
        controller.displayAccountDetails(databaseName, databaseLastName, databaseEmail, databasePhoneNumber, databaseAccountType ,databaseNumber ,databaseCardNumber);
    }


    @FXML
    public void cardsButtonPressed(ActionEvent event) throws SQLException, IOException {
        getData(loginID);
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("virtualCard.fxml"));
        root = loader.load();
        bp.setCenter(root);
        AccountDetailsController controller = loader.getController();
        controller.displayCardDetails(databaseCardNumber, databaseCardNumber,databaseName, databaseLastName, databaseCCV, databaseBalance);

    }



    @FXML
    void transcationHistoryButtonPressed(ActionEvent event) throws IOException {
        loadPage("TransactionHistory");
    }


    @FXML
    void deactivateAccountButtonPressed(ActionEvent event) throws IOException, SQLException {
        getData(loginID);
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deactivateAccount.fxml"));
        root = loader.load();
        bp.setCenter(root);
        AccountDetailsController controller = loader.getController();
        controller.displayDetails(databaseNumber, databaseBalance);

    }

    @FXML
    void loanButtonPressed(ActionEvent event) throws IOException {
        loadPage("Loan");
    }

    @FXML
    void CustomerServiceButtonPressed(ActionEvent event) throws IOException {
        loadPage("CustomerService");
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) throws IOException {
        //return to login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}


