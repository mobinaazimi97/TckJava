package tck.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import tck.model.bl.PersonBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.enums.Role;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j

public class PersonController implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, familyTxt, usernameTxt, passwordTxt, phoneTxt, emailTxt, findByIdTxt, findByFamilyTxt, findByUserPassTxt;
    @FXML
    private RadioButton userRdo, adminRdo;
    @FXML
    private ToggleGroup roleToggle;
    @FXML
    private MenuItem closeMnu, newMnu;
    @FXML
    private CheckBox trueChk, falseChk;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TableView<Person> personTbl;
    @FXML
    private TableColumn<Person, Integer> idCol;
    @FXML
    private TableColumn<Response, String> familyCol, userPassCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Person Windows Start");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Person Load Error\n" + e.getMessage());
            alert.show();
        }
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "New Person Load error\n" + e.getMessage());
                alert.show();
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("App Closed");
        });
        saveBtn.setOnAction(event -> {
            RadioButton role = (RadioButton) roleToggle.getSelectedToggle();
            try {
                Person person = Person
                        .builder()
                        .name(nameTxt.getText())
                        .family(familyTxt.getText())
                        .username(usernameTxt.getText())
                        .password(passwordTxt.getText())
                        .phoneNumber(phoneTxt.getText())
                        .email(emailTxt.getText())
                        .role(Role.valueOf(role.getText()))
                        //TODO         .enabled(trueChk.isSelected())
                        //TODO           .enabled(falseChk.isSelected())
                        .build();
                PersonBl.getPersonBl().save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "person saved\n" + person);
                alert.show();
                resetForm();
                log.info("Person Saved" + person);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "person save error\n" + e.getMessage());
                alert.show();
                log.error("Person Save Error" + e.getMessage());
            }
        });
        editBtn.setOnAction(event -> {
            RadioButton role = (RadioButton) roleToggle.getSelectedToggle();
            try {
                Person person = Person
                        .builder()
                        .name(nameTxt.getText())
                        .family(familyTxt.getText())
                        .username(usernameTxt.getText())
                        .password(passwordTxt.getText())
                        .phoneNumber(phoneTxt.getText())
                        .email(emailTxt.getText())
                        .role(Role.valueOf(role.getText()))
                        //TODO         .enabled(trueChk.isSelected())
                        //TODO           .enabled(falseChk.isSelected())
                        .build();
                PersonBl.getPersonBl().save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "person updated\n" + person);
                alert.show();
                resetForm();
                log.info("Person Updated" + person);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "person edit error\n" + e.getMessage());
                alert.show();
                log.error("Person Edit Error" + e.getMessage());
            }
        });
        removeBtn.setOnAction(event -> {
            try {
                PersonBl.getPersonBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "person removed\n" + idTxt.getText());
                alert.show();
                log.info("Person removed" + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "person remove error\n" + e.getMessage());
                alert.show();
                log.error("Person Remove Error" + e.getMessage());
            }
        });
        findByIdTxt.setOnKeyReleased(event -> {
            try {
                showDataOnTable(PersonBl.getPersonBl().findById(Integer.parseInt(findByIdTxt.getText())));// TODO : Wrong : List for showDataOnTable
                log.info("find by person id success" + Integer.parseInt(findByIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person Id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Id Error" + e.getMessage());
            }
        });
        findByFamilyTxt.setOnKeyReleased(event -> {
            try {
                showDataOnTable(PersonBl.getPersonBl().findByFamily(findByFamilyTxt.getText()));              //TODO
                log.info("found family" + findByFamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search family error\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error" + e.getMessage());
            }
        });
        findByUserPassTxt.setOnKeyReleased(event -> {                        //Todo
            try {
                showDataOnTable(PersonBl.getPersonBl().findByUsernameAndPassword(findByUserPassTxt.getText()));              //TODO
                log.info("found family" + findByFamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search family error\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error" + e.getMessage());
            }
        });
        personTbl.setOnMouseClicked(event -> {
            Person person = personTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(person.getId()));
            nameTxt.setText(String.valueOf(person.getName()));
            familyTxt.setText(String.valueOf(person.getFamily()));
            usernameTxt.setText(String.valueOf(person.getUsername()));
            passwordTxt.setText(String.valueOf(person.getPassword()));
            phoneTxt.setText(String.valueOf(person.getPhoneNumber()));
            emailTxt.setText(String.valueOf(person.getEmail()));
            if (person.getRole().equals(Role.User)) {
                userRdo.setSelected(true);
            } else {
                adminRdo.setSelected(true);
            }
            trueChk.setSelected(person.isEnabled());
            falseChk.setSelected(!person.isEnabled());//TODO : Is not.Check !
        });
    }

    private void showDataOnTable(List<Person> personList) {
        ObservableList<Person> observableList = FXCollections.observableList(personList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
        userPassCol.setCellValueFactory(new PropertyValueFactory<>("username,password"));
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        nameTxt.clear();
        familyTxt.clear();
        usernameTxt.clear();
        passwordTxt.clear();
        phoneTxt.clear();
        emailTxt.clear();
        userRdo.setSelected(true);
        trueChk.setSelected(false);                 //TODO
        falseChk.setSelected(false);
        showDataOnTable(PersonBl.getPersonBl().findAll());
    }
}