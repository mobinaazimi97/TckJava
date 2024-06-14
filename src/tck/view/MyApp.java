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
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("tck/view/person.FXml"))
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("person");
        primaryStage.setOnCloseRequest((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "are you sure to exit?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
        });
//      primaryStage.setTitle("My App");
        primaryStage.show();
    }
    // TODO : other windows !
}
