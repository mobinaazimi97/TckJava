package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tck.model.da.PersonDa;
import tck.model.entity.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {             //TODO : BL |  DA
    @FXML
    private TextField userTxt, passwordTxt;
    @FXML
    private Button logInBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                Person person = Person
                        .builder()
                        .username(userTxt.getText())
                        .password(passwordTxt.getText())
                        .build();
                personDa.save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Logged in\n" + person.toString());
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password\n" + e.getMessage());
                alert.show();
            }
        });
        //TODO : ResetForm

    }
}
