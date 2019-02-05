/**
 * Edit by EC
 */

package boundary;

import control.ApartmentResearchController;
import dao.ApartmentResearchDao;
import entity.Apartment;
import entity.Sorting;
import entity.User;
import exception.CreationFailedException;
import exception.InvalidInputException;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import utils.JavaFx;

import java.io.IOException;
import java.util.*;

public class ApartmentResearchBoundary {

    private User userLogged;
    private ApartmentResearchController apartmentResearchController = new ApartmentResearchController();

    @FXML private TextField cityField, sizeField, priceMinField, priceMaxField, localsMinField, localsMaxField,
            bathroomNumberField, bedsNumberMinField, bedsNumberMaxField;

    @FXML private ChoiceBox<Sorting> sortingField;

    @FXML private CheckBox favoriteCheck, furnishedCheck;

    @FXML private Button button;

    @FXML private Label errorField;

    public void initialize() {
        Sorting[] array = Sorting.values();
        List<Sorting> list = Arrays.asList(array);
        ObservableList<Sorting> sortings = FXCollections.observableArrayList(list);
        sortingField.setItems(sortings);
        // Disable button if necessary field is empty
        BooleanBinding booleanBind = cityField.textProperty().isEmpty().or(priceMinField.textProperty().isEmpty())
                .or(priceMaxField.textProperty().isEmpty()).or(sizeField.textProperty().isEmpty())
                .or(sortingField.valueProperty().isNull());
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
        localsMinField.clear();
        localsMaxField.clear();
        bathroomNumberField.clear();
        bedsNumberMinField.clear();
        bedsNumberMaxField.clear();
        sortingField.getSelectionModel().clearSelection();
        favoriteCheck.setSelected(false);
        furnishedCheck.setSelected(false);
    }

    public void newApartmentResearch() {
       try {
            Double size = JavaFx.doubleTextFieldCheck(sizeField, errorField, "Error! Size must be a number");
            Double priceMin = JavaFx.doubleTextFieldCheck(priceMinField, errorField, "Error! Price Min must be a number");
            Double priceMax = JavaFx.doubleTextFieldCheck(priceMaxField, errorField, "Error! Price Max must be a number");
            Integer localsMin = JavaFx.integerTextFieldCheck(localsMinField, errorField, "Error! Locals Min must be a integer " +
                    "number");
            Integer localsMax = JavaFx.integerTextFieldCheck(localsMaxField, errorField, "Error! Locals Max must be a integer " +
                    "number");
            Integer bathrommNumber = JavaFx.integerTextFieldCheck(bathroomNumberField, errorField, "Error! Bathroom Number must be a " +
                    "integer number");
            Integer bedsNumberMin = JavaFx.integerTextFieldCheck(bedsNumberMinField, errorField, "Error! Beds Min Number must be a " +
                    "integer number");
            Integer bedsNumberMax = JavaFx.integerTextFieldCheck(bedsNumberMaxField, errorField, "Error! Beds Max Number must be a " +
                    "integer number");

            if (priceMin > priceMax) {
                errorField.setText("Price Max must be greater then Price Min!");
                priceMinField.clear();
                priceMaxField.clear();
                throw new InvalidInputException();
            }
            if (localsMin != null && localsMax != null) {
                if (localsMin > localsMax) {
                    errorField.setText("Locals Max must be greater then Locals Min!");
                    localsMinField.clear();
                    localsMaxField.clear();
                    throw new InvalidInputException();
                }
            }
            if (bedsNumberMin != null && bedsNumberMax != null) {
                if (bedsNumberMin > bedsNumberMax) {
                    errorField.setText("Beds Number Max must be greater then Beds Number Min!");
                    bedsNumberMinField.clear();
                    bedsNumberMaxField.clear();
                    throw new InvalidInputException();
                }
            }

            List<Apartment> apartments = apartmentResearchController.newApartmentResearch(cityField.getText(), priceMin, priceMax, size, favoriteCheck.isSelected(), userLogged, sortingField.getValue().toString(), localsMin, localsMax, furnishedCheck.isSelected(), bathrommNumber, bedsNumberMin, bedsNumberMax);

            System.out.println(apartments);
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
