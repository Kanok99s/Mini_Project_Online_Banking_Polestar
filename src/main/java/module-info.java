module Online_Bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Customer to javafx.fxml;
    exports Customer;
    exports DBConnection;
    opens DBConnection to javafx.fxml;
    exports Employee;
    opens Employee to javafx.fxml;
}