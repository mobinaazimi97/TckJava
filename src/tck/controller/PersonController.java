package tck.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.util.ResourceBundle;
@Log4j

public class PersonController implements Initializable {
    @FXML
    private TextField idTxt,nameTxt,familyTxt,usernameTxt,passwordTxt,phoneTxt,emailTxt,findByIdTxt,findAllTxt,findByFamilyTxt,findByUsernameTxt;
    @FXML
    private RadioButton userRdo,adminRdo;
    @FXML
    private ToggleGroup roleToggle;
    @FXML
    private MenuItem closeMnu , newMnu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newMnu.setOnAction(event -> {
            try {
               resetForm();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load error\n" + e.getMessage());
                alert.show();
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?");
            if ( alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
          log.info("App Closed");
        } );
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });


    }
}
