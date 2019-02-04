/**
 * Edit by EC
 */

package boundary;

import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.JavaFx;

public class UserProfileBoundary {

    private User userLogged;

    @FXML
    private Label nicknameField;

    @FXML
    private Label nameField;

    @FXML
    private Label surnameField;

    @FXML
    private Label emailField;

    @FXML
    private Label genderField;

    public void initData(User userLogged) {
        this.userLogged = userLogged;
        nicknameField.setText(userLogged.getNickname());
        nameField.setText(userLogged.getName());
        surnameField.setText(userLogged.getSurname());
        emailField.setText(userLogged.getEmail());
        genderField.setText(userLogged.getGender().toString());
    }

    public void initialize() {}

    public void changeApartmentResearch() {
        try {
            Stage stage = (Stage) nicknameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/apartmentResearch.fxml"));
            Parent root = loader.load();
            ApartmentResearchBoundary apartmentResearchBoundary = loader.getController();
            apartmentResearchBoundary.initData(userLogged);
            stage.setTitle("Apartment Research");
            stage.setScene(new Scene(root, 1000, 650));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeRoomResearch() {
        try {
            Stage stage = (Stage) nicknameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/roomResearch.fxml"));
            Parent root = loader.load();
            RoomResearchBoundary roomResearchBoundary = loader.getController();
            roomResearchBoundary.initData(userLogged);
            stage.setTitle("Room Research");
            stage.setScene(new Scene(root, 1000, 650));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
