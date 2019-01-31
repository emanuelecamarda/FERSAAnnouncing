package utils;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class JavaFx {

    public static void newAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static FXMLLoader loadNewScene(Stage stage, String file, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource(file));
        Parent root = loader.load();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 650));
        stage.setResizable(false);
//        stage.show();
        return loader;
    }
}
