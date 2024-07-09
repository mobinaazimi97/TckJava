package tck.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import lombok.extern.log4j.Log4j;
import tck.model.bl.OperateBl;
import tck.model.entity.Admin;
import tck.model.entity.Operator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j

public class OperatorAdminController implements Initializable {
    @FXML
    private TextField operateIdTxt, adminIdTxt, operateNumTxt, adminUserTxt, adminPassTxt, searchByAdminIdTxt, searchByAdminUserTxt, searchByAdminPassTxt;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private MenuItem closeMnu, newMnu;
    @FXML
    private TableView<Operator> operateTbl;
    @FXML
    private TableColumn<Operator, Integer> operateIdCol, adminIdCol;
    @FXML
    private TableColumn<Operator, String> adminUserCol, adminPassCol;
    @FXML
    private Line lineLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Operator Windows Start For Admin Reports !");
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
                Alert alert = new Alert(Alert.AlertType.ERROR, "New Admin Load error\n" + e.getMessage());
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
                        .id(Integer.parseInt(operateIdTxt.getText()))
                        .operateNumber(operateNumTxt.getText())
                        .admin(Admin.builder().id(Integer.parseInt(adminIdTxt.getText())).build())
                        .admin(Admin.builder().user(adminUserTxt.getText()).build())
                        .admin(Admin.builder().pass(adminPassTxt.getText()).build())
                        .build();
                OperateBl.getOperateBl().save(operator);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "operator data saved\n" + operator.toString());
                alert.show();
                resetForm();
                log.info("Operator Data Saved" + operator);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator data save error\n" + e.getMessage());
                alert.show();
                log.error("Operator Data Save Error" + e.getMessage());
            }
        });
        editBtn.setOnAction(event -> {
            try {
                Operator operator = Operator
                        .builder()
                        .id(Integer.parseInt(operateIdTxt.getText()))
                        .operateNumber(operateNumTxt.getText())
                        .admin(Admin.builder().id(Integer.parseInt(adminIdTxt.getText())).build())
                        .admin(Admin.builder().user(adminUserTxt.getText()).build())
                        .admin(Admin.builder().pass(adminPassTxt.getText()).build())
                        .build();
                OperateBl.getOperateBl().edit(operator);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "operator data updated\n" + operator.toString());
                alert.show();
                resetForm();
                log.info("Operator Data:" + operator);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator data update error\n" + e.getMessage());
                alert.show();
                log.error("Operator Data Update Error" + e.getMessage());
            }
        });
        removeBtn.setOnAction(event -> {
            try {
                OperateBl.getOperateBl().remove(Integer.parseInt(operateIdTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "operator data removed\n" + operateIdTxt.getText());
                alert.show();
                log.info("operator data removed" + operateIdTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "operator data remove error\n" + e.getMessage());
                alert.show();
                log.error("Operator Data Remove Error" + e.getMessage());
            }
        });
        searchByAdminIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
             OperateBl.getOperateBl().findByAdminId(Integer.parseInt(searchByAdminIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by admin id success" + Integer.parseInt(searchByAdminIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search admin id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Admin Id Error" + e.getMessage());
            }
        });
        searchByAdminUserTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByAdminUsername(searchByAdminUserTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info("find by admin username success" + Integer.parseInt(searchByAdminUserTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search admin username error\n" + e.getMessage());
                alert.show();
                log.error("Find By Admin Username Error" + e.getMessage());
            }
        });
        searchByAdminPassTxt.setOnKeyReleased(event -> {             //TODO
            try {
                OperateBl.getOperateBl().findByAdminPassword(searchByAdminPassTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info("find by admin password success" + Integer.parseInt(searchByAdminPassTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search admin password error\n" + e.getMessage());
                alert.show();
                log.error("Find By Admin Password Error" + e.getMessage());
            }
        });
        operateTbl.setOnMouseClicked(event -> {
            Operator operator = operateTbl.getSelectionModel().getSelectedItem();
            operateIdTxt.setText(String.valueOf(operator.getId()));
            operateNumTxt.setText(String.valueOf(operator.getOperateNumber()));
            adminIdTxt.setText(String.valueOf(operator.getAdmin().getId()));
            adminUserTxt.setText(String.valueOf(operator.getAdmin().getUser()));
            adminPassTxt.setText(String.valueOf(operator.getAdmin().getPass()));
        });
    }
    private void showDataOnTable(List<Operator> operatorList) throws Exception {
        ObservableList<Operator> observableList = FXCollections.observableList(operatorList);
        operateIdCol.setCellValueFactory(new PropertyValueFactory<>("operator id"));
        adminIdCol.setCellValueFactory(new PropertyValueFactory<>("admin id"));
        adminUserCol.setCellValueFactory(new PropertyValueFactory<>("admin username"));
        adminPassCol.setCellValueFactory(new PropertyValueFactory<>("admin password"));
        operateTbl.setItems(observableList);
    }
    private void resetForm() throws Exception {
        operateIdTxt.clear();
        operateNumTxt.clear();
        adminIdTxt.clear();
        adminUserTxt.clear();
        adminPassTxt.clear();
        showDataOnTable(OperateBl.getOperateBl().findAll());
    }
}
