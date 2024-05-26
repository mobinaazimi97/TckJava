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
                FXMLLoader.load(getClass().getResource("view/ticketing.FXml"))
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("ticketing");
        primaryStage.show();

    }
}
