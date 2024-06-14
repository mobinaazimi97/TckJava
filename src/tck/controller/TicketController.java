package tck.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import tck.model.bl.TicketBl;
import tck.model.entity.Response;
import tck.model.entity.Ticket;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
@Log4j


public class TicketController implements Initializable {
    @FXML
    private TextField ticketIdTxt,personIdTxt,titleTxt,textTxt,findByIdTxt,findByPersonIdTxt,findByTitleTxt,findByTextTxt,findByGroupTxt,findByStatusTxt;
    @FXML
    private CheckBox seenChk,downChk,answerChk;
    @FXML
    private DatePicker findBystartDatePick,findByEndDatePick;
    @FXML
    private ToggleGroup groupToggle;
    @FXML
    private RadioButton materialRdo,facilitiesRdo;
    @FXML
    private Button saveBtn,editBtn,removeBtn;
    @FXML
    private MenuItem closeMnu,newMnu;
    @FXML
    private TableView <Ticket>ticketTbl;
    @FXML
    private TableColumn<Ticket,Integer>ticketIdCol,personIdCol;
    @FXML
    private TableColumn<Ticket,String> groupCol,statusCol,textCol,titleCol;
    @FXML
    private TableColumn<Ticket, LocalDate>startDateCol,endDateCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void showDataOnTable(List<Ticket> ticketList) {
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
        showDataOnTable(TicketBl.getTicketBl().findAll());
    }
}
