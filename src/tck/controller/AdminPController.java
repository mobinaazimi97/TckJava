package tck.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import tck.model.bl.AdminBl;
import tck.model.entity.Admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
@Log4j

public class AdminPController implements Initializable {
    @FXML
    private TextField findByIdTxt, findByFamilyTxt, findByUserTxt, findByPassTxt;
    @FXML
    private MenuItem closeMnu, newMnu;
    @FXML
    private TableView<Admin> findPersonTbl;
    @FXML
    private TableColumn<Admin, Integer> findIdCol;
    @FXML
    private TableColumn<Admin, String> findFamilyCol, findUserCol, findPassCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Admin Windows Start For Person Search !");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Admin Load Error\n" + e.getMessage());
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
            log.info("Admin Windows Closed");
        });
        findByIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                AdminBl.getAdminBl().findByPersonId(Integer.parseInt(findByIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by person id success" + Integer.parseInt(findByIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person Id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Id Error" + e.getMessage());
            }
        });
        findByFamilyTxt.setOnKeyReleased(event -> {
            try {
                showDataOnTable(AdminBl.getAdminBl().findByPersonFamily(findByFamilyTxt.getText()));              //TODO
                log.info("found by family success" + findByFamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search by family error\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error" + e.getMessage());
            }
        });
        findByUserTxt.setOnKeyReleased(event -> {                        //Todo
            try {
                AdminBl.getAdminBl().findByPersonUser(findByUserTxt.getText());              //TODO
                log.info("found person username" + findByUserTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person username error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Username Error" + e.getMessage());
            }
        });
        findByPassTxt.setOnKeyReleased(event -> {                        //Todo
            try {
                AdminBl.getAdminBl().findByPersonPass(findByPassTxt.getText());              //TODO
                log.info("found person password" + findByPassTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person password error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Password Error" + e.getMessage());
            }
        });
        findPersonTbl.setOnMouseClicked(event -> {
            Admin admin = findPersonTbl.getSelectionModel().getSelectedItem();
            findByIdTxt.setText(String.valueOf(admin.getPerson().getId()));
            findByFamilyTxt.setText(String.valueOf(admin.getPerson().getFamily()));
            findByUserTxt.setText(String.valueOf(admin.getPerson().getUsername()));
            findByPassTxt.setText(admin.getPerson().getPassword());          // TODO : TRUE ?
        });
    }
    private void showDataOnTable(List<Admin>adminListPerson) {
        ObservableList<Admin> observableList = FXCollections.observableList(adminListPerson);
        findIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        findFamilyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
        findUserCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        findPassCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        findPersonTbl.setItems(observableList);
    }
        private void resetForm () throws Exception {
            findByIdTxt.clear();
            findByFamilyTxt.clear();
            findByUserTxt.clear();
            findByPassTxt.clear();
            showDataOnTable(AdminBl.getAdminBl().findAll());
    }
}
