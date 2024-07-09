package tck.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class MyApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {                                                   // TODO
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("../view/operator.admin.FXml"))          //TODO : ERROR : Can Not Search !
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("Report");
        primaryStage.setOnCloseRequest((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "are you sure to exit?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
        });
        primaryStage.show();
    }
}
//TODO : ticket.fxml : Error : for input string for title & text
//TODO : PERSON.FXML  : ERROR For Search
//TODO : RESPONSE.FXML : ERROR---> TOO MANY VALUE
