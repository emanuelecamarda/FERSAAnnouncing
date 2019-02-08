/**
 * Edit by EC
 */
package boundary;

import bean.ApartmentResearchBean;
import bean.RoomResearchBean;
import control.RecentFacadeController;
import entity.*;
import exception.EntityNotExistException;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import utils.Date;
import utils.JavaFx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecentBoundary {

    private User userLogged;
    private List<Research> recentResearches;
    private RecentFacadeController recentFacadeController = new RecentFacadeController();

    @FXML private Label IDField, cityField, priceMinField, priceMaxField, sizeField, sortingField, dateField,
            otherLabel1, otherField1, otherLabel2, otherField2, otherLabel3, otherField3;

    @FXML private ListView<String> listView;

    @FXML private Button researchButton, userProfileButton;

    public void initialize() {
        clearAll();
    }

    public void initData(User userLogged) {
        this.userLogged = userLogged;
        this.recentResearches = recentFacadeController.recentResearch(userLogged);
        List<String> list = new ArrayList<>();
        for (Research r : this.recentResearches) {
            if (r.getClass().equals(ApartmentResearch.class))
                list.add("ApartmentAnnounce Research ID: " + r.getID());
            else
                list.add("RoomAnnounce Research ID: " + r.getID());
        }
        listView.setItems(FXCollections.observableArrayList(list));
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Research research = recentResearches.get(t1.intValue());
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
        userProfileButton.disableProperty().bind(booleanBind);
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

    public void changeShowResearch() {
        try {
            Research selectedResearch = recentResearches.get(listView.getSelectionModel().getSelectedIndex());
            if (selectedResearch.getClass().equals(ApartmentResearch.class)) {
                selectedResearch = (ApartmentResearch) selectedResearch;
                ApartmentResearchBean apartmentResearchBean = new ApartmentResearchBean(selectedResearch.getCity(),
                        selectedResearch.getPriceMin(), selectedResearch.getPriceMax(), selectedResearch.getSize(),
                        selectedResearch.getFavorite(), selectedResearch.getSorting(),
                        ((ApartmentResearch) selectedResearch).getLocalsMin(),
                        ((ApartmentResearch) selectedResearch).getLocalsMax(),
                        ((ApartmentResearch) selectedResearch).getFurnished(),
                        ((ApartmentResearch) selectedResearch).getBathroomNumberMin(),
                        ((ApartmentResearch) selectedResearch).getBedsNumberMin(),
                        ((ApartmentResearch) selectedResearch).getBedsNumberMax());
                Stage stage = (Stage) cityField.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/showResearch.fxml"));
                Parent root = loader.load();
                ShowResearchBoundary showResearchBoundary = loader.getController();
                showResearchBoundary.initData(apartmentResearchBean, userLogged);
                stage.setTitle("Show Research");
                stage.setScene(new Scene(root, 1000, 650));
                stage.setResizable(false);
                stage.show();
            } else {
                RoomResearchBean roomResearchBean = new RoomResearchBean(selectedResearch.getCity(),
                        selectedResearch.getPriceMin(), selectedResearch.getPriceMax(), selectedResearch.getSize(),
                        selectedResearch.getFavorite(), selectedResearch.getSorting(),
                        ((RoomResearch) selectedResearch).getRoomersNumberMax(),
                        ((RoomResearch) selectedResearch).getPrivateBathroom(),
                        ((RoomResearch) selectedResearch).getOnlyFemale(),
                        ((RoomResearch) selectedResearch).getOnlyMale());
                Stage stage = (Stage) cityField.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/showResearch.fxml"));
                Parent root = loader.load();
                ShowResearchBoundary showResearchBoundary = loader.getController();
                showResearchBoundary.initData(roomResearchBean, userLogged);
                stage.setTitle("Show Research");
                stage.setScene(new Scene(root, 1000, 650));
                stage.setResizable(false);
                stage.show();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (EntityNotExistException e) {
            JavaFx.newAlert(Alert.AlertType.INFORMATION, "Result", "No Announce Founded!");
            clearAll();
            return;
        }
    }

    public void changeUserProfile() {
        try {
            Stage stage = (Stage) cityField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/userProfile.fxml"));
            Parent root = loader.load();
            UserProfileBoundary userProfileBoundary = loader.getController();
            userProfileBoundary.initData(userLogged);
            stage.setTitle("User Profile");
            stage.setScene(new Scene(root, 1000, 650));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}