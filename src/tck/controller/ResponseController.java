package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;

import java.net.URL;
import java.util.ResourceBundle;

public class ResponseController implements Initializable {
    @FXML
    private TextField responseIdTxt,ticketIdTxt,personIdTxt,answerTxt,findByIdTxt,findAllTxt,findByTicketIdTxt,findByPersonIdTxt,findByAnswerTxt;
    @FXML
    private DatePicker responseDatePicker,findByDatePicker;
    @FXML
    private Button saveBtn,editBtn,removeBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveBtn.setOnAction(event -> {
            Response response = Response
                    .builder()
                    .id(Integer.parseInt(responseIdTxt.getText()))
                    .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                    .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                    .dateTime(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                    .answer(answerTxt.getText())
                    .build();
            System.out.println(response);
        });
    }
    public void save (Response response){


    }
}
