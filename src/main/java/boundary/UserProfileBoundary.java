package boundary;

import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}
