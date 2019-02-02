/**
 * Edit by EC
 */

package boundary;

import entity.Sorting;
import entity.User;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.*;

public class ApartmentResearchBoundary {

    private User userLogged;

    @FXML private TextField cityField, sizeField, priceMinField, priceMaxField, localsMinField, localsMaxField,
            bathroomNumberField, bedsNumberMinField, bedsNumberMaxField;

    @FXML private ChoiceBox<Sorting> sortingField;

    @FXML private CheckBox favoriteCheck, furnishedCheck;

    @FXML private Button button;

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

    public void newApartmentResearch() {

        //TODO gestire gli input e richiamare il controllore per effettuare una nuova ricerca. In seguito cambiare scena

    }
}
