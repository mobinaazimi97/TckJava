package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonController implements Initializable {
    @FXML
    private TextField idTxt,nameTxt,familyTxt,usernameTxt,passwordTxt,phoneTxt,emailTxt,findByIdTxt,findAllTxt,findByFamilyTxt,findByUsernameTxt;
    @FXML
    private ToggleGroup roleToggle;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
