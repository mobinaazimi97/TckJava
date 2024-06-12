package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private TextField userTxt,passwordTxt;
    @FXML
    private Button logInBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInBtn.setOnAction(event -> {
            System.out.println("Username:" + userTxt.getText());
            System.out.println("Password:" + passwordTxt.getText());
        });

    }
}
