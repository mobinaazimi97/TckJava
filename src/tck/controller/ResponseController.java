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
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j

public class ResponseController implements Initializable {
    @FXML
    private TextField responseIdTxt, ticketIdTxt, personIdTxt, answerTxt;
    @FXML
    private DatePicker responseDatePicker;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TableView<Response> responseTbl;
    @FXML
    private TableColumn<Response, Integer> responseIdCol, personIdCol, ticketIdCol;
    @FXML
    private TableColumn<Response, String> answerCol;
    @FXML
    private TableColumn<Response, LocalDate> dateCol;
    @FXML
    private MenuItem closeMnu, newMnu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("ResponseClass Start");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }
        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            }
        });
        closeMnu.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("ResponseClass Closed");
        });
        saveBtn.setOnAction(event -> {
            try {
                Response response = Response
                        .builder()
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                        .date(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                        .answer(answerTxt.getText())
                        .build();
                ResponseBl.getResponseBl().save(response);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "response saved\n" + response.toString());
                alert.show();
                resetForm();
                log.info("Response Saved" + response);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "response save error\n" + e.getMessage());
                alert.show();
                log.error("Response Save Error" + e.getMessage());
            }
        });
        editBtn.setOnAction(event -> {
            try {
                Response response = Response
                        .builder()
                        .id(Integer.parseInt(responseIdTxt.getText()))
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                        .date(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                        .answer(answerTxt.getText())
                        .build();
                ResponseBl.getResponseBl().save(response);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "response edited\n" + response.toString());
                alert.show();
                resetForm();
                log.info("Response Updated" + response);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "response edit error\n" + e.getMessage());
                alert.show();
                log.error("Response Edit Error" + e.getMessage());
            }
        });
        removeBtn.setOnAction(event -> {
            try {
                ResponseBl.getResponseBl().remove(Integer.parseInt(responseIdTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "response removed\n" + responseIdTxt.getText());
                alert.show();
                log.info("Response removed" + responseIdTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "response remove error\n" + e.getMessage());
                alert.show();
                log.error("Response Remove Error" + e.getMessage());
            }
        });

        responseTbl.setOnMouseClicked(event -> {
            Response response = responseTbl.getSelectionModel().getSelectedItem();
            responseIdTxt.setText(String.valueOf(response.getId()));
            personIdTxt.setText(String.valueOf(response.getPerson().getId()));
            ticketIdTxt.setText(String.valueOf(response.getTicket().getId()));
            responseDatePicker.setValue(response.getDate());          // TODO : TRUE ?
            answerTxt.setText(String.valueOf(response.getAnswer()));
        });
    }

    private void showDataOnTable(List<Response> responseList) {
        ObservableList<Response> observableList = FXCollections.observableList(responseList);
        responseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdCol.setCellValueFactory(new PropertyValueFactory<>("person id"));
        ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticket id"));
        answerCol.setCellValueFactory(new PropertyValueFactory<>("answer"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        responseTbl.setItems(observableList);

    }

    private void resetForm() throws Exception {
        responseIdTxt.clear();
        personIdTxt.clear();
        ticketIdTxt.clear();
        answerTxt.clear();
        responseDatePicker.setValue(null);
        showDataOnTable(ResponseBl.getResponseBl().findAll());
//        findByIdTxt.clear();                              // TODO ?
//        findByPersonIdTxt.clear();
//        findByTicketIdTxt.clear();
//        findByAnswerTxt.clear();
//        findByDatePicker.setValue(null);
    }
}
