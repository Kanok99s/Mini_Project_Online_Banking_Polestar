module com.example.customersearchnew {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.customersearchnew to javafx.fxml;
    exports com.example.customersearchnew;
}