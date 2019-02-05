package boundary;

import control.ApartmentResearchController;
import control.RoomResearchController;
import entity.Apartment;
import entity.Room;
import entity.Sorting;
import entity.User;
import exception.CreationFailedException;
import exception.InvalidInputException;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.JavaFx;

import java.util.Arrays;
import java.util.List;

public class RoomResearchBoundary {

    private User userLogged;
    private RoomResearchController roomResearchController = new RoomResearchController();

    @FXML
    private TextField cityField, sizeField, priceMinField, priceMaxField, roomersNumberMaxField;

    @FXML private ChoiceBox<Sorting> sortingField;

    @FXML private CheckBox favoriteCheck, onlyFemaleCheck, onlyMaleCheck, privateBathroomCheck;

    @FXML private Button button;

    @FXML private Label errorField;

    public void initialize() {
        Sorting[] array = Sorting.values();
        List<Sorting> list = Arrays.asList(array);
        ObservableList<Sorting> sortings = FXCollections.observableArrayList(list);
        sortingField.setItems(sortings);
        // Disable button if necessary field is empty
        BooleanBinding booleanBind = (priceMinField.textProperty().isEmpty())
                .or(priceMaxField.textProperty().isEmpty()).or(sizeField.textProperty().isEmpty())
                .or(sortingField.valueProperty().isNull()).or(cityField.textProperty().isEmpty());
        button.disableProperty().bind(booleanBind);
    }

    public void initData(User userLogged) {
        this.userLogged = userLogged;
    }

    public void clearAll() {
        cityField.clear();
        sizeField.clear();
        priceMinField.clear();
        priceMaxField.clear();
        roomersNumberMaxField.clear();
        sortingField.getSelectionModel().clearSelection();
        favoriteCheck.setSelected(false);
        onlyFemaleCheck.setSelected(false);
        onlyMaleCheck.setSelected(false);
        privateBathroomCheck.setSelected(false);
    }

    public void newRoomResearch() {
        try {
            Double size = JavaFx.doubleTextFieldCheck(sizeField, errorField, "Error! Size must be a number");
            Double priceMin = JavaFx.doubleTextFieldCheck(priceMinField, errorField, "Error! Price Min must be a number");
            Double priceMax = JavaFx.doubleTextFieldCheck(priceMaxField, errorField, "Error! Price Max must be a number");
            Integer roomersNumberMax = JavaFx.integerTextFieldCheck(roomersNumberMaxField, errorField, "Error! Roomers Number's Max must be a integer " +
                    "number");

            if (priceMin > priceMax) {
                errorField.setText("Price Max must be greater then Price Min");
                priceMinField.clear();
                priceMaxField.clear();
                throw new InvalidInputException();
            }
            if (onlyMaleCheck.isSelected() && onlyFemaleCheck.isSelected()) {
                errorField.setText("Select only one between \"Only Male\" and \"Only Female\"");
                onlyFemaleCheck.setSelected(false);
                onlyMaleCheck.setSelected(false);
                throw new InvalidInputException();
            }

            List<Room> rooms = roomResearchController.newRoomResearch(cityField.getText(), priceMin, priceMax, size, favoriteCheck.isSelected(), userLogged, sortingField.getValue().toString(), privateBathroomCheck.isSelected(), roomersNumberMax, onlyFemaleCheck.isSelected(), onlyMaleCheck.isSelected());

            System.out.println(rooms);
            // TODO cambio di scena "vista della ricerca"
//            Stage stage = (Stage) cityField.getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/userProfile.fxml"));
//            Parent root = loader.load();
//            UserProfileBoundary userProfileBoundary = loader.getController();
//            userProfileBoundary.initData(u);
//            stage.setTitle("User Profile");
//            stage.setScene(new Scene(root, 1000, 650));
//            stage.setResizable(false);
//            stage.show();
        } catch (InvalidInputException iie) {
            return;
        } catch (CreationFailedException cfe) {
            cfe.printStackTrace();
            JavaFx.newAlert(Alert.AlertType.ERROR, "Error!", "Error during research!");
            clearAll();
//        } catch (IOException e) {
//            e.printStackTrace();
        }

    }
}
