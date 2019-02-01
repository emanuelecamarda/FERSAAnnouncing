package boundary;

import control.LoginController;
import entity.User;
import exception.EntityNotExistException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.JavaFx;

public class LoginBoundary {

    private LoginController loginController = LoginController.getInstance();

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField passwordField;

    @FXML
    public void login() {
        try {
            User u = loginController.login(nicknameField.getText(), passwordField.getText());
            if (u == null) {
                JavaFx.newAlert(Alert.AlertType.ERROR, "Error!", "Wrong password!");
                passwordField.clear();
            }
            Stage stage = (Stage) nicknameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/userProfile.fxml"));
            Parent root = loader.load();
            UserProfileBoundary userProfileBoundary = loader.getController();
            userProfileBoundary.initData(u);
            stage.setTitle("User Profile");
            stage.setScene(new Scene(root, 1000, 650));
            stage.setResizable(false);
            stage.show();

        } catch (EntityNotExistException e) {
            JavaFx.newAlert(Alert.AlertType.ERROR, "Error!", "Nickname not exists!");
            nicknameField.clear();
            passwordField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
