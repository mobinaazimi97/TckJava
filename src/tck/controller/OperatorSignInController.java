package tck.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import tck.model.bl.OperateBl;
import tck.model.entity.Operator;
import tck.model.entity.Person;
import tck.model.entity.SignIn;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j

public class OperatorSignInController implements Initializable {
    @FXML
    private TextField operatorIdTxt, operatorNumberTxt, signIdTxt, perIdTxt, perUsernameTxt, perPasswordTxt, perPhoneTxt, perMailTxt, searchSignIdTxt, searchPerIdTxt, searchPerUserTxt, searchPerPassTxt, searchPerPhoneTxt, searchPerMailTxt;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private MenuItem closeMnu, newMnu;
    @FXML
    private TableView<Operator> operatorSignInTbl;
    @FXML
    private TableColumn<Operator, Integer> operatorIdCol, signIdCol, perIdCol;
    @FXML
    private TableColumn<Operator, String> operatorNumCol, perUserCol, perPassCol, perPhoneCol, perMailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Operator Windows Start For Sign In Reports !");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Operator Load Error\n" + e.getMessage());
            alert.show();
        }
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "New SignIn Load error\n" + e.getMessage());
                alert.show();
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("Operator Closed");
        });
        saveBtn.setOnAction(event -> {
            try {
                Operator operator = Operator
                        .builder()
                        .id(Integer.parseInt(operatorIdTxt.getText()))
                        .operateNumber(operatorNumberTxt.getText())
                        .signIn(SignIn.builder().id(Integer.parseInt(signIdTxt.getText())).build())
                        .signIn(SignIn.builder().person(Person.builder().id(Integer.parseInt(perIdTxt.getText())).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().username(perUsernameTxt.getText()).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().password(perPasswordTxt.getText()).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().phoneNumber(perPhoneTxt.getText()).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().email(perMailTxt.getText()).build()).build())
                        .build();
                OperateBl.getOperateBl().save(operator);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "operator data for sign in saved\n" + operator);
                alert.show();
                resetForm();
                log.info("Operator Data For SignIn Saved" + operator);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator data for signIn save error\n" + e.getMessage());
                alert.show();
                log.error("Operator Data For Sign In Save Error" + e.getMessage());
            }
        });
        editBtn.setOnAction(event -> {
            try {
                Operator operator = Operator
                        .builder()
                        .id(Integer.parseInt(operatorIdTxt.getText()))
                        .operateNumber(operatorNumberTxt.getText())
                        .signIn(SignIn.builder().id(Integer.parseInt(signIdTxt.getText())).build())
                        .signIn(SignIn.builder().person(Person.builder().id(Integer.parseInt(perIdTxt.getText())).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().username(perUsernameTxt.getText()).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().password(perPasswordTxt.getText()).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().phoneNumber(perPhoneTxt.getText()).build()).build())
                        .signIn(SignIn.builder().person(Person.builder().email(perMailTxt.getText()).build()).build())
                        .build();
                OperateBl.getOperateBl().edit(operator);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "operator data for sign in update\n" + operator);
                alert.show();
                resetForm();
                log.info("Operator Data For SignIn Updated" + operator);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator data for signIn update error\n" + e.getMessage());
                alert.show();
                log.error("Operator Data For Sign In Update Error" + e.getMessage());
            }
        });
        removeBtn.setOnAction(event -> {
            try {
                OperateBl.getOperateBl().remove(Integer.parseInt(operatorIdTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "operator data for signIn removed\n" + operatorIdTxt.getText());
                alert.show();
                log.info("operator data for signIn removed" + operatorIdTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator data for signIn remove error\n" + e.getMessage());
                alert.show();
                log.error("Operator Data For SignIn Remove Error" + e.getMessage());
            }
        });
        searchSignIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findBySignId(Integer.parseInt(searchSignIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by signIn id success" + Integer.parseInt(searchSignIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search signIn id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Sign In Id Error" + e.getMessage());
            }
        });
        searchPerIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByPerId(Integer.parseInt(searchPerIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info(" operator - find by person id success" + Integer.parseInt(searchPerIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator - search person id error\n" + e.getMessage());
                alert.show();
                log.error("Operator - Find By Person Id Error" + e.getMessage());
            }
        });
        searchPerUserTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByPerUsername(searchPerUserTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info(" operator - find by person username success" + searchPerUserTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator - search person username error\n" + e.getMessage());
                alert.show();
                log.error("Operator - Find By Person Username Error" + e.getMessage());
            }
        });
        searchPerPassTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByPerPassword(searchPerPassTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info(" operator - find by person password success" + searchPerPassTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator - search person password error\n" + e.getMessage());
                alert.show();
                log.error("Operator - Find By Person Password Error" + e.getMessage());
            }
        });
        searchPerPhoneTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByPerPhone(searchPerPhoneTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info(" operator - find by person phoneNumber success" + searchPerPhoneTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator - search person phoneNumber error\n" + e.getMessage());
                alert.show();
                log.error("Operator - Find By Person Phone Number Error" + e.getMessage());
            }
        });
        searchPerMailTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByPerEmail(searchPerMailTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info(" operator - find by person email success" + searchPerMailTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator - search person email error\n" + e.getMessage());
                alert.show();
                log.error("Operator - Find By Person Email Error" + e.getMessage());
            }
        });
        operatorSignInTbl.setOnMouseClicked(event -> {
            Operator operator = operatorSignInTbl.getSelectionModel().getSelectedItem();
            operatorIdTxt.setText(String.valueOf(operator.getId()));
            operatorNumberTxt.setText(String.valueOf(operator.getOperateNumber()));
            signIdTxt.setText(String.valueOf(operator.getSignIn().getId()));
            perIdTxt.setText(String.valueOf(operator.getSignIn().getPerson().getId()));
            perUsernameTxt.setText(String.valueOf(operator.getSignIn().getPerson().getUsername()));
            perPasswordTxt.setText(String.valueOf(operator.getSignIn().getPerson().getPassword()));
            perPhoneTxt.setText(String.valueOf(operator.getSignIn().getPerson().getPhoneNumber()));
            perMailTxt.setText(String.valueOf(operator.getSignIn().getPerson().getEmail()));
        });
    }
    private void showDataOnTable(List<Operator> operateList) throws Exception {
        ObservableList<Operator> observableList = FXCollections.observableList(operateList);
        operatorIdCol.setCellValueFactory(new PropertyValueFactory<>("operator id:"));
        operatorNumCol.setCellValueFactory(new PropertyValueFactory<>("operator number:"));
        signIdCol.setCellValueFactory(new PropertyValueFactory<>("signIn id:"));
        perIdCol.setCellValueFactory(new PropertyValueFactory<>("person id:"));
        perUserCol.setCellValueFactory(new PropertyValueFactory<>("person username:"));
        perPassCol.setCellValueFactory(new PropertyValueFactory<>("person password:"));
        perPhoneCol.setCellValueFactory(new PropertyValueFactory<>("person phone:"));
        perMailCol.setCellValueFactory(new PropertyValueFactory<>("person mail:"));
        operatorSignInTbl.setItems(observableList);
    }
    private void resetForm() throws Exception {
        operatorIdTxt.clear();
        operatorNumberTxt.clear();
        signIdTxt.clear();
        perIdTxt.clear();
        perUsernameTxt.clear();
        perPasswordTxt.clear();
        perPhoneTxt.clear();
        perMailTxt.clear();
        showDataOnTable(OperateBl.getOperateBl().findAll());
    }
}
