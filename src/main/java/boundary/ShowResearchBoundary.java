package boundary;

import bean.ResearchBean;
import control.ShowResearchController;
import entity.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import utils.JavaFx;


import java.util.ArrayList;
import java.util.List;

public class ShowResearchBoundary {

    private ResearchBean researchBean;
    private User loggedUser;
    private int selectedAnnounce;

    private ShowResearchController showResearchController = new ShowResearchController();

    @FXML
    private ListView announceList;

    public void initialize(){}

    public void initData(ResearchBean researchBean , User loggedUser){

        this.researchBean = researchBean;
        this.loggedUser = loggedUser;
        List<Announce> announces = showResearchController.showCurrentResearch(researchBean, loggedUser);
        List<String> list = new ArrayList<>();
        for (Announce r : announces) {
            if (r.getClass().equals(Apartment.class))
                list.add("Apartment ID: " + r.getID());
            else
                list.add("Room ID: " + r.getID());
        }
        announceList.setItems(FXCollections.observableArrayList(list));

        announceList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedAnnounce = announces.get(newValue.intValue()).getID();
            }
        });

    }

    public void OnClick(){
        try {
            Stage stage = (Stage) announceList.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(JavaFx.class.getResource("/standAlone/showSingleAnnounce.fxml"));
            Parent root = loader.load();
            SingleAnnounceBoundary singleAnnounceBoundary = loader.getController();
            singleAnnounceBoundary.initData(selectedAnnounce , loggedUser);
            stage.setTitle("Announce shown");
            stage.setScene(new Scene(root, 1000, 650));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
