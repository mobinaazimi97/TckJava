package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j;
import tck.model.bl.PersonBl;
import tck.model.da.PersonDa;
import tck.model.entity.Person;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class SignInController implements Initializable {
    @FXML
    private TextField userTxt, passTxt,emailTxt,phoneTxt;
    @FXML
    private Button signInBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("SignIn Start");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sign In Error\n" + e.getMessage());
            alert.show();
        }
        signInBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                Person person = Person
                        .builder()
                        .username(userTxt.getText())
                        .password(passTxt.getText())
                        .email(emailTxt.getText())
                        .phoneNumber(phoneTxt.getText())
                        .build();
                //   logInBtn.getScene().getWindow().hide();
                //  PersonBl.getPersonBl().save(person);
                personDa.save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Signed In\n" + person);
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Information\n" + e.getMessage());
                alert.show();
            }
        });
    }
    private void resetForm() throws Exception{                                  //TODO : CHECK  !
        userTxt.clear();
        passTxt.clear();
        emailTxt.clear();
        phoneTxt.clear();
        PersonBl.getPersonBl().findAll();
    }
}
