package tck.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tck.model.da.ResponseDa;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ResponseController implements Initializable {
    @FXML
    private TextField responseIdTxt, ticketIdTxt, personIdTxt, answerTxt, findByIdTxt, findAllTxt, findByTicketIdTxt, findByPersonIdTxt, findByAnswerTxt;
    @FXML
    private DatePicker responseDatePicker, findByDatePicker;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            resetForm();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }
        saveBtn.setOnAction(event -> {
            try (ResponseDa responseDa = new ResponseDa()) {
                Response response = Response
                        .builder()
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                        .dateTime(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                        .answer(answerTxt.getText())
                        .build();
                responseDa.save(response);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "response saved\n" + response.toString());
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "response save error\n" + e.getMessage());
                alert.show();
            }
        });
        editBtn.setOnAction(event -> {
            try (ResponseDa responseDa = new ResponseDa()) {
                Response response = Response
                        .builder()
                        .id(Integer.parseInt(responseIdTxt.getText()))
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                        .dateTime(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                        .answer(answerTxt.getText())
                        .build();
                responseDa.save(response);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "response edited\n" + response.toString());
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "response edit error\n" + e.getMessage());
                alert.show();
            }
        });
        removeBtn.setOnAction(event -> {
            try (ResponseDa responseDa = new ResponseDa()) {
                responseDa.remove(Integer.parseInt(responseIdTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "response removed\n" + responseIdTxt.getText());
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "response remove error\n" + e.getMessage());
                alert.show();
            }
        });
        findByIdTxt.setOnKeyReleased(event -> {
            try(ResponseDa responseDa = new ResponseDa()){
            showDataOnTable(responseDa.findById(Integer.parseInt(findByIdTxt.getText())) ;              // TODO : Error : ( ; )
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search response Id error\n" + e.getMessage());
                alert.show();
            }
        });
            responseTbl.setOnMouseClicked(event -> {
                Response response = responseTbl.getSelectionModel().getSelectedItem();
                responseIdTxt.setText(String.valueOf(response.getId()));
                personIdTxt.setText(String.valueOf(response.getPerson().getId()));
                ticketIdTxt.setText(String.valueOf(response.getTicket().getId()));
                responseDatePicker.setValue(response.getDateTime().toLocalDate());          // TODO : TRUE ?
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
        try (ResponseDa responseDa = new ResponseDa()) {
            showDataOnTable(responseDa.findAll());
        }
//        findByIdTxt.clear();                              // TODO
//        findByPersonIdTxt.clear();
//        findByTicketIdTxt.clear();
//        findByAnswerTxt.clear();
//        findByDatePicker.setValue(null);
    }
}
