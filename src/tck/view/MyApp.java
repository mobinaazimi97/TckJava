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
    public void start(Stage primaryStage) throws Exception {                                                   // TODO : METHODE START HAS ERROR .
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("view/logIn.FXml"))          //TODO : LOGIN??
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("profile");
        primaryStage.setOnCloseRequest((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "are you sure to exit?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
        });
        primaryStage.show();
    }
    // TODO : other windows !
}
