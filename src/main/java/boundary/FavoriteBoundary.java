/**
 * Edit by EC
 */
package boundary;

import control.FavoriteController;
import entity.*;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.Date;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBoundary {

    private User userLogged;
    private List<Research> favoriteResearches;
    private FavoriteController favoriteController = new FavoriteController();

    @FXML private Label IDField, cityField, priceMinField, priceMaxField, sizeField, sortingField, dateField,
            otherLabel1, otherField1, otherLabel2, otherField2, otherLabel3, otherField3;

    @FXML private ListView<String> listView;

    @FXML private Button researchButton, deleteButton;

    public void initialize() {
        clearAll();
    }

    public void initData(User userLogged) {
        this.userLogged = userLogged;
        this.favoriteResearches = favoriteController.findFavoriteResearches(userLogged);
        List<String> list = new ArrayList<>();
        for (Research r : this.favoriteResearches) {
            if (r.getClass().equals(ApartmentResearch.class))
                list.add("Apartment Research ID: " + r.getID());
            else
                list.add("Room Research ID: " + r.getID());
        }
        listView.setItems(FXCollections.observableArrayList(list));
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Research research = favoriteResearches.get(t1.intValue());
                IDField.setText(String.valueOf(research.getID()));
                cityField.setText(research.getCity());
                priceMinField.setText(research.getPriceMin().toString());
                priceMaxField.setText(research.getPriceMax().toString());
                sizeField.setText(research.getSize().toString());
                sortingField.setText(research.getSorting().toString());
                dateField.setText(Date.gregorianCalendarToString(research.getDate()));
                if (research.getClass().equals(ApartmentResearch.class)) {
                    otherLabel1.setText("Furnished: ");
                    otherField1.setText(((ApartmentResearch) research).getFurnished()?"yes":"no");
                    otherLabel2.setText("Locals Min: ");
                    if (((ApartmentResearch) research).getLocalsMin() != null)
                        otherField2.setText(((ApartmentResearch) research).getLocalsMin().toString());
                    else
                        otherField2.setText("---");
                    otherLabel3.setText("Locals Max: ");
                    if (((ApartmentResearch) research).getLocalsMax() != null)
                        otherField3.setText(((ApartmentResearch) research).getLocalsMax().toString());
                    else
                        otherField3.setText("---");
                }
                else {
                    otherLabel1.setText("Private Bathroom: ");
                    otherField1.setText(((RoomResearch) research).getPrivateBathroom()?"yes":"no");
                    otherLabel2.setText("Roomers Number Max:");
                    if (((RoomResearch) research).getRoomersNumberMax() != null)
                        otherField2.setText(((RoomResearch) research).getRoomersNumberMax().toString());
                    else
                        otherField2.setText("---");
                    otherLabel3.setText("Other: ");
                    if (((RoomResearch) research).getOnlyFemale())
                        otherField3.setText("Only Female");
                    else if (((RoomResearch) research).getOnlyMale())
                        otherField3.setText("Only Male");
                    else
                        otherField3.setText("---");
                }

            }
        });

        BooleanBinding booleanBind = listView.getSelectionModel().selectedItemProperty().isNull();
        researchButton.disableProperty().bind(booleanBind);
        deleteButton.disableProperty().bind(booleanBind);
    }

    public void clearAll() {
        IDField.setText("");
        cityField.setText("");
        priceMinField.setText("");
        priceMaxField.setText("");
        sizeField.setText("");
        sortingField.setText("");
        dateField.setText("");
        otherLabel1.setText("");
        otherField1.setText("");
        otherLabel2.setText("");
        otherField2.setText("");
        otherLabel3.setText("");
        otherField3.setText("");
    }

}
