package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j;
import tck.model.da.PersonDa;
import tck.model.entity.Person;

import java.net.URL;
import java.util.ResourceBundle;
@Log4j

public class LogInController implements Initializable {             //TODO : BL |  DA
    @FXML
    private TextField userTxt, passwordTxt;
    @FXML
    private Button logInBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("LogIn Start");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Log In Error\n" + e.getMessage());
            alert.show();
        }
        logInBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                Person person = Person
                        .builder()
                        .username(userTxt.getText())
                        .password(passwordTxt.getText())
                        .build();
                personDa.save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Logged in\n" + person);
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password\n" + e.getMessage());
                alert.show();
            }
        });
    }
    private void resetForm() throws Exception{                                  //TODO : CHECK  !
        userTxt.clear();
        passwordTxt.clear();
    }
    }
