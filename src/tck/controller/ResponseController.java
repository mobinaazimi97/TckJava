package tck.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tck.model.da.ResponseDa;
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
            try (ResponseDa responseDa=new ResponseDa()){
                Response response = Response
                        .builder()
                        .id(Integer.parseInt(responseIdTxt.getText() ))
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                        .dateTime(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                        .answer(answerTxt.getText())
                        .build();
                responseDa.save(response);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"response saved\n" + response.toString());
                alert.show();
            }catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.ERROR,"response save error\n" + e.getMessage());
             alert.show();
            }
        } );
        editBtn.setOnAction(event -> {
            try (ResponseDa responseDa=new ResponseDa()){
                Response response = Response
                        .builder()
                        .id(Integer.parseInt(responseIdTxt.getText() ))
                        .person(Person.builder().id(Integer.parseInt(personIdTxt.getText())).build())
                        .ticket(Ticket.builder().id(Integer.parseInt(ticketIdTxt.getText())).build())
                        .dateTime(responseDatePicker.getValue())                                                //TODO :NOT found in UI  !!
                        .answer(answerTxt.getText())
                        .build();
                responseDa.save(response);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"response edited\n" + response.toString());
                alert.show();
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"response edit error\n" + e.getMessage());
                alert.show();
            }
        } );
        removeBtn.setOnAction(event -> {
            try (ResponseDa responseDa=new ResponseDa()) {
                responseDa.remove(Integer.parseInt(responseIdTxt.getText()));
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"response removed\n" + responseIdTxt.getText());
                alert.show();
            }catch (Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR,"response remove error\n" + e.getMessage());
                alert.show();
            }
            });
    }

    public void save (Response response){


    }
}
