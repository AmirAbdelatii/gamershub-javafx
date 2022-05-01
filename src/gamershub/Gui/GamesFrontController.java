/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Game;
import gamershub.Services.GameService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class GamesFrontController implements Initializable {

    @FXML
    private HBox gamesList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameService us = new GameService();
        try {
            List<Game> gamesListt = us.afficher();

            for (Game u : gamesListt) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameCardFront.fxml"));
                Parent root = loader.load();
                GameCardFrontController cont = loader.getController();
                cont.setGameImage("http://127.0.0.1:8000/games/images/" + u.getImage());
                cont.setGameName(u.getName());
                cont.setLike(us.getLike(u.getId()));
                gamesList.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
}
