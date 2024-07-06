package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j;
import tck.model.bl.PersonBl;
import tck.model.bl.SignInBl;
import tck.model.da.PersonDa;
import tck.model.da.SignInDa;
import tck.model.entity.Person;
import tck.model.entity.SignIn;

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
            try (SignInDa signInDa = new SignInDa()) {
                SignIn signIn = SignIn
                        .builder()
                        .person(Person.builder().username(userTxt.getText()).build())
                        .person(Person.builder().password(passTxt.getText()).build())
                        .person(Person.builder().phoneNumber(phoneTxt.getText()).build())
                        .person(Person.builder().email(emailTxt.getText()).build())
                        .build();
                signInDa.save(signIn);
            //      PersonBl.getPersonBl().save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Signed In\n" + signIn);
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Information\n" + e.getMessage());
                alert.show();
//                try (PersonDa personDa = new PersonDa()) {
//                    Person person = Person
//                            .builder()
//                            .username(userTxt.getText())
//                            .password(passTxt.getText())
//                            .email(emailTxt.getText())
//                            .phoneNumber(phoneTxt.getText())
//                            .build();
//                    PersonBl.getPersonBl().save(person);
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
