package polestarbank;

import MYSQLConnector.DatabaseConnector;
import TableModels.TransactionTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerTransactionListController implements Initializable {

    // Establish connection to the database

    DatabaseConnector connectNow = new DatabaseConnector();
    Connection connectDB = connectNow.getConnection();

    // FXML annotation, elements that we need from FXML file TransactionHistore.fxml and that are assigned to the SceneBuilder scene

    @FXML
    private TableView<TransactionTable> transactionsTable; // the table
    @FXML
    private TableColumn<TransactionTable, String> col_id; // id number column
    @FXML
    private TableColumn<TransactionTable, Date> col_date; // date column
    @FXML
    private TableColumn<TransactionTable, Integer> col_sender; // sender account number column
    @FXML
    private TableColumn<TransactionTable, Integer> col_amount; // amount column
    @FXML
    private TableColumn<TransactionTable, Integer> col_payee; //payee account number column
    @FXML
    private TextField keywordTextField; // search text field


    ObservableList<TransactionTable> transactionsList = FXCollections.observableArrayList();  // create Observablelist to allow LISTENER to track any changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String transactionViewQuery = "SELECT * FROM Transcations;";  // the query to extract the data from the transactions table
        try {
            Statement stm = connectDB.createStatement();  // execute query
            ResultSet queryOutput = stm.executeQuery(transactionViewQuery);

            while (queryOutput.next()) { // handle the query result

                String queryId = queryOutput.getString("IDnumber");  // declare variables and initializing to the corresponding data type from database
                Date queryDate = queryOutput.getDate("Date");
                int querySenderAcc = queryOutput.getInt("Sender_account");
                Integer queryAmount = queryOutput.getInt("Transfered_amount");
                Integer queryPayeeAcc = queryOutput.getInt("Receiver_account");


                transactionsList.add(new TransactionTable(queryId, queryDate, // populate the observable list with the data from the query result
                        querySenderAcc, queryAmount, queryPayeeAcc));
            }

            //PropertyValueFactory corresponds to the new Transaction table fields that we annotate above as @FXML
            col_id.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_sender.setCellValueFactory(new PropertyValueFactory<>("senderAccountNumber"));
            col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            col_payee.setCellValueFactory(new PropertyValueFactory<>("receiverAccountNumber"));

            transactionsTable.setItems(transactionsList); // set the populated observable list to the tableview scene

            // initial filtered list
            FilteredList<TransactionTable> filteredData = new FilteredList<>(transactionsList, b -> true);
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {   // add listener to capture changes in the search text field
                filteredData.setPredicate(TransactionTable -> { // set predicate for the filtered list

                    //if no search then display all records

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();  // convert search keyword to lower case

                    if (TransactionTable.getIdNumber().toLowerCase().contains(searchKeyword)) { // check if there are any matches in the column customer Id number to the keyword
                        return true;
                    } else if (TransactionTable.getSenderAcc().toString().indexOf(searchKeyword) > -1) { // match in the column sender Account number
                        return true;
                    } else if (TransactionTable.getPayeeAcc().toString().indexOf(searchKeyword) > -1) {  // match in the column receiver Account number
                        return true;
                    } else {
                        return false; //no match in any of three columns
                    }
                });
            });

            SortedList<TransactionTable> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(transactionsTable.comparatorProperty());

            transactionsTable.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(TransactionTable.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

}

