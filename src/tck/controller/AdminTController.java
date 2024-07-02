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
import tck.model.bl.TicketBl;
import tck.model.da.AdminDa;
import tck.model.entity.Admin;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class AdminTController implements Initializable {
    @FXML
    private TextField findByTicketIdTxt,findByPersonIdTxt,findByTitleTxt,findByTextTxt,findByGroupTxt,findByStatusTxt,findByPersonUserTxt;
    @FXML
    private DatePicker findByStartDatePick,findByEndDatePick;
    @FXML
    private TableView<Admin> findTicketTbl;
    @FXML
    private TableColumn<Admin, LocalDate> findStartDateCol, findEndDateCol;
    @FXML
    private TableColumn<Admin, Integer> findTicketIdCol,findPersonIdCol;
    @FXML
    private TableColumn<Admin,String>findTitleCol,findGroupCol,findStatusCol,findTextCol;
    @FXML
    private TableColumn<Admin,String>findPersonUserCol;
    @FXML
    private MenuItem closeMnu, newMnu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Admin Windows Start For Ticket Report !");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ticket Report Load Error\n" + e.getMessage());
            alert.show();
        }
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "New Ticket Load error\n" + e.getMessage());
                alert.show();
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("Ticket Search Windows Closed");
        });
        findByTicketIdTxt.setOnKeyReleased(event -> {             //TODO
            try {
                AdminBl.getAdminBl().findByTicketId(Integer.parseInt(findByTicketIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                log.info("find by ticket id success" + Integer.parseInt(findByTicketIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search ticket Id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Ticket Id Error" + e.getMessage());
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
        findByTitleTxt.setOnKeyReleased(event -> {             //TODO
            try {
                TicketBl.getTicketBl().findByTitle(findByTitleTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info("find by ticket title success" + Integer.parseInt(findByTitleTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search ticket title error\n" + e.getMessage());
                alert.show();
                log.error("Find By Ticket Title Error" + e.getMessage());
            }
        });
        findByTextTxt.setOnKeyReleased(event -> {             //TODO
            try {
                TicketBl.getTicketBl().findByText(findByTextTxt.getText());                   // TODO : Wrong : List for showDataOnTable
                log.info("find by text success" + Integer.parseInt(findByTitleTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search ticket title error\n" + e.getMessage());
                alert.show();
                log.error("Find By Ticket Title Error" + e.getMessage());
            }
        });
        findByStartDatePick.setOnKeyReleased(event -> {             //TODO
            try {
                TicketBl.getTicketBl().findByDateRange(findByStartDatePick.getValue(), findByEndDatePick.getValue());          //TODO : CHECK .
                log.info("found Date" + findByStartDatePick.getValue() + findByEndDatePick.getValue());                                   //TODO : CHECK
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Search By Range Of Date Error\n" + e.getMessage());
                alert.show();
                log.error("Find By Start Date Error" + e.getMessage());
            }
        });
        findByStatusTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByStatus(Status.valueOf(findByStatusTxt.getText()));                 // TODO : Wrong :  ENUMS | LIST
                log.info("found status" + findByStatusTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search status error\n" + e.getMessage());
                alert.show();
                log.error("Find By Status Error" + e.getMessage());
            }
        });
        findByGroupTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByGroup(Group.valueOf(findByGroupTxt.getText())); // TODO : Wrong :  ENUMS | LIST
                log.info("found group" + findByGroupTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search group error\n" + e.getMessage());
                alert.show();
                log.error("Find By Group Error" + e.getMessage());
            }
        });
        findByPersonUserTxt.setOnKeyReleased(event -> {
            try {
                AdminBl.getAdminBl().findByPersonUser(findByPersonUserTxt.getText());           //TODO
                log.info("found person username" + findByPersonUserTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person username error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Username Error" + e.getMessage());
            }
        });
        findTicketTbl.setOnMouseClicked(event -> {
            Admin admin=findTicketTbl.getSelectionModel().getSelectedItem();
            findByTicketIdTxt.setText(String.valueOf(admin.getTicket().getId()));
            findByStatusTxt.setText(String.valueOf(admin.getTicket().getStatus()));
            findByGroupTxt.setText(String.valueOf(admin.getTicket().getGroup()));
            findByPersonIdTxt.setText(String.valueOf(admin.getPerson().getId()));
            findByPersonUserTxt.setText(String.valueOf(admin.getPerson().getUsername()));
            findByTitleTxt.setText(String.valueOf(admin.getTicket().getTitle()));
            findByTextTxt.setText(String.valueOf(admin.getTicket().getText()));
            findByStartDatePick.setValue(admin.getTicket().getTicketDate());
        });
    }
    private void showDataOnTable(List<Admin>adminTicketList){
        ObservableList<Admin>observableList= FXCollections.observableList(adminTicketList);
        findTicketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticket id"));
        findPersonIdCol.setCellValueFactory(new PropertyValueFactory<>("person id"));
        findStartDateCol.setCellValueFactory(new PropertyValueFactory<>("start date"));
        findEndDateCol.setCellValueFactory(new PropertyValueFactory<>("end date"));
        findTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        findGroupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        findStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        findTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        findPersonUserCol.setCellValueFactory(new PropertyValueFactory<>("person username"));

        findTicketTbl.setItems(observableList);
    }
    private void resetForm () throws Exception {
        findByStatusTxt.clear();
        findByGroupTxt.clear();
        findByTicketIdTxt.clear();
        findByPersonIdTxt.clear();
        findByTitleTxt.clear();
        findByTextTxt.clear();
        findByPersonUserTxt.clear();
        showDataOnTable(AdminBl.getAdminBl().findAll());
    }
}
