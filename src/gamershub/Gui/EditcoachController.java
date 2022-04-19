/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.Game;
import gamershub.Gamershub;
import gamershub.Services.GameService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EditcoachController implements Initializable {

    @FXML
    private TextField usernamefield;
    @FXML
    private ComboBox<String> gamecombo;
    @FXML
    private TextField descField;
    @FXML
    private Label errorMsg;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Coach c = new Coach();
        usernamefield.setText(Gamershub.loggedUser.getUsername());
        descField.setText(c.getDescription());
         try {
            GameService cs = new GameService();
            List<Game> list = cs.afficher();
            ObservableList<String> listgame = FXCollections.observableArrayList();
            for (Game cat : list) {
                listgame.add(cat.getId() + ":  "+cat.getName());
            }

            gamecombo.setItems(listgame);
        } catch (SQLException ex) {
            Logger.getLogger(EditcoachController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }    

    @FXML
    private void UpdatecoachClick(ActionEvent event) {
        
    }
    
}
