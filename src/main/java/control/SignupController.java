package control;

import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SignupController {

    private UserDao userDao = new UserDao();

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
        userDao.create(nicknameField.getText(), nameField.getText(), surnameField.getText(), emailField.getText(),
                passwordField.getText(), genderField.getValue().toCharArray()[0]);
    }

}
