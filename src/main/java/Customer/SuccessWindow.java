package Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class SuccessWindow {
    @FXML
    Button exitBtn;
    @FXML
    Button goBackBtn;



        public void goBackBtn() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginSceneController.class.getResource("Login.fxml"));
        Stage stage1 = (Stage) goBackBtn.getScene().getWindow();
        stage1.setScene(new Scene(fxmlLoader.load(), 849, 609));


    }

    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }


}
