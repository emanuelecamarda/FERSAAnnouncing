package boundary;

import control.ShowResearchController;
import entity.Research;
import entity.User;
import javafx.fxml.FXML;

import javax.swing.text.html.ListView;
import java.awt.*;

public class ShowResearchBoundary {

    private Research research;
    private User loggedUser;

    private ShowResearchController showResearchController = new ShowResearchController();

    @FXML
    private ListView announceList;

    public void initialize(){}

    public void initData(Research research , User loggedUser){

        this.research = research;
        this.loggedUser = loggedUser;
        this.research = showResearchController
    }


}
