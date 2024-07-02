package tck.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {
    public static void showProfileForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/logIn.FXml")));
        stage.setScene(scene);
        stage.setTitle("profile");
        stage.show();
    }

    public static void showPersonForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("tck/view/person.FXml")));
        stage.setScene(scene);
        stage.setTitle("profile");
        stage.show();
    }

    public static void showResponseForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("tck/view/response.fxml"))
        );
        stage.setScene(scene);
        stage.setTitle("response");
        stage.show();
    }

    public static void showTicketForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("tck/view/ticket.fxml"))
        );
        stage.setScene(scene);
        stage.setTitle("ticket");
        stage.show();
    }
    public static void showAdminForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("tck/view/admin.person.fxml"))
        );
        stage.setScene(scene);
        stage.setTitle("Admin.Person");
        stage.show();
    }
}
