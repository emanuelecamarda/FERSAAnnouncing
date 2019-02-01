package boundary;

import control.SignupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SignupBoundary {

    private SignupController signupController = new SignupController();

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField emailField;

    @FXML
    private ChoiceBox<String> genderField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField surnameField;

    @FXML
    private Button submitButton;

    public void initialize() {
        ObservableList<String> genders =
                FXCollections.observableArrayList("Male", "Female");
        genderField.setItems(genders);
    }

    @FXML
    public void signup() {
        signupController.signup(nicknameField.getText(), nameField.getText(), surnameField.getText(),
                emailField.getText(), passwordField.getText(), genderField.getValue().toCharArray()[0]);

    }
}
