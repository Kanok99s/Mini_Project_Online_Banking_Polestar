module com.example.onlinebankgroup11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.onlinebankgroup11 to javafx.fxml;
    exports com.example.onlinebankgroup11;

    exports TableModels;
    opens TableModels to javafx.fxml;
}