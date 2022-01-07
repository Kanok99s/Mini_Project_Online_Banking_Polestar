module com.example.home {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.home to javafx.fxml;
    exports com.example.home;
    exports com.example.home.approveloan;
    opens com.example.home.approveloan to javafx.fxml;
    exports com.example.home.database;
    opens com.example.home.database to javafx.fxml;
    exports com.example.home.employeelogin to javafx.fxml;
    opens com.example.home.employeelogin to javafx.fxml;
}