package com.example.customersearchnew;

import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

    import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class customerSearchController implements Initializable {

        @FXML
        private TableColumn<CustomerSearchModel , Integer> accountnumberTableColumn;

        @FXML
        private TableColumn<CustomerSearchModel , String> accounttypeTableColumn;

        @FXML
        private TableColumn<CustomerSearchModel , Integer> cardnumberTableColumn;


        @FXML
        private TableView<CustomerSearchModel> customerSearchTableView;

        @FXML
        private TableColumn<CustomerSearchModel , String> dobTableColumn;

        @FXML
        private TableColumn<CustomerSearchModel , String> emailTableColumn;

        @FXML
        private TableColumn<CustomerSearchModel , String> firstnameTableColumn;

        @FXML
        private TableColumn<CustomerSearchModel , Integer> idnumberTableColumn;

        @FXML
        private TextField keywordTextField;

        @FXML
        private TableColumn<CustomerSearchModel , String> phonenumberTableColumn;

        @FXML
        private Label searchcustomerLabel;

        @FXML
        private TableColumn<CustomerSearchModel , String> surnameTableColumn;



        ObservableList<CustomerSearchModel> customerSearchModelObservableList = FXCollections.observableArrayList();
        @Override
        public void initialize(URL url, ResourceBundle resource){

            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String customerViewQuery = "SELECT * FROM login.customer_details;" ;

            try{
                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(customerViewQuery);

                while(queryOutput.next()) {
                    String queryIDnumber = queryOutput.getString("IDnumber");
                    String queryAccount_type = queryOutput.getString("Account_type");
                    String queryFirst_name = queryOutput.getString("First_name");
                    String querySurname = queryOutput.getString("Surname");
                    String queryDOB = queryOutput.getString("DOB");
                    String queryEmail = queryOutput.getString("Email");
                    Integer queryPhone_number = queryOutput.getInt("Phone_number");
                    Integer queryCard_number = queryOutput.getInt("Card_number");
                    Integer queryAccount_number = queryOutput.getInt("Account_number");


                    customerSearchModelObservableList.add(new CustomerSearchModel(queryIDnumber, queryAccount_type, queryFirst_name, querySurname,
                            queryDOB, queryEmail, queryPhone_number, queryCard_number, queryAccount_number));
                }

                idnumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
                accounttypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
                firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                surnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("surName"));
                dobTableColumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));
                emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
                phonenumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                cardnumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
                accountnumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
                //description thing

                customerSearchTableView.setItems(customerSearchModelObservableList);

                FilteredList<CustomerSearchModel> filteredData = new FilteredList<>(customerSearchModelObservableList, b-> true);
                keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate(customerSearchModel -> {
                        if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                            return true;
                        }
                        String searchKeyword = newValue.toLowerCase();
                        if(customerSearchModel.getIdNumber().toString().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if(customerSearchModel.getFirstName().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        }else if(customerSearchModel.getSurName().toLowerCase().indexOf(searchKeyword) > -1){
                            return true;
                        }else if(customerSearchModel.getCardNumber().toString().indexOf(searchKeyword) > -1){
                            return true;
                        }else if(customerSearchModel.getAccountNumber().toString().indexOf(searchKeyword) > -1){
                            return true;
                        }else if(customerSearchModel.getAccountType().toLowerCase().indexOf(searchKeyword) > -1){
                            return true;
                        }else if(customerSearchModel.getDOB().toLowerCase().indexOf(searchKeyword) > -1){
                            return true;
                        }else if(customerSearchModel.getEmail().toLowerCase().indexOf(searchKeyword) > -1){
                            return true;
                        }else if(customerSearchModel.getPhoneNumber().toString().indexOf(searchKeyword) > -1) {
                            return true;
                        }else
                            return false;


                    });
                });

                SortedList<CustomerSearchModel> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(customerSearchTableView.comparatorProperty());

                customerSearchTableView.setItems(sortedData);

            }catch(SQLException e){
                Logger.getLogger(customerSearchController.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }


        }
    }




