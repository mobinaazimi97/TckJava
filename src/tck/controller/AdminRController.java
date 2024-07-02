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
import tck.model.bl.ResponseBl;
import tck.model.entity.Admin;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j

public class AdminRController implements Initializable {
    @FXML
    private TextField findByResponseIdTxt,findByTicketIdTxt,findByPersonIdTxt,findByAnswerTxt;
    @FXML
    private DatePicker findByDatePicker;
    @FXML
    private TableView<Admin> findResponseTbl;
    @FXML
    private TableColumn<Admin, LocalDate>findDateCol;
    @FXML
    private TableColumn<Admin, Integer>findResponseIdCol, findTicketIdCol,findPersonIdCol;
    @FXML
    private TableColumn<Admin, String> findAnswerCol;
    @FXML
    private MenuItem closeMnu,newMnu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Admin Windows Start For Response Report !");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Response Report Load Error\n" + e.getMessage());
            alert.show();
        }
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "New Response Load error\n" + e.getMessage());
                alert.show();
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("Response Search Windows Closed");
        });
        findByResponseIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                AdminBl.getAdminBl().findByResponseId(Integer.parseInt(findByResponseIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by response id success" + Integer.parseInt(findByResponseIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search response id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Response Id Error" + e.getMessage());
            }
        });
        findByPersonIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                AdminBl.getAdminBl().findByPersonId(Integer.parseInt(findByPersonIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by person id success" + Integer.parseInt(findByPersonIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person Id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Id Error" + e.getMessage());
            }
        });
        findByTicketIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                AdminBl.getAdminBl().findByTicketId(Integer.parseInt(findByTicketIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by ticket id success" + Integer.parseInt(findByTicketIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search ticket id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Ticket Id Error" + e.getMessage());
            }
        });
        findByAnswerTxt.setOnKeyReleased(event -> {
            try {
                ResponseBl.getResponseBl().findByAnswer(findByAnswerTxt.getText());              //TODO
                log.info("found answer" + findByAnswerTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search answer error\n" + e.getMessage());
                alert.show();
                log.error("Find By Answer Error" + e.getMessage());
            }
        });
        findByDatePicker.setOnKeyReleased(event -> {
            try {
                ResponseBl.getResponseBl().findByDate(LocalDate.from(findByDatePicker.getValue()));          //TODO
                log.info("found Date" + findByDatePicker.getValue());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search Date error\n" + e.getMessage());
                alert.show();
                log.error("Find By Date Error" + e.getMessage());
            }
        });
        findResponseTbl.setOnMouseClicked(event -> {
            Admin admin=findResponseTbl.getSelectionModel().getSelectedItem();
            findByResponseIdTxt.setText(String.valueOf(admin.getResponse().getId()));
            findByTicketIdTxt.setText(String.valueOf(admin.getTicket().getId()));
            findByPersonIdTxt.setText(String.valueOf(admin.getPerson().getId()));
            findByDatePicker.setValue(admin.getResponse().getDate());
            findByAnswerTxt.setText(String.valueOf(admin.getResponse().getAnswer()));
        });
    }
    private void showDataOnTable(List<Admin> adminResponseList){
        ObservableList<Admin> observableList= FXCollections.observableList(adminResponseList);
        findResponseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        findTicketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticket id"));
        findPersonIdCol.setCellValueFactory(new PropertyValueFactory<>("person id"));
        findAnswerCol.setCellValueFactory(new PropertyValueFactory<>("answer"));
        findDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        findResponseTbl.setItems(observableList);
    }
    private void resetForm () throws Exception {
        findByResponseIdTxt.clear();
        findByPersonIdTxt.clear();
        findByTicketIdTxt.clear();
        findByAnswerTxt.clear();
        showDataOnTable(AdminBl.getAdminBl().findAll());
    }
    }
