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
import tck.model.entity.Person;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
@Log4j

public class AdminController implements Initializable {
    @FXML
    private TextField findByIdTxt,findByFamilyTxt,findByUserTxt,findByPassTxt;
    @FXML
    private MenuItem closeMnu, newMnu;
    @FXML
    private TableView<Admin> findPersonTbl;
    @FXML
    private TableColumn<Admin, Integer> findIdCol;
    @FXML
    private TableColumn<Admin, String> findFamilyCol,findUserCol,findPassCol;

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
        findByIdTxt.setOnKeyReleased(event -> {
  //          if (roleToggle.getSelectedToggle().equals(adminRdo)) {                                                            //TODO
                try {
                  AdminBl.getAdminBl().findById(Integer.parseInt(findByIdTxt.getText()));                   // TODO : Wrong : List for showDataOnTable
                    log.info("find by person id success" + Integer.parseInt(findByIdTxt.getText()));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "search person Id error\n" + e.getMessage());
                    alert.show();
                    log.error("Find By Person Id Error" + e.getMessage());
                }
  //          }
        });
        findByFamilyTxt.setOnKeyReleased(event -> {
            try {
  //             showDataOnTable(AdminBl.getAdminBl().findByPersonFamily(findByFamilyTxt.getText()));              //TODO
                log.info("found by family success" + findByFamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search by family error\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error" + e.getMessage());
            }
        });
        findByUserTxt.setOnKeyReleased(event -> {                        //Todo
            try {
    //       AdminBl.getAdminBl().(findByUserTxt.getText()));              //TODO
                log.info("found family" + findByFamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "search family error\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error" + e.getMessage());
            }
        });
 //       private void showDataOnTable(List<Admin>adminList) {
 //           ObservableList<Person> observableList = FXCollections.observableList(adminList);
            findIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            findFamilyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
            findUserCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            findPassCol.setCellValueFactory(new PropertyValueFactory<>("password"));
 //           findPersonTbl.setItems(observableList);
        }
        private void resetForm() throws Exception {
            findByIdTxt.clear();
            findByFamilyTxt.clear();
            findByUserTxt.clear();
            findByPassTxt.clear();
   //         showDataOnTable(AdminBl.getAdminBl().findAll();
//        }
    }
}
