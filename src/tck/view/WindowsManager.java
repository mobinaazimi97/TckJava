package tck.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {
    public static void showPersonForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/profile.FXml")));
        stage.setScene(scene);
        stage.setTitle("profile");
        stage.show();
    }
    // |OTHER EXAMPLES:
    public static void showStuffForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/stuff.fxml"))
        );
        stage.setScene(scene);
        stage.setTitle("profile");
        stage.show();
    }
    public static void showUserForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/user.fxml"))
        );
        stage.setScene(scene);
        stage.setTitle("profile");
        stage.show();
    }
}
