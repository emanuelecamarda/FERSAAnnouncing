package boundary;

import entity.Announce;
import entity.User;
import javafx.fxml.FXML;

import java.awt.*;

public class SingleAnnounceBoundary {

    @FXML private Label IDField , CityField , AddressField , PriceField , DescriptionField , AvailableField , DateField;

    private User loggedUser;
    private int selectedAnnounce;

    public void initData(int selectedAnnounce , User loggedUser){

        this.selectedAnnounce = selectedAnnounce;
        this.loggedUser = loggedUser;

    }
}
