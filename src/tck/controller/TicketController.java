package tck.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import tck.model.bl.ResponseBl;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


@Log4j


public class TicketController implements Initializable {
    @FXML
    private TextField ticketIdTxt, personIdTxt, personUserTxt , titleTxt, textTxt, findByIdTxt, findByPersonIdTxt, findByTitleTxt, findByTextTxt, findByGroupTxt, findByStatusTxt,findByPersonUserTxt;
    @FXML
    private ComboBox<String> statusCmb;
    @FXML
    private DatePicker ticketDatePick, findByStartDatePick, findByEndDatePick;
    @FXML
    private ToggleGroup groupToggle;
    @FXML
    private RadioButton materialRdo, facilitiesRdo;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private MenuItem closeMnu, newMnu;
    @FXML
    private TableView<Ticket> ticketTbl;
    @FXML
    private TableColumn<Ticket, Integer> ticketIdCol, personIdCol;
    @FXML
    private TableColumn<Ticket, String> groupCol, statusCol, textCol, titleCol;
    @FXML
    private TableColumn<Ticket, LocalDate> startDateCol, endDateCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("TicketClass Start");
        for (Status value : Status.values()) {
            statusCmb.getItems().add(value.name());
        }
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ticket Load Error\n" + e.getMessage());
            alert.show();
        }
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ticket Load Error\n" + e.getMessage());
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("TicketClass Closed");
        });
        saveBtn.setOnAction(event -> {
            try {
                RadioButton group = (RadioButton) groupToggle.getSelectedToggle();
                Ticket ticket = Ticket
                        .builder()
                        .id(Integer.parseInt(ticketIdTxt.getText()))
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .person(Person.builder().username(personUserTxt.getText()).build())
                        .title(titleTxt.getText())
                        .text(textTxt.getText())
                        .group(Group.valueOf(group.getText()))
                        .ticketDate(ticketDatePick.getValue())            //TODO
                        .status(Status.valueOf(statusCmb.getSelectionModel().getSelectedItem()))
                        //            TODO    .ticketDateTime(findByEndDatePick.getValue())
//                        .answer(answerChk.isSelected())
//                        .down(downChk.isSelected())
//                        .seen(seenChk.isSelected())
                        .build();
                TicketBl.getTicketBl().save(ticket);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ticket saved\n" + ticket.toString());
                alert.show();
                resetForm();
                log.info("Ticket Saved" + ticket);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ticket save error\n" + e.getMessage());
                alert.show();
                log.error("Ticket Save Error" + e.getMessage());
            }
        });
        editBtn.setOnAction(event -> {
            try {
                RadioButton group = (RadioButton) groupToggle.getSelectedToggle();
                Ticket ticket = Ticket
                        .builder()
                        .id(Integer.parseInt(ticketIdTxt.getText()))
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .person(Person.builder().username(personUserTxt.getText()).build())
                        .title(titleTxt.getText())
                        .text(textTxt.getText())
                        .group(Group.valueOf(group.getText()))
                        .ticketDate(ticketDatePick.getValue())              //TODO
                        //                   TODO    .ticketDateTime(findByEndDatePick.getValue())
                        .status(Status.valueOf(statusCmb.getSelectionModel().getSelectedItem()))
                        .build();
                TicketBl.getTicketBl().edit(ticket);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ticket updated\n" + ticket.toString());
                alert.show();
                resetForm();
                log.info("Ticket Updated" + ticket);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ticket edit error\n" + e.getMessage());
                alert.show();
                log.error("Ticket Edit Error" + e.getMessage());
            }
        });
        removeBtn.setOnAction(event -> {
            try {
                TicketBl.getTicketBl().remove(Integer.parseInt(ticketIdTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ticket removed\n" + ticketIdTxt.getText());
                alert.show();
                log.info("Ticket removed" + ticketIdTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ticket remove error\n" + e.getMessage());
                alert.show();
                log.error("Ticket Remove Error" + e.getMessage());
            }
        });
        findByIdTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findById(Integer.parseInt(findByIdTxt.getText()));                           // TODO : Wrong : List for showDataOnTable
                log.info("find by ticket id success" + Integer.parseInt(findByIdTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search ticket id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Ticket Id Error" + e.getMessage());
            }
        });
        findByPersonIdTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByPersonId(Integer.parseInt(findByPersonIdTxt.getText()));           //TODO
                log.info("found person id" + findByPersonIdTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person id error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Id Error" + e.getMessage());
            }
        });
        findByPersonUserTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByUsername(findByPersonUserTxt.getText());           //TODO
                log.info("found person username" + findByPersonUserTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search person username error\n" + e.getMessage());
                alert.show();
                log.error("Find By Person Username Error" + e.getMessage());
            }
        });
        findByTitleTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByTitle(findByTitleTxt.getText()); // TODO : Wrong : List for showDataOnTable
                log.info("found title" + findByTitleTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search title error\n" + e.getMessage());
                alert.show();
                log.error("Find By Title Error" + e.getMessage());
            }
        });
        findByTextTxt.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByText(findByTextTxt.getText()); // TODO : Wrong : List for showDataOnTable
                log.info("found text" + findByTextTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search text error\n" + e.getMessage());
                alert.show();
                log.error("Find By Text Error" + e.getMessage());
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
        findByStartDatePick.setOnKeyReleased(event -> {
            try {
                TicketBl.getTicketBl().findByDateRange(findByStartDatePick.getValue(), findByEndDatePick.getValue());          //TODO : CHECK .
                log.info("found Date" + findByStartDatePick.getValue() + findByEndDatePick.getValue());                                   //TODO : CHECK
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Search By Range Of Date Error\n" + e.getMessage());
                alert.show();
                log.error("Find By Start Date Error" + e.getMessage());
            }
        });
//        findByEndDatePick.setOnKeyReleased(event -> {
//            try {
//             TicketBl.getTicketBl().findByDateRange(findByEndDatePick.getValue());                          //TODO
//                log.info("found End Date" + findByEndDatePick.getValue());
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, "search by End Date error\n" + e.getMessage());
//                alert.show();
//                log.error("Find By End Date Error" + e.getMessage());
//            }
//        });
        ticketTbl.setOnMouseClicked(event -> {
            Ticket ticket = ticketTbl.getSelectionModel().getSelectedItem();
            ticketIdTxt.setText(String.valueOf(ticket.getId()));
            personIdTxt.setText(String.valueOf(ticket.getPerson().getId()));
            titleTxt.setText(String.valueOf(ticket.getTitle()));
            textTxt.setText(String.valueOf(ticket.getText()));
            ticketDatePick.setValue(ticket.getTicketDate());          // TODO : TRUE ?
            if (ticket.getGroup().equals(Group.Materiel)) {
                materialRdo.setSelected(true);
            } else {
                facilitiesRdo.setSelected(true);
            }
            statusCmb.getSelectionModel().select(ticket.getStatus().ordinal());
        });
    }

    private void showDataOnTable(List<Ticket> ticketList) throws Exception {
        ObservableList<Ticket> observableList = FXCollections.observableList(ticketList);
        ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdCol.setCellValueFactory(new PropertyValueFactory<>("personId"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        textCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("start date"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("end date"));
        ticketTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        ticketIdTxt.clear();
        personIdTxt.clear();
        titleTxt.clear();
        textTxt.clear();
        ticketDatePick.setValue(null);
        materialRdo.setSelected(true);
        statusCmb.getSelectionModel().select(0);
        showDataOnTable(TicketBl.getTicketBl().findAll());
    }
}
