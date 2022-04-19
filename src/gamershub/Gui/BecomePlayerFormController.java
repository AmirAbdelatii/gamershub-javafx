    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Player;
import gamershub.Gamershub;
import gamershub.Services.PlayerService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class BecomePlayerFormController implements Initializable {

    @FXML
    private TextField rank;
    @FXML
    private Button btnAddPlayer;
    private List<Player> players= new ArrayList<Player>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BecomePlayer(ActionEvent event) {
        Player p = new Player();
        p.setRank(rank.getText());
        p.setUser(Gamershub.loggedUser);
        PlayerService ps = new PlayerService();
        if (players.contains(Gamershub.loggedUser.getId())) {
        try{
            ps.AddPlayer(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Welcome !");
        alert.setContentText("Congrats you became a player !");
        alert.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("You are already a player !");
        alert.show();
        }
        
    }
    
}
