/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Game;
import gamershub.Entities.User;
import gamershub.Services.GameService;
import gamershub.Services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class GamesContentFormController implements Initializable {

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameService us = new GameService();
        try {
            List<Game> gamesList = us.afficher();

            for (Game u : gamesList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GamesCardForm.fxml"));
                Parent root = loader.load();
                GamesCardFormController cont = loader.getController();
                cont.setGameImage("http://127.0.0.1:8000/games/images/" + u.getImage());
                cont.setGameName(u.getName());
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
}
