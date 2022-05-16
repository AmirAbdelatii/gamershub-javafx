/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Player;
import static gamershub.Gui.ListPlayerBackController.currentPlayer;
import gamershub.Services.PlayerService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class UpdatePlayerFormController implements Initializable {

    @FXML
    private TextField updateRank;
    @FXML
    private Button btnUpdatePlayer;
    
        private Player b = currentPlayer;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updatePlayer(ActionEvent event) {
        if (updateRank.getText().length() != 0){
             b.setRank(updateRank.getText());

             
             PlayerService bs = new PlayerService();
             
             
             try {
                 bs.UpdatePlayer(b);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                 Parent root = loader.load();
                 updateRank.getScene().setRoot(root);
                 
             } catch (IOException ex) {
                 Logger.getLogger(ListPlayerBackController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
    
}
